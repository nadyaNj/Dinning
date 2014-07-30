package am.ucom.dinning.web.action.cashier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.web.action.BaseAction;

/**
 * This class for show today menu.
 * @author arthur
 *
 */
public class ShowTodayManuCashierAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		showTodayMenu(request);
		return "/pages/cashier/cashierTodayMenu.jsp";
	}

}
