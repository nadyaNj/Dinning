package am.ucom.dinning.web.action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import am.ucom.dinning.web.model.ProductSearchCriteriaBean;

/**
 * search product action
 * @author arthur
 */
public class SearchProductAction extends ProductAction {

    /*
     * (non-Javadoc)
     * @see am.edu.web.action.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {    	
    	ProductSearchCriteriaBean productSearchCriteriaBean = new ProductSearchCriteriaBean();
    	productSearchCriteriaBean.initIstance(request);
    	Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber").trim()); 
    	HttpSession session = request.getSession();    
        session.setAttribute("beanList", productSearchCriteriaBean);
        requestPaginationParameters(request, pageNumber);        
        return "/pages/product/searchResult.jsp";        
    }       
}