package am.ucom.dinning.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.service.DepartamentService;
import am.ucom.dinning.service.impl.CompanyNameServiceImpl;
import am.ucom.dinning.service.impl.DepartamentServiceImpl;
import am.ucom.dinning.util.StringUtil;
import am.ucom.dinning.web.model.CompanyNameBean;
import am.ucom.dinning.web.model.DepartamentBean;
/**
 * This class for manage departament.
 * @author arthur
 *
 */
public class ManageDepartamentAction extends BaseAction{
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.web.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		DepartamentBean departamentBean = new DepartamentBean();
		DepartamentService departamentService = new DepartamentServiceImpl();
		String departamentName = request.getParameter("departamentName").trim();  
		String compId = request.getParameter("id");
		Long companyId = (long) 0;
		if(compId != null){
			companyId = Long.parseLong(compId);
		}
		departamentBean.setDepartamentName(departamentName);
		departamentBean.setCompanyId(companyId);
		if(!StringUtil.isEmptyString(departamentName) && compId != null && departamentService.isDepartmentUnique(departamentBean) == null){
			departamentService.saveDepartament(departamentBean);
		} else {
			request.setAttribute("errorName","Invalid name or unique");
		}
		CompanyNameService companyNameService = new CompanyNameServiceImpl();
		List<CompanyNameBean> companyNameBeanList = companyNameService.getAllCompanyNames();
		List<DepartamentBean> departamentBeanList = departamentService.getAllDepartament(null);
		request.setAttribute("bean",departamentBean);
		request.setAttribute("companyNameList", companyNameBeanList);
		request.setAttribute("departaments",departamentBeanList);
		return "/pages/manageDepartament.jsp";
	}

}
