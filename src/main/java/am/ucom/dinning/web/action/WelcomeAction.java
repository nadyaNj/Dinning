package am.ucom.dinning.web.action;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.DepartamentService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.service.impl.DepartamentServiceImpl;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.web.model.CompanyNameBean;
import am.ucom.dinning.web.model.DepartamentBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class for set types on request.
 *
 * @author arthur
 */
public class WelcomeAction extends BaseAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        String returnPage = null;
        requestMeasurements(request);
        requestTypes(request);
        beanType(null, request);
        CompanyNameService companyNameService = new CompanyNameServiceImpl();
        List<CompanyNameBean> companyNameBeanList = companyNameService.getAllCompanyNames();
        request.setAttribute("companyNameList", companyNameBeanList);
        String pg = request.getParameter("pageFlag");
        if (pg == null || pg.equals(Constants.SHOW_CREATE_PAGE)) {
            returnPage = "/pages/createProduct.jsp";
        } else if (pg.equals(Constants.WELCOME_PAGE_FLAG)) {
            returnPage = "/pages/search.jsp";
        } else if (pg.equals(Constants.CREATE_MENU_PAGE_VIEW)) {
            returnPage = "/pages/createMenu.jsp";
        } else if (pg.equals(Constants.MANAGE_COMPANY_NAME)) {
            returnPage = "/pages/manageCompanyPage.jsp";
        } else if (pg.equals(Constants.MANAGE_DEPARTAMENT)) {
            DepartamentService departamentService = new DepartamentServiceImpl();
            List<DepartamentBean> departamentBeanList = departamentService.getAllDepartament(null);
            request.setAttribute("departaments", departamentBeanList);
            returnPage = "/pages/manageDepartament.jsp";
        }
        return returnPage;
    }

}
