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
 * Action Class for menu viewing
 * @author aram
 *
 */
public class MenuViewAction extends BaseAction{
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		Long menuId = Long.parseLong(request.getParameter("menuId"));
		CreateMenuService menuService = new CreateMenuServiceImpl();
		MenuBean menuBean = menuService.getMenuById(menuId);
		
		ProductService prodService = new ProductServiceImpl();
		List<ProductsBean> prodList = new ArrayList<ProductsBean>();
		for (MenuItemsBean menuItemsBean: menuBean.getMenuItemsBeanList()){
			ProductsBean prodBean = prodService.getProductById(menuItemsBean.getProductId());
			prodList.add(prodBean);
		}
		DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		String dat = simpleDate.format(menuBean.getMenuActualDate());
		
		request.setAttribute("addedProdList", prodList);
		request.setAttribute("menuDate", dat);
		request.setAttribute("menuId", menuId);
		request.setAttribute("menu", menuBean);
		
		return "pages/menuPreview.jsp";
	}

}
