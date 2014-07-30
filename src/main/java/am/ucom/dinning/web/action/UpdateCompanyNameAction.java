package am.ucom.dinning.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.CompanyNameBean;

/**
 * This class for update company name.
 * @author arthur
 *
 */
public class UpdateCompanyNameAction extends BaseAction {
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String companyName = request.getParameter("companyName");
		Long id = Long.parseLong(request.getParameter("id"));
		CompanyNameBean companyNameBean = new CompanyNameBean();
		companyNameBean.setCompanyName(companyName);
		companyNameBean.setId(id);
		CompanyNameService companyNameService = new CompanyNameServiceImpl();
		if(!StringUtil.isEmptyString(companyNameBean.getCompanyName()) && companyNameService.isCompanyUnique(companyNameBean) == null) {
			companyNameService.updateCompanyName(companyNameBean);
		} else {
			request.setAttribute("errorNameUpdate", "Invalid name or unique");
		}
		List<CompanyNameBean> companyNameBeanList = companyNameService.getAllCompanyNames();
		request.setAttribute("companyNameBean",companyNameBean);
		request.setAttribute("companyNameList", companyNameBeanList);
		return "/pages/manageCompanyPage.jsp";
	}

}