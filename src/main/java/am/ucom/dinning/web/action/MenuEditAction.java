package am.ucom.dinning.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.CreateMenuService;
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.CreateMenuServiceImpl;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.MenuItemsBean;
import am.ucom.dinning.web.model.ProductsBean;

/**
 * Action Class for menu edit page
 * @author aram
 *
 */
public class MenuEditAction extends BaseAction {
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Long menuId = Long.parseLong(request.getParameter("menuId"));
		CreateMenuService menuService = new CreateMenuServiceImpl();
		MenuBean menuBean = menuService.getMenuById(menuId);
		DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		String dat = simpleDate.format(menuBean.getMenuActualDate());
		
		StringBuilder stringBuilder = new StringBuilder();
		List<ProductsBean> prodList = new ArrayList<ProductsBean>();
		ProductService prodService = new ProductServiceImpl();
		for (MenuItemsBean menuItemsBean: menuBean.getMenuItemsBeanList()){
			ProductsBean prodBean = prodService.getProductById(menuItemsBean.getProductId());
			prodList.add(prodBean);
			stringBuilder.append(menuItemsBean.getProductId().toString()).append(",");
		}
				
		request.setAttribute("editMenuId", menuId);
		request.setAttribute("menuDate", dat);
		request.setAttribute("menu", menuBean);
		request.setAttribute("addedProdList", prodList);
		requestTypes(request);
		request.setAttribute("searchFilter", stringBuilder.toString());
		requestPagingMenu(request, 0);
		
		return "/pages/createMenu.jsp";
	}
}
