package am.ucom.dinning.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * extends BaseAction action for menu producs search pagination
 * @author aram
 *
 */
public class MenuPagingAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber")) - 1;
		requestPagingMenu(request, pageNumber);
		
		return "/pages/searchResultMenu.jsp";
	}

}
