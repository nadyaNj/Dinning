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

import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.MenuItemsBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.UserBean;

/**
 * Action class for menu creating cancel
 * @author aram
 *
 */
public class MenuCancelAction extends BaseAction{
	
	public static final Logger LOG = LoggerFactory.getLogger(MenuCancelAction.class);
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		MenuBean menuBean = new MenuBean();
		String dat = request.getParameter("menuDate");
		List<MenuItemsBean> menuItemsList = new ArrayList<MenuItemsBean>();
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
				
		List<ProductsBean> prodList = new ArrayList<ProductsBean>();
		ProductService prodService = new ProductServiceImpl();
		for (int i=0; i < prodIds.size(); i++){
			MenuItemsBean menuItemsBean = new MenuItemsBean();
			menuItemsBean.setProductId(Long.valueOf(prodIds.get(i)));
			menuItemsList.add(menuItemsBean);
			ProductsBean prodBean = prodService.getProductById(Long.valueOf(prodIds.get(i)));
			prodList.add(prodBean);
		}
		menuBean.setMenuUsersId(userBean.getId());
		menuBean.setMenuActualDate(date);
		menuBean.setMenuItemsBeanList(menuItemsList);
		request.setAttribute("menuDate", dat);
		request.setAttribute("menu", menuBean);
		request.setAttribute("addedProdList", prodList);
		requestTypes(request);
		requestPagingMenu(request, 0);
		
		return "/pages/createMenu.jsp";
		
	}

}
