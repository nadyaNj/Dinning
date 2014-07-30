package am.ucom.dinning.web.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * extends ProductAction
 * @author arthur
 *
 */
public class ShowWithNumberProductAction extends ProductAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber")) - 1;
		requestPaginationParameters(request, pageNumber);
		return "/pages/product/searchResult.jsp";
	}

}
