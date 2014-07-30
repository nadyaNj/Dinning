package am.ucom.dinning.web.action.user;

import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.web.model.DepartamentBean;
import am.ucom.dinning.web.model.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * extends UserAction
 *
 * @author arthur
 */
public class UpdateUser extends UserAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        UserBean userBean = setUserParameters(request);
        List<String> errors = validUser(userBean);
        if (errors.isEmpty()) {
            UserService userService = new UserServiceImpl();
            userService.update(userBean);
        } else {
            request.setAttribute("errors", errors);
            List<DepartamentBean> departmentList = departmenView(Long.parseLong(userBean.getUserDepId()));
            request.setAttribute("dep", departmentList);
            request.setAttribute("userBean", userBean);
        }
        request.setAttribute("comList", companyView());
        request.setAttribute("page", requestPaginationParameter(0));
        return "/pages/user/createUser.jsp";
    }

}
