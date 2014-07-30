package am.ucom.dinning.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action class for menu create page
 * @author aram
 *
 */
public class MenuAction extends BaseAction {
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		 
    	requestTypes(request);
    	requestPagingMenu(request, 0);
        
        return "/pages/createMenu.jsp";
        }
	}
	

