package am.ucom.dinning.web.action.cashier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import am.ucom.dinning.service.BasketService;
import am.ucom.dinning.service.impl.BasketServiceImpl;
import am.ucom.dinning.web.action.BaseAction;
import am.ucom.dinning.web.model.EmployeeBasketBean;
import am.ucom.dinning.web.model.EmployeeBasketItemBean;
/**
 * This class for save employee basket
 * @author arthur
 *
 */
public class SaveEmployeeBasketAction extends BaseAction{
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Integer paymentTypeCode = Integer.parseInt(request.getParameter("paymentTypeCode").trim());
		Long cashierId = Long.parseLong(request.getParameter("cashierId").trim());
		String paymentTotal = request.getParameter("total").trim();
		BigDecimal total = new BigDecimal(paymentTotal);
		
		Long userId = Long.parseLong(request.getParameter("userId").trim());
		Date paymentDate = new Date();
		
		if(paymentTotal != null) {
			EmployeeBasketBean employeeBasketBean = new EmployeeBasketBean();
			BasketService employeeBasketService = new BasketServiceImpl();
			
			employeeBasketBean.setPaymentTypeCode(paymentTypeCode);
			employeeBasketBean.setCashierId(cashierId);
			employeeBasketBean.setPaymentTotal(total);
			employeeBasketBean.setPaymentDate(paymentDate);
			employeeBasketBean.setEmployeeId(userId);
			employeeBasketBean.setItemsList(getEmployeeBasketBeanList(request));
			
			employeeBasketService.saveEmployeeBasket(employeeBasketBean);
			
			HttpSession session = request.getSession();
			session.setAttribute("getUser", null);
		}
		return "/pages/cashier/cashierHead.jsp";
	}
	
	/**
	 * This method for get employee basket bean list
	 * @param request - HttpServletRequest
	 * @return employeeBasketItemBeanList - List<EmployeeBasketItemBean>
	 */
	private List<EmployeeBasketItemBean> getEmployeeBasketBeanList(HttpServletRequest request) {
		String [] prIdes = request.getParameterValues("ids[]");
		String [] prDescountPrices = request.getParameterValues("prices[]");
		String [] prPrices = request.getParameterValues("disPrices[]");
		
		List<EmployeeBasketItemBean> employeeBasketItemBeanList = new ArrayList<EmployeeBasketItemBean>();
		for (int i = 0; i < prIdes.length; i++) {
			EmployeeBasketItemBean employeeBasketItemBean = new EmployeeBasketItemBean();
			
			BigDecimal productPrice = new BigDecimal(prPrices[i]);
			BigDecimal productDescountPrice = new BigDecimal(prDescountPrices[i]);
			
			employeeBasketItemBean.setItemPrice(productPrice);
			employeeBasketItemBean.setItemDiscountPrice(productDescountPrice);
			employeeBasketItemBean.setProductId(Long.parseLong(prIdes[i]));
			employeeBasketItemBeanList.add(employeeBasketItemBean);
		}
		return employeeBasketItemBeanList;
	}
}
