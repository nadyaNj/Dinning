package am.ucom.dinning.web.action.user;

import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser extends UserAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        UserService userService = new UserServiceImpl();
        Long deleteUserId = Long.parseLong(request.getParameter("id"));
        Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        userService.deleteUser(deleteUserId);
        request.setAttribute("page", requestPaginationParameter(pageNumber - 1));
        return "/pages/user/usersShowPage.jsp";
    }

}
