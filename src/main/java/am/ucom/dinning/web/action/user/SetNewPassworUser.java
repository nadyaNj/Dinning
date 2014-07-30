package am.ucom.dinning.web.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.util.MailSender;
import am.ucom.dinning.util.SecurityUtil;
import am.ucom.dinning.web.model.UserBean;

/**
 * extends UserAction
 * @author arthur
 *
 */
public class SetNewPassworUser extends UserAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String returnPage = "/pages/user/usersShowPage.jsp";
		Long userId = Long.parseLong(request.getParameter("id"));
		UserService userService = new UserServiceImpl();
		String password = SecurityUtil.generateTmpPassword();
		userService.updateUserPassword(password, userId, Constants.USER_STATE_PENDING);
		UserBean userBean = userService.getUserById(userId);
		userBean.setPassword(password);
		String email = userBean.getUserEmail();
		if(email == null || email.trim().equals("")) {
			returnPage = "/pages/user/printUnamePass.jsp";
			request.setAttribute("userPro", userBean);	
		} else {
			StringBuilder body = new StringBuilder();
			body.append("your password updated \n").
				 append("new password: ").append(password);
			MailSender.sendMail(email, "updated your password", body.toString());
			request.setAttribute("page", requestPaginationParameter(0));
		}
		return returnPage;
	}

}
