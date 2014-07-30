/**
 *
 */
package am.ucom.dinning.web.action;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.UserService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.CompanyNameBean;
import am.ucom.dinning.web.model.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author siranush
 */
public class UpdateUserAction extends BaseAction {
    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(HttpServletRequest, HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        String returnPage = "/pages/user.jsp";
        UserService userService = new UserServiceImpl();

        UserBean userBean = new UserBean();
        userBean.setId(Long.parseLong(request.getParameter("id")));
        userBean.setUserName(request.getParameter("username"));
        userBean.setUserEmail(request.getParameter("userEmail"));
        userBean.setUserDepId(request.getParameter("userDepId"));
        userBean.setDiscountCode(request.getParameter("dicountCode"));
        userBean.setUserPosition(request.getParameter("userPosition"));

        if (!StringUtil.isEmptyString(userBean.getUserName())) {
            userService.update(userBean);
        } else {
            request.setAttribute("errorName", "Invalid name and name unique");

        }
        CompanyNameService companyNameService = new CompanyNameServiceImpl();
        List<CompanyNameBean> companyNameBeanList = companyNameService.getAllCompanyNames();
        if (request.getParameter("compid") != null) {
            Long compId = Long.parseLong(request.getParameter("compid"));
            for (CompanyNameBean companyNameBean : companyNameBeanList) {
                if (compId.longValue()  == companyNameBean.getId().longValue() ) {
                    request.setAttribute("departaments", companyNameBean.getDepartmentsId());
                    break;
                }
            }
        }
        Integer pageNumber = 0;//= Integer.parseInt(request.getParameter("pagenumber"));
        String pageNumberReq = request.getParameter("typePageNumber");
        if (pageNumberReq != null) {
            pageNumber = Integer.parseInt(pageNumberReq);
        }

        int count;
        int userCount = userService.getUsersCount();
        if ((double) userCount % 10 != 0) {
            count = (userCount / 10) + 1;
        } else {
            count = userCount / 10;
        }


        request.setAttribute("companyNameList", companyNameBeanList);
        List<UserBean> users = userService.getUsersByNumber(pageNumber);
        request.setAttribute("users", users);
        request.setAttribute("count", count);
        request.setAttribute("userCount", userCount);
        return returnPage;
    }

}
