package am.ucom.dinning.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Product search action in menu create page
 * @author aram
 *
 */
public class MenuProductSearchAction extends BaseAction{
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String returnPage = "/pages/searchResultMenu.jsp";
				
		Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber").trim());
		requestPagingMenu(request, pageNumber);
		return returnPage;
	}
		
}