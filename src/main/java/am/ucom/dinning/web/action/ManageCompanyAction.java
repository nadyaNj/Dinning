package am.ucom.dinning.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.CompanyNameBean;
/**
 * This class for manage company name.
 * @author arthur
 *
 */
public class ManageCompanyAction extends BaseAction {
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String companyName = request.getParameter("companyName").trim();
		CompanyNameBean companyNameBean = new CompanyNameBean();
		companyNameBean.setCompanyName(companyName);
		CompanyNameService companyNameService = new CompanyNameServiceImpl();
		if(!StringUtil.isEmptyString(companyNameBean.getCompanyName()) && companyNameService.isCompanyUnique(companyNameBean) == null) {
			companyNameService.saveCompanyName(companyNameBean);
		} else {
			request.setAttribute("errorName", "Invalid name or name was unique");
		}
		List<CompanyNameBean> companyNameBeanList = companyNameService.getAllCompanyNames();
		request.setAttribute("companyNameBean", companyNameBean);
		request.setAttribute("companyNameList", companyNameBeanList);
		return "/pages/manageCompanyPage.jsp";
	}

}