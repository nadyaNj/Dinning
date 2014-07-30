package am.ucom.dinning.web.action;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.DepartamentService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.service.impl.DepartamentServiceImpl;
import am.ucom.dinning.web.model.CompanyNameBean;
import am.ucom.dinning.web.model.DepartamentBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class for delete departament
 *
 * @author arthur
 */
public class DeleteDepartamentAction extends BaseAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("deleteDepartamentId"));
        DepartamentService departamentService = new DepartamentServiceImpl();
        if (departamentService.deleteDepartament(id) == 0) {
            request.setAttribute("errorName", "Department is in use ");
        }
        CompanyNameService companyNameService = new CompanyNameServiceImpl();
        List<CompanyNameBean> companyNameBeanList = companyNameService.getAllCompanyNames();
        List<DepartamentBean> departamentBeanList = departamentService.getAllDepartament(null);
        request.setAttribute("companyNameList", companyNameBeanList);
        request.setAttribute("departaments", departamentBeanList);
        return "/pages/manageDepartament.jsp";
    }

}
