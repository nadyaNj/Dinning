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
public class EditUser extends UserAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        UserService userService = new UserServiceImpl();
        Long userId = Long.parseLong(request.getParameter("id"));
        UserBean userBean = userService.getUserById(userId);
        List<DepartamentBean> departmentList = departmenView(Long.parseLong(userBean.getUserDepId()));
        request.setAttribute("dep", departmentList);
        request.setAttribute("userBean", userBean);
        // request in page companies
        request.setAttribute("comList", companyView());
        return "/pages/user/userChangeRol.jsp";
    }

}
