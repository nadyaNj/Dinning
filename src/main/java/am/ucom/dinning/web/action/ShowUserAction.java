package am.ucom.dinning.web.action;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.DepartamentService;
import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.service.impl.DepartamentServiceImpl;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.web.model.CompanyNameBean;
import am.ucom.dinning.web.model.DepartamentBean;
import am.ucom.dinning.web.model.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * the class create type
 *
 * @author arthur
 */
public class ShowUserAction extends BaseAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserServiceImpl();
        CompanyNameService companyNameService = new CompanyNameServiceImpl();
        List<CompanyNameBean> companyNameBeanList = companyNameService.getAllCompanyNames();
        if (request.getParameter("depid") != null) {
            DepartamentService departamentService = new DepartamentServiceImpl();
            DepartamentBean departamentBean = departamentService.getDepartamentById(Long.parseLong(request.getParameter("depid")));
            for (CompanyNameBean companyNameBean : companyNameBeanList) {
                if (departamentBean.getCompanyId().longValue() == companyNameBean.getId().longValue()) {
                    request.setAttribute("departament", departamentBean);
                    request.setAttribute("companyName", companyNameBean);
                    return "/pages/user.jsp";
                }
            }
        }


        Integer pageNumber = 0;//= Integer.parseInt(request.getParameter("pagenumber"));
        String pageNumberReq = request.getParameter("typePageNumber");
        if (pageNumberReq != null) {
            pageNumber = Integer.parseInt(pageNumberReq);
        }


        //List<UserBean> users = userService.getUsersByNumber(pageNumber);

        int count;
        int userCount = userService.getUsersCount();
        if ((double) userCount % 10 != 0) {
            count = (userCount / 10) + 1;
        } else {
            count = userCount / 10;
        }


        if (request.getParameter("compid") != null) {
        	Long compId = Long.parseLong(request.getParameter("compid"));
            for (CompanyNameBean companyNameBean : companyNameBeanList) {
                if (compId.longValue()  == companyNameBean.getId().longValue() ) {
                    request.setAttribute("departaments", companyNameBean.getDepartmentsId());
                    break;
                }
            }
        }

        request.setAttribute("companyNameList", companyNameBeanList);

        List<UserBean> users = userService.getUsersByNumber(pageNumber);
        request.setAttribute("users", users);

        request.setAttribute("count", count);
        request.setAttribute("userCount", userCount);


        return "/pages/user.jsp";
    }

}
