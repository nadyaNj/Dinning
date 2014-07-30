package am.ucom.dinning.web.action;

import am.ucom.dinning.service.ProductTypeService;
import am.ucom.dinning.service.impl.ProductTypeServiceImpl;
import am.ucom.dinning.web.model.ProductTypeBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * the  class get type id and deletet this type
 *
 * @author arthur
 */
public class DeleteTypeAction extends BaseAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        ProductTypeService deleteService = new ProductTypeServiceImpl();
        ProductTypeService typeService = new ProductTypeServiceImpl();
        Long typeId = Long.parseLong(request.getParameter("typeId"));
        if (deleteService.deleteType(typeId) == 0) {
            request.setAttribute("errorMessage", "This type is in use");
        }
        Integer pageNumber = 0;//= Integer.parseInt(request.getParameter("pagenumber"));
        String pageNumberReq = request.getParameter("pageNumber");
        if (pageNumberReq != null) {
            pageNumber = Integer.parseInt(pageNumberReq);
        }

        typeService.deleteType(typeId);
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
        return "/pages/type.jsp";
    }

}
