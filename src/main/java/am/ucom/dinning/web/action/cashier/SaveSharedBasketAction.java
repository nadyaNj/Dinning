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
import am.ucom.dinning.web.model.SharedBasketBean;
import am.ucom.dinning.web.model.SharedBasketItemBean;
/**
 * This class for save basket .
 * @author arthur
 *
 */
public class SaveSharedBasketAction extends BaseAction{
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Long cashierId = Long.parseLong(request.getParameter("cashierId").trim());
		String paymentTotal = request.getParameter("total").trim();
		BigDecimal total = new BigDecimal(paymentTotal);
		Date paymentDate = new Date();
		
		if(paymentTotal != null) {
			SharedBasketBean sharedBasketBean = new SharedBasketBean();
			BasketService basketService = new BasketServiceImpl();
			
			sharedBasketBean.setCashierId(cashierId);
			sharedBasketBean.setPaymentDate(paymentDate);
			sharedBasketBean.setPaymentTotal(total);
			sharedBasketBean.setSharedBasketItemBeanList(getBasketItemBeanList(request));
			
			basketService.saveSharedBasket(sharedBasketBean);
			
			HttpSession session = request.getSession();
			session.setAttribute("getUser", null);
		}
		return "/pages/cashier/cashierHead.jsp";
	}
	
	/**
	 * This method for get basket items bean list
	 * @param request - HttpServletRequest
	 * @return sharedBasketItemBeanList - List<SharedBasketItemBean>
	 */
	private List<SharedBasketItemBean> getBasketItemBeanList(HttpServletRequest request) {
		String [] prIdes = request.getParameterValues("ids[]");
		String [] prPrices = request.getParameterValues("prices[]");
		
		List<SharedBasketItemBean> sharedBasketItemBeanList = new ArrayList<SharedBasketItemBean>();
		for (int i = 0; i < prIdes.length; i++) {
			SharedBasketItemBean sharedBasketItemBean = new SharedBasketItemBean();
			BigDecimal productPrice = new BigDecimal(prPrices[i]);
			sharedBasketItemBean.setItemPrice(productPrice);
			sharedBasketItemBean.setProductId(Long.parseLong(prIdes[i]));
			sharedBasketItemBeanList.add(sharedBasketItemBean);
		}
		return sharedBasketItemBeanList;
	}
}
