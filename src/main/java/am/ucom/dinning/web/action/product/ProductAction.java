package am.ucom.dinning.web.action.product;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import am.ucom.dinning.ServiceProperties;
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.FileUtil;
import am.ucom.dinning.web.action.BaseAction;
import am.ucom.dinning.web.model.*;

/**
 * extends BaseAction and
 * adds validation methods
 * 
 * @author arthur
 */
public abstract class ProductAction extends BaseAction {

	public static final Logger LOG = LoggerFactory.getLogger(ProductAction.class);
	
	/**
	 * 
	 * @param productBean - ProductsBean
	 * @return List<String> - errors list
	 */
	protected List<String> isValid(ProductsBean productBean) {
		List<String> errors = new ArrayList<String>();
		// this if checked is name or no?
		if(!haveName(productBean.getName())) {
			errors.add("please insert product name");
		}
		// this if check price if inValid?
		if(!isNumber(productBean.getPrice(), productBean.getDiscountPrice())) {
			errors.add("invalid price or/and discount price");
		}		
		// this if check is code or no
		if(!isValidCode(productBean.getCode())) {
			errors.add("invalid code");
		}		
		// this id check product have measurement
		if(!isValidMeasurement(productBean.getMeasurementId())) {
			errors.add("no measurement");
		}		
		// this if check product have types
		if(!haveType(productBean.getProductTypes())) {
			errors.add("no sort or sorts");
		}		
		// check is name and code unique
		if(!isNameAndCodeUnique(productBean.getName(), productBean.getCode(),
				productBean.getId())) {
			errors.add("the name and code has be unique");
		}		
		// this if check image format
		if(!isImageUploaded(productBean.getImgUrl())) {
			errors.add("Please select a image file");
		}			
		return errors;
	}

	/**
	 * requested in page product by pageNumber, pageNumber and page counts
	 * @param request - HttpServletRequest
	 * @param pageNumber - Integer
	 */
	protected void requestPaginationParameters(HttpServletRequest request, Integer pageNumber) {
    	ProductService productService = new ProductServiceImpl();
    	HttpSession session = request.getSession();    
    	ProductSearchCriteriaBean search = (ProductSearchCriteriaBean) session.getAttribute("beanList");
    	RequestPage<ProductsBean> page;
    	if(search == null) {
    		page = productService.getProducts(pageNumber);
    	} else {
    		page = productService.productSearchBySelectedCriteria(search, pageNumber);
    	}  
    	imagesShow(page.getProductList(), request);
    	request.setAttribute("page", page);    	
    }
		
	/**
	 * 
	 * @param productList - List<ProductBean>
	 * @param request - HttpServletRequest
	 */
	private void imagesShow(List<ProductsBean> productList, HttpServletRequest request) {
		String imgBaseDir = ServiceProperties.getImageSavePath();	
    	if(imgBaseDir.endsWith(File.separator)) {
    		imgBaseDir = imgBaseDir.substring(0, imgBaseDir.length() - 1);
    	}       
    	try {
    		for(ProductsBean product : productList) {
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
    		LOG.error("imageShow IOException in ProductAction" + e.getMessage());
    	}    	
	}
	
    /**
     * check is price number
     * @param price - String
     * @return boolean
     */
    private boolean isNumber(String price, String discountPrice) {
    	int pr, discPr;
    	try {
    		pr = Integer.parseInt(price);
    		discPr = Integer.parseInt(discountPrice);
    	} catch(NumberFormatException e) {
    		LOG.error("invalid price or discount price" + e.getMessage());
    		return false;
    	}    	
    	return (discPr > 0) && (discPr <= pr);
    }
    
    /**
     * 
     * @param code - String
     * @return boolean
     */
    private boolean isValidCode(String code) {
    	if(code.length() > 7) {
    		return false;
    	}
    	try {
    		Integer.parseInt(code);
    		return true;
    	} catch(NumberFormatException e) {
    		LOG.error("invalid code" + e.getMessage());
    		return false;
    	}    	
    } 
    
    /**
     * 
     * @param measurement - String
     * @return boolean
     */
    private boolean isValidMeasurement(String measurement) {
    	return !(measurement == null || measurement.equals("0"));    	
    }  
    
    /**
     * check is select type
     * @param types - String[]
     * @return boolean
     */
    private boolean haveType(String[] types) {
    	return !(types == null || types[0].equals("0"));    	
    }
    
    /**
     * check is name and code unique
     * @param name - String
     * @param code - String
     * @param id - Long
     * @return boolean
     */
    protected boolean isNameAndCodeUnique(String name, String code, Long id) {
    	return new ProductServiceImpl().isNameAndCodeUnique(name, code, id);
    }
    
    /**
     * is image valid and is chosen any image
     * @param imageUrl - String
     * @return
     */
    private boolean isImageUploaded(String imageUrl) {
    	return !"NonImage".equals(imageUrl); 
    }
   
}
