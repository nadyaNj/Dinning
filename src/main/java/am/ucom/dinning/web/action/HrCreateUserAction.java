package am.ucom.dinning.web.action;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.math.BigInteger;

import java.util.*;

import am.ucom.dinning.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.DepartamentService;
import am.ucom.dinning.service.ProductTypeService;
import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.service.impl.DepartamentServiceImpl;
import am.ucom.dinning.service.impl.ProductTypeServiceImpl;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.web.model.CompanyNameBean;
import am.ucom.dinning.web.model.DepartamentBean;
import am.ucom.dinning.web.model.ProductTypeBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.UserBean;
import java.util.UUID;

/**
 * the class create type
 * @author arthur
 *
 */
public class HrCreateUserAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response) {
		UserService userService = new UserServiceImpl();
		UserBean userBean = new UserBean();
		String pageFlag = request.getParameter("pageFlag");
		HttpSession session = request.getSession();    
		userBean.setUserName(request.getParameter("username"));
		userBean.setUserEmail(request.getParameter("userEmail"));
		userBean.setUserDepId (request.getParameter("userDepId"));
		userBean.setDiscountCode (request.getParameter("dicountCode"));
		userBean.setUserPosition(request.getParameter("userPosition"));
		session.setAttribute("tempUsername", userBean.getUserName());
		userBean.setPassword(setPassword(request));
		
		if (request.getParameter("roleId")==null){
			userBean.setRoleId("4");
		} else {
			userBean.setRoleId(request.getParameter("roleId"));
		}
	
		userBean.setUserDepId(request.getParameter("depid"));
		userBean.setStateId(2);
		setCompDepList(request);
		List<UserBean> users = userService.getUsersByNumber(getPagenumber(request)); 
    	request.setAttribute("users", users);
    	return getReturnPage(request, userBean, users );
	}
	
	/**
	 * 
	 * @param request
	 * The function List the companies in requset and get the appropriate departemnts instance for selected company
	 */
	private void setCompDepList(HttpServletRequest request){	
		
		CompanyNameService companyNameService = new CompanyNameServiceImpl();
		List<CompanyNameBean> companyNameBeanList = companyNameService.getAllCompanyNames();
		request.setAttribute("companyNameList", companyNameBeanList);
		if(request.getParameter("compid")!=null){
			Long compId = Long.parseLong(request.getParameter("compid"));
			for(CompanyNameBean companyNameBean:companyNameBeanList) {
				if(compId.longValue() == companyNameBean.getId().longValue()) {
					request.setAttribute("departaments", companyNameBean.getDepartmentsId());
					break;
				}
			}
		}
	}
	/**
	 * The function generates temporary password, set it in session 
	 * @param request
	 * @return the tgenerated encoded password 
	 */
	
	private String setPassword(HttpServletRequest request){

		HttpSession session = request.getSession();    
		String tempPassword = SecurityUtil.generateTmpPassword();
		session.setAttribute("tempPassword", tempPassword);		
		return tempPassword;		
	}
	
	/**
	 * The function gets the page number for users, set the count of users into requset
	 * @param request
	 * @return the current page
	 */
	
	private Integer getPagenumber(HttpServletRequest request){

		UserService userService = new UserServiceImpl();
		Integer pageNumber = 0;
		String pageNumberReq = request.getParameter("typePageNumber");
		if (pageNumberReq!=null) {
			pageNumber = Integer.parseInt(pageNumberReq);
		}
		int count;
    	int userCount = userService.getUsersCount();
		if((double)userCount %10 != 0) {
    		count = (userCount/10)+1;
    	} else {
    		count = userCount/10;
    	}
		request.setAttribute("count", count);
    	request.setAttribute("userCount", userCount);
    	return pageNumber;
    	
	}
	/**
	 * function to validate if it the pageflag is for printing the user credentilas or is to save user
	 * @param request 
	 * @param userBean instsance of UserBean
	 * @param users List of instsances of UserBean 
	 * @return Sting the page to redirect;
	 */
	private String getReturnPage(HttpServletRequest request, UserBean userBean, List<UserBean> users ){
		HttpSession session = request.getSession();    
		UserService userService = new UserServiceImpl();
		String returnPage = null;
		String pageFl= request.getParameter("pageFlag");
		if(pageFl.equals(Constants.PRINT_USER_PAGE)){
			session.setAttribute("userBean", userBean);

			if(userBean.getUserEmail().equals("")){
				if(!StringUtil.isEmptyString(userBean.getUserName()) && isNameUnique(userBean.getUserName(), userService.getUserNames(null)) ){
					returnPage = "/pages/printpassword.jsp";
					request.setAttribute("pageFlag", Constants.CREATE_USER);
				} else {
					request.setAttribute("errorName", "Invalid name and name unique");
					returnPage = "/pages/user.jsp";
				}	
				
			} else {
				boolean isCompDomain=false;
			 String [] domains = CommonProperties.getDomainsList();
			 String emaildomain=userBean.getUserEmail().substring((userBean.getUserEmail().indexOf("@")+1),userBean.getUserEmail().length());
			 for (String domain : domains){
				 if(emaildomain.equals(domain)){
				 isCompDomain=true;
				 break;
				 }
 			 }
			 if(!isCompDomain) {
					request.setAttribute("errorName", "Select an email from comapny domains");
					returnPage = "/pages/user.jsp";
				}	else {
				
					 userBean = (UserBean) session.getAttribute("userBean");
					 userService.saveUser(userBean);
					 String emailText = "<b>User Name:</b>"+userBean.getUserName()+"</div><div><b>Password:</b>"+session.getAttribute("tempPassword")+"</div ><div > Please use the credentials in order to enter your <a  href=\"edu.iunetworks.am:8080/dinning-web/\" ><b>Dining Room</b></a> at address: <a  href=\"edu.iunetworks.am:8080/dinning-web/\" >edu.iunetworks.am:8080/dinning-web/</a></div>";
		             	
					 MailSender.sendMail(userBean.getUserEmail(),"userCredentials", emailText);
						
					 users = userService.getUsersByNumber(getPagenumber(request)); 
				     request.setAttribute("users", users);
				     returnPage = "/pages/user.jsp";
				    	
				}
			}
			
			
		}
		else 
			{
			 userBean = (UserBean) session.getAttribute("userBean");
			 userService.saveUser(userBean);
			 session.setAttribute("userBean", null);
				
			    users = userService.getUsersByNumber( getPagenumber(request)); 
		    	request.setAttribute("users", users);
		    	
		    	
		    	returnPage = "/pages/user.jsp";
		}
		return returnPage;
	}
	
}
