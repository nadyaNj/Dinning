package am.ucom.dinning.web.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * extends UserAction
 *
 * @author arthur
 */
public class ShowUser extends UserAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        request.setAttribute("page", requestPaginationParameter(pageNumber - 1));
        return "/pages/user/usersShowPage.jsp";
    }

}
