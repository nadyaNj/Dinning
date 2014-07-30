package am.ucom.dinning.web.action;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.util.SecurityUtil;
import am.ucom.dinning.web.model.UserBean;

/**
 * This class for show errors.
 * @author arthur
 *
 */
public class LoginAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String returnPage = null;	
		returnPage = "/pages/current_menu.jsp";

		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("user");
		if(userBean == null) {
			returnPage = "/pages/login.jsp";
		} else {
			if(!validUser(userBean)) {
				Map<String, String> errors = new HashMap<String, String>(); 
				errors.put("loginError", "Entered login/password is/are incorrect");
				request.setAttribute("errors", errors);
				request.setAttribute("user", userBean);
				returnPage = "/pages/login.jsp";
			}
			if(userBean.getStateId() != null && userBean.getStateId() == Constants.USER_STATE_PENDING) {
				returnPage = "/pages/userPage.jsp";
			}	
		}
		showTodayMenu(request);
		session.setAttribute("user", userBean);
		
		return returnPage;
	}
	/**
	 *This method for check login validation.
	 * @param userBean
	 * @return
	 */
	private boolean validUser(UserBean userBean) {
		boolean boolUser = false;
		UserService userService = new UserServiceImpl();
		List<UserBean> users = userService.getUserParameters(false);
		String userName = userBean.getUserName();
		String password = userBean.getPassword();
		
		if(userBean.getUserName() != null && userBean.getPassword()!= null){
			for(UserBean user : users) {
				if(userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
					boolUser = true;
					userBean = userService.getUserParametersByName(userName);
					break;
				} 
			}
		}	
		return boolUser;
	}
}