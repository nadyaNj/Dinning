package am.ucom.dinning.web.action;

import am.ucom.dinning.service.ProductTypeService;
import am.ucom.dinning.service.impl.ProductTypeServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.ProductTypeBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UpdateTypeAction extends BaseAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String returnPage = "/pages/type.jsp";
        ProductTypeService productTypeService = new ProductTypeServiceImpl();
        ProductTypeBean productTypeBean = new ProductTypeBean(request);
        productTypeBean.setId(Long.parseLong(request.getParameter("id")));
        if (!StringUtil.isEmptyString(productTypeBean.getType()) &&
                isNameUnique(productTypeBean.getType(), productTypeService.getTypeNames(productTypeBean.getId()))) {
            productTypeService.updateType(productTypeBean);
        } else {
            request.setAttribute("errorName", "Invalid type OR type exists");
            returnPage = "/pages/type.jsp";
        }
        request.setAttribute("editType", productTypeBean);
        int typeCount;
        Integer pageNumber = 0;
        String pageNumberReq = request.getParameter("pageNumber");
        if (pageNumberReq != null) {
            pageNumber = Integer.parseInt(pageNumberReq);
        }

        typeCount = productTypeService.getProductTypeCount();
        List<ProductTypeBean> type = productTypeService.getAllProductTypeListbyPage(pageNumber);
        request.setAttribute("type", type);
        int count;
        if ((double) typeCount % 10 != 0) {
            count = (typeCount / 10) + 1;
        } else {
            count = typeCount / 10;
        }
        request.setAttribute("count", count);
        request.setAttribute("typeCount", typeCount);
        return returnPage;
    }

}
