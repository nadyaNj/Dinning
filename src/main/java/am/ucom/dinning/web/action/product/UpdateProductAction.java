package am.ucom.dinning.web.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.web.model.ProductsBean;

/**
 * updated product by id
 * @author arthur
 *
 */
public class UpdateProductAction extends ProductAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		ProductsBean updateProduct = (ProductsBean)request.getAttribute("createProduct");
		ProductService service = new ProductServiceImpl();
		List<String> errors = isValid(updateProduct);
		if(!errors.isEmpty()) {			
			request.setAttribute("errors", errors);
			beanType(updateProduct.getProductTypes(), request);
			request.setAttribute("edit", updateProduct);
		} else {
			service.updateProduct(updateProduct);
			beanType(null, request);
		}
		requestTypes(request);
		requestMeasurements(request);
		requestPaginationParameters(request, 0);		
		return "/pages/product/viewThenCreate.jsp";
	}
}
