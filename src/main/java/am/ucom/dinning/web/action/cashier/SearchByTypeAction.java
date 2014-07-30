package am.ucom.dinning.web.action.cashier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.web.action.BaseAction;
import am.ucom.dinning.web.model.ProductsBean;
 

/**
 * extends CashierAction
 * @author arthur
 *
 */
public class SearchByTypeAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) {
		String typeId = request.getParameter("searchTypeId");
		List<ProductsBean> listProduct = new ProductServiceImpl().searchByType(typeId);
		request.setAttribute("addedProdList", listProduct);
		return "/pages/cashier/cashierTodayMenu.jsp";
	}

}
