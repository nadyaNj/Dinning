package am.ucom.dinning.web.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.util.Constants;

/**
 * extends ProductAction
 * @author arthur
 *
 */
public class ShowProductAction extends ProductAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String returnPage = null;
		requestMeasurements(request);
		requestTypes(request);
		beanType(null, request);
		requestPaginationParameters(request, 0);
		String pg = request.getParameter("pageFlag");
		if(pg.equals(Constants.SHOW_CREATE_PAGE)) {
    		returnPage = "/pages/product/createProduct.jsp";
    	} else if(pg.equals(Constants.WELCOME_PAGE_FLAG)) {
    		returnPage = "/pages/product/search.jsp";
    	}
		return returnPage;
	}

}
