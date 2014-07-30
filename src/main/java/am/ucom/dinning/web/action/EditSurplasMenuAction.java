
package am.ucom.dinning.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.CreateMenuService;
import am.ucom.dinning.service.CreateSurplusMenuService;
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.CreateMenuServiceImpl;
import am.ucom.dinning.service.impl.CreateSurplusMenuServiceImpl;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.SurplusMenuBean;
import am.ucom.dinning.web.model.SurplusMenuItemsBean;

/**
 * 
 * @author nadya
 *
 */
public class EditSurplasMenuAction extends BaseAction{

	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("editProductId");
		Calendar cal = GregorianCalendar.getInstance(Locale.getDefault());
		DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		Date todaysDate = cal.getTime();
		String date = simpleDate.format(todaysDate);
		CreateSurplusMenuServiceImpl createSurplusMenuService = new CreateSurplusMenuServiceImpl();
		SurplusMenuBean surplusMenuBean = new SurplusMenuBean();
		
		
			CreateMenuService menuService = new CreateMenuServiceImpl();
			//MenuBean todayMenuBean = menuService.getMenuByDate(todaysDate);
			//getProductBySurplusMenu
			List<ProductsBean> prodList = createSurplusMenuService.getProductBySurplusMenu(Long.parseLong(id));
			
			List<SurplusMenuItemsBean> surplusMenuItemsBeans = createSurplusMenuService.getSurplusMenuItemsById(Long.parseLong(id));
			
			requestMeasurements(request);
			request.setAttribute("menu", surplusMenuItemsBeans);
			//request.setAttribute("menuId", surplusMenuBean.getId());
			
			request.setAttribute("addedProdList", prodList);
		
		return "/pages/editSurplusMenu.jsp";
	}

}
