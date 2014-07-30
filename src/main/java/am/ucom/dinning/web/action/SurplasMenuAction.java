package am.ucom.dinning.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.CreateMenuService;
import am.ucom.dinning.service.impl.CreateMenuServiceImpl;
import am.ucom.dinning.service.impl.CreateSurplusMenuServiceImpl;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.SurplusMenuBean;
import am.ucom.dinning.web.model.SurplusMenuItemsBean;

/**
 * Action for Surplus Menu
 * @author nadya
 *
 */
public class SurplasMenuAction extends BaseAction{
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String returnPage = null;
		String id = request.getParameter("editProductId");
		Calendar cal = GregorianCalendar.getInstance(Locale.getDefault());
		DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		Date todaysDate = cal.getTime();
		String date = simpleDate.format(todaysDate);
		
		CreateSurplusMenuServiceImpl createSurplusMenuService = new CreateSurplusMenuServiceImpl();
		SurplusMenuBean surplusMenuBean = new SurplusMenuBean();
		CreateMenuService menuService = new CreateMenuServiceImpl();
		
		if(createSurplusMenuService.getSurplusMenuBeanList() == null && id == null) {
			ProductServiceImpl productServiceImpl = new ProductServiceImpl();
			
			List<ProductsBean> productsBeanList= productServiceImpl.getCurrentDateAllBoughtAndMenuProducts();
			
			requestMeasurements(request);
			request.setAttribute("addedProdList", productsBeanList);
				
			returnPage = "/pages/surplusMenu.jsp";
		} else {
			
			List<ProductsBean> prodList = createSurplusMenuService.getProductBySurplusMenu(Long.parseLong(id));
				
			List<SurplusMenuItemsBean> surplusMenuItemsBeans = createSurplusMenuService.getSurplusMenuItemsById(Long.parseLong(id));
				
			request.setAttribute("menu", surplusMenuItemsBeans);
			
			request.setAttribute("addedProdList", prodList);
			
			returnPage = "/pages/editSurplusMenu.jsp";
		}
		
		return returnPage;
	}

}
