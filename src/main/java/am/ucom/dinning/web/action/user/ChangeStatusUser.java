package am.ucom.dinning.web.action.user;

import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.web.model.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * extends UserAction
 *
 * @author arthur
 */
public class ChangeStatusUser extends UserAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        Long userId = Long.parseLong(request.getParameter("id"));
        UserService userService = new UserServiceImpl();
        UserBean userBean = new UserBean();
        userBean.setId(userId);
        userService.updateStatus(userBean);
        return "/pages/user/usersShowPage.jsp";
    }

}
