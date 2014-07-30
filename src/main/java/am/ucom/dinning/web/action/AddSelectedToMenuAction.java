package am.ucom.dinning.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.impl.ProductServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.ProductsBean;

/**
 * Action class for adding selected products to menu
 * @author aram
 *
 */
public class AddSelectedToMenuAction extends BaseAction{
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<String> prodIds= StringUtil.arrayToArrayList((request.getParameterValues(("menuProdIds"))));
		ProductService prodService = new ProductServiceImpl();
		List<ProductsBean> prodList = new ArrayList<ProductsBean>();
		for(int i=0; i<prodIds.size(); i++) {
			ProductsBean prodBean = prodService.getProductById(Long.valueOf(prodIds.get(i)));
			prodList.add(prodBean);
		}
		request.setAttribute("addedProdList", prodList);
		
		return "/pages/menuProd.jsp";
	}

}
