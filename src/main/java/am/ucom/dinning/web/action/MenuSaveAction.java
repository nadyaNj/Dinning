package am.ucom.dinning.web.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import am.ucom.dinning.service.CreateMenuService;
import am.ucom.dinning.service.impl.CreateMenuServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.MenuItemsBean;
import am.ucom.dinning.web.model.UserBean;
/**
 * Action class for menu saving
 * @author aram
 *
 */
public class MenuSaveAction extends BaseAction{
	
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
				
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("user");
		List<String> prodIds= StringUtil.arrayToArrayList((request.getParameterValues(("menuProdIds"))));
				
		MenuBean menuBean = new MenuBean();
		List<MenuItemsBean> menuItemsList = new ArrayList<MenuItemsBean>();
		CreateMenuService menuService = new CreateMenuServiceImpl();
		
		for (int i=0; i < prodIds.size(); i++){
			MenuItemsBean menuItemsBean = new MenuItemsBean();
			menuItemsBean.setProductId(Long.valueOf(prodIds.get(i)));
			menuItemsList.add(menuItemsBean);
		}
		menuBean.setMenuUsersId(userBean.getId());
		menuBean.setMenuActualDate(date);
		menuBean.setMenuItemsBeanList(menuItemsList);
		menuService.saveMenu(menuBean);
		requestTypes(request);
		requestPagingMenu(request, 0);
		
		return "/pages/createMenu.jsp";
				
	}

}
