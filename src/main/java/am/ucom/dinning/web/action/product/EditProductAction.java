package am.ucom.dinning.web.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.web.model.ProductsBean;

/**
 * the EditAction class extends BaseAction
 * @author arthur
 *
 */
public class EditProductAction extends ProductAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {		
		ProductService productService = new ProductServiceImpl();
		ProductsBean productBean = productService.getProductById(Long.parseLong(request.getParameter(Constants.EDIT_PRODUCT_ID_FLAG)));		
		request.setAttribute("edit", productBean);
		requestTypes(request);
		requestMeasurements(request);
		beanType(productBean.getProductTypes(), request);
		return "/pages/product/createProductInputs.jsp";
	}
}
