package am.ucom.dinning.web.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * show user page action
 *
 * @author arthur
 */
public class ViewRolesUser extends UserAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        request.setAttribute("comList", companyView());
        request.setAttribute("page", requestPaginationParameter(0));
        return "/pages/user/createUser.jsp";
    }

}
