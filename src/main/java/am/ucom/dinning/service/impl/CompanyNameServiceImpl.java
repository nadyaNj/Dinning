package am.ucom.dinning.service.impl;

import java.util.ArrayList;
import java.util.List;

import am.ucom.dinning.persistence.dao.CompanyDao;
import am.ucom.dinning.persistence.dao.impl.CompanyDaoImpl;
import am.ucom.dinning.persistence.domain.CompanyDomain;
import am.ucom.dinning.persistence.domain.DepartamentDomain;
import am.ucom.dinning.service.CompanyNameService;
import am.ucom.dinning.web.model.CompanyNameBean;
import am.ucom.dinning.web.model.DepartamentBean;
/**
 * This class implements Company Name Service interface.
 * @author arthur
 *
 */
public class CompanyNameServiceImpl implements CompanyNameService {
	
	private CompanyDao companyDao;
	
	/**
	 * Public constructor
	 */
	public CompanyNameServiceImpl(){
		companyDao = new CompanyDaoImpl();
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CompanyNameService#saveCompanyName(am.ucom.dinning.web.model.CompanyNameBean)
	 */
	@Override
	public Long saveCompanyName(CompanyNameBean companyName) {
		CompanyDomain companyDomain = new CompanyDomain();
		companyDomain.setCompanyName(companyName.getCompanyName());
		return companyDao.save(companyDomain);
	}
	
	/**
	 * Initialize List<CompanyDomain> to List<CompanyNameBean> 
	 * @param domainList - List<CompanyDomain> 
	 * @return beanList - List<CompanyNameBean> 
	 */
	private List<CompanyNameBean> initListBeanByDomainList(List<CompanyDomain> domainList) {
		List<CompanyNameBean> beanList = new ArrayList<CompanyNameBean>();
		for(CompanyDomain domain: domainList) {
			beanList.add(initCompanyNameBeanByCompanyDomain(domain));
		}
		return beanList;
	}
	
	/**
	 * Initialize CompanyNameBean to CompanyDomain 
	 * @param  companyDomain - CompanyDomain
	 * @return companyBean - CompanyNameBean
	 */
	private CompanyNameBean initCompanyNameBeanByCompanyDomain(CompanyDomain companyDomain) {
		CompanyNameBean companyBean = new CompanyNameBean();
		companyBean.setId(companyDomain.getId());
		companyBean.setCompanyName(companyDomain.getCompanyName());
		companyBean.setDepartmentsId(initDepListBeanByDepDomainList(companyDomain.getDepartmentsId()));
		return companyBean;		
	}
	/**
	 * Initialize List<CompanyDomain> to List<CompanyNameBean> 
	 * @param domainList - List<DepartamentDomain>
	 * @return beanList - List<DepartamentBean>
	 */
	private List<DepartamentBean> initDepListBeanByDepDomainList(List<DepartamentDomain> domainList) {
		List<DepartamentBean> beanList = new ArrayList<DepartamentBean>();
		for(DepartamentDomain domain: domainList) {
			beanList.add(initDepartamentBeanByDepartamentDomain(domain));
		}
		return beanList;
	}
	
	/**
	 * Initialize DepartamentBean to DepartamentDomain 
	 * @param departamentDomain - DepartamentDomain
	 * @return departamentBean - DepartamentBean
	 */
	private DepartamentBean initDepartamentBeanByDepartamentDomain(DepartamentDomain departamentDomain) {
		DepartamentBean departamentBean = new DepartamentBean();
		departamentBean.setId(departamentDomain.getId());
		departamentBean.setDepartamentName((departamentDomain.getDepartamentName()));
		departamentBean.setCompanyId(departamentDomain.getCompanyId());
		return departamentBean;		
	}
	
	/**
	 * This method for init domain by bean.
	 * @param companyName - CompanyNameBean
	 * @return companyDomain - CompanyDomain
	 */
	private CompanyDomain initCompanyDomainByCompanyBean(CompanyNameBean companyName){
		CompanyDomain companyDomain = new CompanyDomain();
		companyDomain.setId(companyName.getId());
		companyDomain.setCompanyName(companyName.getCompanyName());
		return companyDomain;
	}
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CompanyNameService#getAllCompanyNames()
	 */
	@Override
	public List<CompanyNameBean> getAllCompanyNames() {
		return 	initListBeanByDomainList(companyDao.getAll());	
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CompanyNameService#deleteCompanyName(long)
	 */
	@Override
	public int deleteCompanyName(Long id) {
		return companyDao.delete(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CompanyNameService#updateCompanyName(am.ucom.dinning.web.model.CompanyNameBean)
	 */
	@Override
	public int updateCompanyName(CompanyNameBean companyName) {
		return	companyDao.update(initCompanyDomainByCompanyBean(companyName));
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CompanyNameService#isCompanyUnique(am.ucom.dinning.web.model.CompanyNameBean)
	 */
	@Override
	public String isCompanyUnique(CompanyNameBean company) {
		return companyDao.isNameUnique(initCompanyDomainByCompanyBean(company));
	}
}