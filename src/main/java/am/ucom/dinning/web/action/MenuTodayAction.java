package am.ucom.dinning.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This class for show Today menu.
 * @author arthur
 *
 */
public class MenuTodayAction extends BaseAction{
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response) {
		showTodayMenu(request);
		requestTypes(request);
		return "/pages/todayMenu.jsp";
	}
}
