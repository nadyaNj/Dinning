package am.ucom.dinning.web.action;

import am.ucom.dinning.service.ProductTypeService;
import am.ucom.dinning.service.impl.ProductTypeServiceImpl;
import am.ucom.dinning.web.model.ProductTypeBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * the class show type page
 *
 * @author arthur
 */
public class ShowTypePageAction extends BaseAction {

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //requestTypes(request);

        int typeCount;
        Integer pageNumber = 0;//= Integer.parseInt(request.getParameter("pagenumber"));
        String pageNumberReq = request.getParameter("typePageNumber");
        if (pageNumberReq != null) {
            pageNumber = Integer.parseInt(pageNumberReq);
        }
        ProductTypeService productTypeService = new ProductTypeServiceImpl();
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


        return "/pages/type.jsp";
    }

}
