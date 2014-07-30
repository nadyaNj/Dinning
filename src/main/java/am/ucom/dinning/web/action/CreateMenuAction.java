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
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.CreateMenuServiceImpl;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.MenuItemsBean;
import am.ucom.dinning.web.model.ProductsBean;
/**
 * Action class for menu create
 * @author aram
 *
 */
public class CreateMenuAction extends BaseAction{

	public static final Logger LOG = LoggerFactory.getLogger(CreateMenuAction.class); 
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		MenuBean menuBean = new MenuBean();
		String returnPage = "/pages/menuPreview.jsp";
		List<MenuItemsBean> menuItemsList = new ArrayList<MenuItemsBean>();
		
		String dat = request.getParameter("menuDate");
		DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {			
			date = (Date)simpleDate.parse(dat);
		} catch (ParseException e) {
			LOG.error("String parsing to Date error :" + e.getMessage(), e);
		}
		
		if(!dateIsUnique(date)) {
			returnPage ="/pages/createMenu.jsp";
			requestTypes(request);
			requestPagingMenu(request, 0);
			String error = "You are already have Menu for this date";
			request.setAttribute("error", error);
		}
		List<String> prodIds= StringUtil.arrayToArrayList((request.getParameterValues(("menuProdIds"))));
		
		ProductsBean prodBean = new ProductsBean();
		List<ProductsBean> prodList = new ArrayList<ProductsBean>();
		ProductService prodService = new ProductServiceImpl();
				
		for(int i=0; i < prodIds.size(); i++){
			MenuItemsBean menuItemsBean = new MenuItemsBean();
			menuItemsBean.setProductId(Long.valueOf(prodIds.get(i)));
			menuItemsList.add(menuItemsBean);
			prodBean = prodService.getProductById(Long.valueOf(prodIds.get(i)));
			prodList.add(prodBean);
		}
		menuBean.setMenuItemsBeanList(menuItemsList);
		request.setAttribute("menuDate", dat);
		request.setAttribute("menu", menuBean);
		request.setAttribute("addedProdList", prodList);
		return returnPage;
	}
	
	/**
	 * Check is Date unique or not
	 * @param date
	 * @return
	 */
	private boolean dateIsUnique(Date date) {
		CreateMenuService menuService = new CreateMenuServiceImpl();
		MenuBean menuBean = menuService.getMenuByDate(date);
		boolean bool;
		if(menuBean.getId() != null) {
			bool = false;
		} else {
			bool = true;
		}
		return bool;
	}
}
