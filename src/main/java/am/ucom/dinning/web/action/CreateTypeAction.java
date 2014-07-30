package am.ucom.dinning.web.action;

import am.ucom.dinning.service.ProductTypeService;
import am.ucom.dinning.service.impl.ProductTypeServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.ProductTypeBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * the class create type
 *
 * @author arthur
 */
public class CreateTypeAction extends BaseAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductTypeService typeService = new ProductTypeServiceImpl();
        ProductTypeBean typeBean = new ProductTypeBean(request);

        if (!StringUtil.isEmptyString(typeBean.getType()) && isNameUnique(typeBean.getType(), typeService.getTypeNames(null))) {
            typeService.createProductType(typeBean);
        } else {
            request.setAttribute("errorName", "Invalid name and name unique");

        }

        Integer pageNumber = 0;//= Integer.parseInt(request.getParameter("pagenumber"));
        String pageNumberReq = request.getParameter("typePageNumber");
        if (pageNumberReq != null) {
            pageNumber = Integer.parseInt(pageNumberReq);
        }

        int typeCount = typeService.getProductTypeCount();
        List<ProductTypeBean> type = typeService.getAllProductTypeListbyPage(pageNumber);
        request.setAttribute("type", type);
        int count;
        if ((double) typeCount % 10 != 0) {
            count = (typeCount / 10) + 1;
        } else {
            count = typeCount / 10;
        }
        request.setAttribute("count", count);
        request.setAttribute("typeCount", typeCount);
        //requestTypes(request);
        return "/pages/type.jsp";
    }
}
