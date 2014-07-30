package am.ucom.dinning.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.CreateMenuService;
import am.ucom.dinning.service.impl.CreateMenuServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.RequestPage;

/**
 * Action Class for Menu Edit page and menus list representation
 * @author aram
 *
 */

public class MenuListPageAction  extends BaseAction{
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		Calendar cal = GregorianCalendar.getInstance(Locale.getDefault());
		DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		Date todaysDate = cal.getTime();
		String date = simpleDate.format(todaysDate);
		CreateMenuService menuService = new CreateMenuServiceImpl();
		RequestPage<MenuBean> menuRequestPage = new RequestPage<MenuBean>();
		if (!StringUtil.isEmptyString(request.getParameter("pageNumber"))) {
			Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			menuRequestPage = menuService.getAllMenus(pageNumber-1);
		} else {
			menuRequestPage = menuService.getAllMenus(0);
		}
		
		request.setAttribute("page", menuRequestPage);
		request.setAttribute("todaysDate", date);
		return "pages/menuList.jsp";
	}
}
