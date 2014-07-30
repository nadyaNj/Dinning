package am.ucom.dinning.web.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.StringUtil;

/**
 * the DeleteAllAction class extends BaseAction
 * @author arthur
 *
 */
public class DeleteAllProductAction extends ProductAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) {		
		String[] ids = request.getParameterValues(("delProductIds"));
		ProductService productService = new ProductServiceImpl(); 
		List<String> deleteIds = StringUtil.arrayToArrayList(ids);
		// get list ids use products 
		List<String> usedProducts = productService.usedProducts(deleteIds);
		deleteIds.removeAll(usedProducts);
		productService.batchDeleteById(deleteIds);
		Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber")) - 1;
    	request.setAttribute("noDelete", usedProducts);
		requestPaginationParameters(request, pageNumber);
		return "/pages/product/searchResult.jsp";
	}

}
