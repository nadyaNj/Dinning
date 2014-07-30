package am.ucom.dinning.web.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.persistence.dao.SurplusMenuDao;
import am.ucom.dinning.persistence.dao.impl.SurplusMenuDaoImpl;
import am.ucom.dinning.persistence.domain.SurplusMenuDomain;
import am.ucom.dinning.persistence.domain.SurplusMenuItemsDomain;
import am.ucom.dinning.service.CreateMenuService;
import am.ucom.dinning.service.CreateSurplusMenuService;
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.CreateMenuServiceImpl;
import am.ucom.dinning.service.impl.CreateSurplusMenuServiceImpl;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.SurplusMenuBean;
import am.ucom.dinning.web.model.SurplusMenuItemsBean;



public class CreateSurplusMenuAction extends BaseAction {
	public static final Logger LOG = LoggerFactory.getLogger(CreateMenuAction.class); 
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<String> prodIds= StringUtil.arrayToArrayList((request.getParameterValues(("menuProdIds"))));
		List<String> prodCounts= StringUtil.arrayToArrayList((request.getParameterValues(("menuProdCounts"))));
		//Date menudate = new Date();//"2012-03-21";
		
		Calendar cal = GregorianCalendar.getInstance(Locale.getDefault());
		DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		Date todaysDate = cal.getTime();
		String date = simpleDate.format(todaysDate);
		
		if(prodIds != null && prodCounts != null) {
			SurplusMenuBean surplusMenuBean = new SurplusMenuBean();

			surplusMenuBean.setSurplusMenuActualDate(todaysDate);
			surplusMenuBean.setSurplusMenuUsersId(Long.parseLong("1"));

			surplusMenuBean.setSurplusMenuItemsBeanList(getSurplusMenuItemsBeanList(request));

			CreateSurplusMenuService createSurplusMenuService 	= new CreateSurplusMenuServiceImpl();
			
			createSurplusMenuService.saveSurplusMenu(surplusMenuBean);
			
		}
		
		CreateSurplusMenuServiceImpl createSurplusMenuService = new CreateSurplusMenuServiceImpl();
		
		request.setAttribute("todayMenu", createSurplusMenuService.getSurplusMenuBeanList());
		request.setAttribute("surplusMenu", createSurplusMenuService.getSurplusMenuBeanList());
		request.setAttribute("todayDate", date);
		
		return "/pages/surplusMenuList.jsp";
	}
	
	private List<SurplusMenuItemsBean> getSurplusMenuItemsBeanList(HttpServletRequest request) {
		List<SurplusMenuItemsBean> surplusMenuItemsBeanList = new ArrayList<SurplusMenuItemsBean>();
		List<String> prodIds = StringUtil.arrayToArrayList((request.getParameterValues(("menuProdIds"))));
		List<String> prodCounts = StringUtil.arrayToArrayList((request.getParameterValues(("menuProdCounts"))));
	
		ProductsBean productsBean = new ProductsBean();
		List<ProductsBean> productsBeanList = new ArrayList<ProductsBean>();
		ProductService productService = new ProductServiceImpl();
	
		for (int i = 0; i < prodIds.size(); i++) {
			SurplusMenuItemsBean surplusMenuItemsBean = new SurplusMenuItemsBean();
			
			surplusMenuItemsBean.setSurplusProductId(Long.valueOf(prodIds.get(i)));
			surplusMenuItemsBean.setSurplusProductCount(Integer.parseInt(prodCounts.get(i)));
			surplusMenuItemsBeanList.add(surplusMenuItemsBean);
		}
	
		return surplusMenuItemsBeanList;
	
	}
}
