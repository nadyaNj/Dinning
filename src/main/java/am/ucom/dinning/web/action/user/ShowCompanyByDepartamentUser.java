package am.ucom.dinning.web.action.user;

import am.ucom.dinning.service.DepartamentService;
import am.ucom.dinning.service.impl.DepartamentServiceImpl;
import am.ucom.dinning.web.model.DepartamentBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * extends UserAction
 *
 * @author arthur
 */
public class ShowCompanyByDepartamentUser extends UserAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        Long companyId = Long.parseLong(request.getParameter("companyId"));
        List<DepartamentBean> departamentBean = null;
        if (companyId > 0) {
            DepartamentService depService = new DepartamentServiceImpl();
            departamentBean = depService.getDepartamentByCompanyId(companyId);
        }
        request.setAttribute("departament", departamentBean);
        return "/pages/user/departamentByCompany.jsp";
    }

}
