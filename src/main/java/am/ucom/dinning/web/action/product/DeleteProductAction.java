package am.ucom.dinning.web.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;

/**
 * the DeleteAction class extends BaseAction
 * @author arthur
 */
public class DeleteProductAction extends ProductAction {
		
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) {		
		ProductService productService = new ProductServiceImpl();
		Long deleteProductId = Long.parseLong(request.getParameter("deleteProductId"));
    	//delete by id if returned -1 the product use in menu
    	if(productService.deleteProductById(deleteProductId) == -1) {
    		request.setAttribute("deleteError", "prodtuc use in menu");
    	}
    	Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber")) - 1;
    	requestPaginationParameters(request, pageNumber);	
		return "/pages/product/searchResult.jsp";
	}
}
