package am.ucom.dinning.web.action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.ServiceProperties;
import am.ucom.dinning.service.CreateMenuService;
import am.ucom.dinning.service.MeasurementService;
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.ProductTypeService;
import am.ucom.dinning.service.impl.CreateMenuServiceImpl;
import am.ucom.dinning.service.impl.MeasurementServiceImpl;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.service.impl.ProductTypeServiceImpl;
import am.ucom.dinning.util.FileUtil;
import am.ucom.dinning.web.model.MeasurementBean;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.ProductSearchCriteriaBean;
import am.ucom.dinning.web.model.ProductTypeBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.RequestPage;


/**
 * Base class for all other Actgion classes.
 * All Action classes must extend this class.
 *
 * @author arthur
 */
public abstract class BaseAction {

    /**
     * All instances of this class have to overwrite this method...
     * @param response
     * @return
     */
    abstract public String execute(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * request Measurement
	 * @param request
	 */
	protected void requestMeasurements(HttpServletRequest request) {
		MeasurementService measurementService = new MeasurementServiceImpl();
    	List<MeasurementBean> measurement = measurementService.getMeasurementBeanList();
		request.setAttribute("measurement", measurement);
	}
	
	/**
	 * request types
	 * @param request
	 */
	protected void requestTypes(HttpServletRequest request) {
		List<ProductTypeBean> type  = new ProductTypeServiceImpl().getAllProductTypeList();
    	request.setAttribute("type", type);
    	request.setAttribute("size", type.size());
	}
	
	/**
	 * method for getting bean type
	 * @param ids
	 * @param request
	 */
	protected void beanType(String[] ids, HttpServletRequest request) {
		ProductTypeService productTypeService = new ProductTypeServiceImpl();
    	List<ProductTypeBean> type = productTypeService.getAllProductTypeList();
    	
		if(ids != null) {			
			for(String id:ids) {
				int i = 0;
				for(ProductTypeBean productTypeBean: type){
					if(Long.parseLong(id) == productTypeBean.getId()) {
						type.remove(i);
						break;
					}
					i ++;
				}
			}
		}
		request.setAttribute("noSelectTypes", type);
	}
	
	/**
     * check name
     * @param name
     * @return boolean
     */
    protected boolean haveName(String name) {
    	boolean boolName = true;
    	if(name == null || name.equals("")) {
    		return false;
    	}     	
    	return boolName;
    }
    
    /**
     * check name unique id unique return true 
     * else return false
     * @param name
     * @param names
     * @return
     */
    protected boolean isNameUnique(String name, List<String> names) {
    	boolean boolName = true;
    	for(String entityName : names) {
    		if(name.equals(entityName)) {
    			boolName = false;
    			break;
    		}
    	}
    	return boolName;
    }
    
    /**
     * method for products searching with pagination in menu create page
     * @param request
     * @param pageNumber
     */
    protected void requestPagingMenu(HttpServletRequest request, Integer pageNumber) {
    	ProductSearchCriteriaBean prodSearch = new ProductSearchCriteriaBean();
		prodSearch.initIstance(request);
		prodSearch.setPriceMax(prodSearch.getPriceMin());
		String str = null;
		
		if (request.getParameter("pageFlag").equals("editMenuFlag")) {
			str = (String) request.getAttribute("searchFilter");
		} else {
			str = request.getParameter("searchFilter");
		}
				
		prodSearch.setSearchFilterIds(str);
		
		ProductService productService = new ProductServiceImpl();
		requestMeasurements(request);
    	RequestPage<ProductsBean> page = productService.productSearchBySelectedCriteria(prodSearch, pageNumber);
    	String imgBaseDir = ServiceProperties.getImageSavePath();	
    	if(imgBaseDir.endsWith(File.separator)) {
    		imgBaseDir = imgBaseDir.substring(0, imgBaseDir.length() - 1);
    	}       
    	try {
    		for(ProductsBean product : page.getProductList()) {
    			File imgFile = new File(imgBaseDir + File.separator + product.getImgUrl());
                String tmpPath = request.getSession().getServletContext().getRealPath("/") + File.separator + "tmp";
                boolean dirExist = new File(tmpPath).exists();
                if(!dirExist) {
                	dirExist = new File(tmpPath).mkdir();
                }

                if(dirExist && imgFile.exists()) {
                	File tmpImgFile = new File(tmpPath  + File.separator + product.getImgUrl());
                	FileUtil.fileCopy(imgFile, tmpImgFile);
                }
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("page", page);
		
	}

    /**
     * show today menu
     * @param HttpServletRequest - request
     */
    protected void showTodayMenu(HttpServletRequest request) {
    	
		Calendar cal = GregorianCalendar.getInstance(Locale.getDefault());
		DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		Date todaysDate = cal.getTime();
		String date = simpleDate.format(todaysDate);
		CreateMenuService menuService = new CreateMenuServiceImpl();
		requestTypes(request);
		MenuBean todayMenuBean = menuService.getMenuByDate(todaysDate);
		if(todayMenuBean.getId() != null) {
			List<ProductsBean> prodList = menuService.getProductByMenu(todayMenuBean);
			requestMeasurements(request);
			request.setAttribute("menu", todayMenuBean);
			request.setAttribute("menuDate", date);
			request.setAttribute("addedProdList", prodList);
		} else {
			request.setAttribute("errorTodayMenu", "Today menu was'nt create ");
		}
	}
}