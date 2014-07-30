package am.ucom.dinning.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * extends BaseAction
 *
 * @author arthur
 */
public class LogoutAction extends BaseAction {

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        showTodayMenu(request);
        return "/pages/current_menu.jsp";
    }
}
