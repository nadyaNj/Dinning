package am.ucom.dinning.web.action;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.web.model.CompanyNameBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class for delete  company name
 *
 * @author arthur
 */
public class DeleteCompanyNameAction extends BaseAction {
    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        CompanyNameService companyNameService = new CompanyNameServiceImpl();
        Long companyNameId = Long.parseLong(request.getParameter("deleteCompanyNameId"));
        if (companyNameService.deleteCompanyName(companyNameId) == 0) {
            request.setAttribute("errorName", "This Company name is in use");
        }
        List<CompanyNameBean> companyNameBeanList = companyNameService.getAllCompanyNames();
        request.setAttribute("companyNameList", companyNameBeanList);
        return "/pages/manageCompanyPage.jsp";
    }

}