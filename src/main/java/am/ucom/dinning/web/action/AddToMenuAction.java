package am.ucom.dinning.web.action;

import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.web.model.ProductsBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Action class for adding products to menu
 *
 * @author aram
 */
public class AddToMenuAction extends BaseAction {
    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        String productId = null;
        ProductsBean prodBean = new ProductsBean();
        List<ProductsBean> prodList = new ArrayList<ProductsBean>();
        productId = request.getParameter(Constants.ADD_PRODUCT_ID_FLAG);
        ProductService prodService = new ProductServiceImpl();
        prodBean = prodService.getProductById(Long.valueOf(productId));
        prodList.add(prodBean);
        request.setAttribute("addedProdList", prodList);

        return "/pages/menuProd.jsp";
    }

}
