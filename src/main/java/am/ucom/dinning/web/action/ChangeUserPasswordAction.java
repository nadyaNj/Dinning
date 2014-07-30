/**
 *
 */
package am.ucom.dinning.web.action;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.util.CommonProperties;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.util.MailSender;
import am.ucom.dinning.util.SecurityUtil;
import am.ucom.dinning.web.model.CompanyNameBean;
import am.ucom.dinning.web.model.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author siranush
 */
public class ChangeUserPasswordAction extends BaseAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see am.ucom.dinning.web.action.BaseAction#execute(HttpServletRequest,
	 * HttpServletResponse)
	 */
	@Override
	public String execute ( HttpServletRequest request, HttpServletResponse response ) {
		UserService userService = new UserServiceImpl();
		setCompDepList( request );
		List<UserBean> users = userService.getUsersByNumber( getPagenumber(request) );
		request.setAttribute("users", users);

		return getReturnPage(request, users);
	}

	/**
	 * @param request
	 *            The function List the companies in requset and get the
	 *            appropriate departemnts instance for selected company
	 */
	private void setCompDepList(HttpServletRequest request) {

		CompanyNameService companyNameService = new CompanyNameServiceImpl();
		List<CompanyNameBean> companyNameBeanList = companyNameService
				.getAllCompanyNames();
		request.setAttribute( "companyNameList", companyNameBeanList );
		if ( request.getParameter("compid") != null ) {
			String test = request.getParameter( "compid" );
			Long compId = Long.parseLong( request.getParameter("compid") );
			for ( CompanyNameBean companyNameBean : companyNameBeanList ) {
				if (compId == companyNameBean.getId()) {
					request.setAttribute( "departaments", companyNameBean.getDepartmentsId() );
					break;
				}
			}
		}
	}

	/**
	 * The function generates temporary password, set it in session
	 * 
	 * @param request
	 * @return the tgenerated encoded password
	 */

	private String setPassword(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String password = null;
		try {
			String tempPassword = SecurityUtil.generateTmpPassword();
			session.setAttribute( "tempPassword", tempPassword );
			password = SecurityUtil.encryptString( tempPassword );
		} catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace();
		}
		return password;

	}

	/**
	 * The function gets the page number for users, set the count of users into
	 * requset
	 * 
	 * @param request
	 * @return the current page
	 */

	private Integer getPagenumber(HttpServletRequest request) {

		UserService userService = new UserServiceImpl();
		Integer pageNumber = 0;// =
								// Integer.parseInt(request.getParameter("pagenumber"));
		String pageNumberReq = request.getParameter("typePageNumber");
		if (pageNumberReq != null) {
			pageNumber = Integer.parseInt(pageNumberReq);
		}

		int count;
		int userCount = userService.getUsersCount();
		if ((double) userCount % 10 != 0) {
			count = (userCount / 10) + 1;
		} else {
			count = userCount / 10;
		}

		request.setAttribute("count", count);
		request.setAttribute("userCount", userCount);
		return pageNumber;

	}

	/**
	 * function to validate if it the pageflag is for printing the user
	 * credentilas or is to save user
	 * 
	 * @param request
	 * @param userBean
	 *            instsance of UserBean
	 * @param users
	 *            List of instsances of UserBean
	 * @return the page to redirect;
	 */
	private String getReturnPage(HttpServletRequest request,
			List<UserBean> users) {
		HttpSession session = request.getSession();
		UserService userService = new UserServiceImpl();
		UserBean userBean = new UserBean();

		String returnPage = null;
		if (request.getParameter("pageFlag").equals(Constants.SETPASSWORD_USER)) {

			Long userId = Long.parseLong(request.getParameter("userid"));
			userBean.setId(userId);
			userBean.setStateId(2);
			userBean.setUserName(request.getParameter("username"));
			String userEmail = request.getParameter("userEmail");
			userBean.setUserEmail(request.getParameter("userEmail"));
			session = request.getSession();
			session.setAttribute("tempUsername", userBean.getUserName());
			userBean.setPassword(setPassword(request));

			if (request.getParameter("roleId") == null) {
				userBean.setRoleId("4");
			} else {
				userBean.setRoleId(request.getParameter("roleId"));
			}

			userBean.setStateId(2);
			session.setAttribute("userBean", userBean);

			if (userBean.getUserEmail().equals("")) {

				returnPage = "/pages/printpassword.jsp";

				request.setAttribute("pageFlag", Constants.SAVEPASSWORD_USER);

			} else {
				boolean isCompDomain = false;
				String[] domains = CommonProperties.getDomainsList();
				String emaildomain = userBean.getUserEmail().substring(
						(userBean.getUserEmail().indexOf("@") + 1),
						userBean.getUserEmail().length());
				for (int i = 0; i < domains.length; i++) {
					if (emaildomain.equals(domains[i])) {
						isCompDomain = true;
						break;
					}
				}
				if (!isCompDomain) {
					request.setAttribute("errorName",
							"Select an email from comapny domains");
					returnPage = "/pages/user.jsp";
				} else {

					userBean = (UserBean) session.getAttribute("userBean");
					userService.setPassword(userBean);
					String emailText = "<b>User Name:</b>"
							+ userBean.getUserName()
							+ "</div><div><b>Password:</b>"
							+ session.getAttribute("tempPassword")
							+ "</div ><div > Please use the credentials in order to enter your <a  href=\"edu.iunetworks.am:8080/dinning-web/\" ><b>Dining Room</b></a> at address: <a  href=\"edu.iunetworks.am:8080/dinning-web/\" >edu.iunetworks.am:8080/dinning-web/</a></div>";

					MailSender.sendMail(userBean.getUserEmail(),
							"userCredentials", emailText);

					users = userService
							.getUsersByNumber(getPagenumber(request));
					request.setAttribute("users", users);
					returnPage = "/pages/user.jsp";

				}
			}

		} else {
			userBean = (UserBean) session.getAttribute("userBean");
			userService.setPassword(userBean);
			session.setAttribute("userBean", null);
			users = userService.getUsersByNumber(getPagenumber(request));
			request.setAttribute("users", users);

			returnPage = "/pages/user.jsp";
		}
		return returnPage;
	}

}
