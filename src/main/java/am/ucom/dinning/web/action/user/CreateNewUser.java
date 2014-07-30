package am.ucom.dinning.web.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.util.MailSender;
import am.ucom.dinning.web.model.UserBean;

/**
 * extends UserAction
 * @author arthur
 *
 */
public class CreateNewUser extends UserAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {		
		// requested in page departaments
		UserBean userBean = setUserParameters(request);
		List<String> errors = validUser(userBean);
		String returnPage = "/pages/user/usersShowPage.jsp";
		if(errors.isEmpty()) {
			UserService userService = new UserServiceImpl();
			userService.saveUser(userBean);
			if(userBean.getUserEmail().equals("")) {
				returnPage = "/pages/user/printUnamePass.jsp";
				request.setAttribute("userPro", userBean);				
			} else {
				StringBuilder body = new StringBuilder();
				body.append("Your account created please login and change password. \n ").
				 append("username: ").append(userBean.getUserName()).
				 append("\n password: ").append(userBean.getPassword());
			MailSender.sendMail(userBean.getUserEmail(), "Welcome Dinning", body.toString());
			}
		} else {
			request.setAttribute("errors", errors);
		}
		request.setAttribute("page", requestPaginationParameter(0));
		
		return returnPage;
	}
	
}
