package am.ucom.dinning.web.action.cashier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.service.impl.UserServiceImpl;
import am.ucom.dinning.web.action.BaseAction;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.UserBean;
/**
 * This class for get by code.
 * @author arthur
 *
 */
public class GetByCodeAction extends BaseAction{
	
	public static final Logger LOG = LoggerFactory.getLogger(GetByCodeAction.class);
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code").trim();
		if(!code.isEmpty() && code.length() < 8 && isValidCode(code)) {
			ProductsBean productBean = new ProductServiceImpl().searchByCode(code);
			if(productBean == null) {
				request.setAttribute("errorMessage", "wrong code");
			} else {
				request.setAttribute("product", productBean);
			}
		} else if(code.length() > 7){
			UserBean userBean = new UserServiceImpl().getUserByCode(code);
			if(userBean != null) {
				HttpSession session = request.getSession();
				session.setAttribute("getUser", userBean);
			} else {
				request.setAttribute("errorMessage", "wrong code");
			}
		} else {
			request.setAttribute("errorMessage", "please insert code");
		}
		return "/pages/cashier/addProductByCode.jsp";
	}
	
	/**
     * This method for check code validation
     * @param code - String 
     * @return boolean 
     */
    private boolean isValidCode(String code) {
    	try {
    		Integer.parseInt(code);
    		return true;
    	} catch(NumberFormatException e) {
    		LOG.error("invalid code" + e.getMessage());
    		return false;
    	}    	
    } 
}
