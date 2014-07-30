package am.ucom.dinning.web.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.web.model.ProductsBean;

/**
 * the create action class
 * @author arthur
 *
 */
public class CreateProductAction extends ProductAction {

	/* 
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		ProductsBean createProduct = (ProductsBean)request.getAttribute("createProduct");
		ProductService service = new ProductServiceImpl();
		List<String> errors = isValid(createProduct);
		if(!errors.isEmpty()) {			
			request.setAttribute("errors", errors);
			beanType(createProduct.getProductTypes(), request);
			request.setAttribute("edit", createProduct);
		} else {
			service.saveProduct(createProduct);
			beanType(null, request);
		}
		requestTypes(request);
		requestMeasurements(request);
		requestPaginationParameters(request, 0);		
		return "/pages/product/viewThenCreate.jsp";
	}

}
