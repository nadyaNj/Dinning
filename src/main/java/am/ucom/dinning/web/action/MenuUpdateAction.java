package am.ucom.dinning.web.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import am.ucom.dinning.service.CreateMenuService;
import am.ucom.dinning.service.impl.CreateMenuServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.MenuItemsBean;
import am.ucom.dinning.web.model.RequestPage;

/**
 * Action class for menu update
 * @author aram
 *
 */
public class MenuUpdateAction extends BaseAction {
	
	public static final Logger LOG = LoggerFactory.getLogger(MenuSaveAction.class);
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String dat = request.getParameter("menuDate");
		
		DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDate.parse(dat);
		} catch (ParseException e) {
			LOG.error("String parsing to Date error :" + e.getMessage(), e);
		}
		List<String> prodIds= StringUtil.arrayToArrayList((request.getParameterValues(("menuProdIds"))));
		
		CreateMenuService menuService = new CreateMenuServiceImpl();
		List<MenuItemsBean> menuItemsList = new ArrayList<MenuItemsBean>();
		MenuBean menuBean = menuService.getMenuByDate(date);
		
		for (int i=0; i < prodIds.size(); i++) {
			MenuItemsBean menuItemsBean = new MenuItemsBean();
			menuItemsBean.setMenuId(menuBean.getId());
			menuItemsBean.setProductId(Long.valueOf(prodIds.get(i)));
			menuItemsList.add(menuItemsBean);
		}
		menuBean.setMenuItemsBeanList(menuItemsList);
		menuService.updateMenu(menuBean);
		
		
		RequestPage<MenuBean> menuRequestPage = new RequestPage<MenuBean>();
		if (!StringUtil.isEmptyString(request.getParameter("pageNumber"))) {
			Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			menuRequestPage = menuService.getAllMenus(pageNumber-1);
		} else {
			menuRequestPage = menuService.getAllMenus(0);
		}
		
		request.setAttribute("page", menuRequestPage);
		
		return "pages/menuList.jsp";
	}

}
