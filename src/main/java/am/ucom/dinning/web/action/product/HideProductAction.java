package am.ucom.dinning.web.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.Constants;

/**
 * the HideAction class extends BaseAction
 * @author arthur
 *
 */
public class HideProductAction extends ProductAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		Integer hideFlag; //variable for give flag
		
		ProductService productService = new ProductServiceImpl();
		//get hidden product id

		//check hidden false or true
		if(request.getParameter(Constants.HIDDEN_FLAG).equals("false")){
			hideFlag = 1;
		} else {
			hideFlag = 0;
		}
		//hidden product with id
		productService.hideProductService(request.getParameter(Constants.HIDE_PRODUCT_ID_FLAG).trim(), hideFlag);
		//call private method
		Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber")) - 1;
    	requestPaginationParameters(request, pageNumber);
				
		return "/pages/product/searchResult.jsp";
	}
}