package am.ucom.dinning.service.impl;

import java.util.ArrayList;
import java.util.List;

import am.ucom.dinning.persistence.dao.DepartamentDao;
import am.ucom.dinning.persistence.dao.impl.DepartamentDaoImpl;
import am.ucom.dinning.persistence.domain.DepartamentDomain;
import am.ucom.dinning.service.DepartamentService;
import am.ucom.dinning.web.model.DepartamentBean;
/**
 * This class implements Departament Service interface.
 * @author arthur
 *
 */
public class DepartamentServiceImpl implements DepartamentService {
	
	private DepartamentDao departamentDao;
	
	/**
	 * Public constructor.
	 */
	public DepartamentServiceImpl(){
		departamentDao = new DepartamentDaoImpl();
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.DepartamentService#saveDepartament(am.ucom.dinning.web.model.DepartamentBean)
	 */
	@Override
	public Long saveDepartament(DepartamentBean departament) {
		DepartamentDomain departamentDomain = new DepartamentDomain();
		departamentDomain.setDepartamentName(departament.getDepartamentName());
		departamentDomain.setCompanyId(departament.getCompanyId());
		return departamentDao.save(departamentDomain);
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.DepartamentService#getAllDepartament()
	 */
	@Override
	public List<DepartamentBean> getAllDepartament(Long id) {
		return initListBeanByDomainList(departamentDao.getAllDepartaments(id));
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.DepartamentService#deleteDepartament(java.lang.Long)
	 */
	@Override
	public int deleteDepartament(Long id) {
		return departamentDao.delete(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.DepartamentService#updateDepartament(am.ucom.dinning.web.model.DepartamentBean)
	 */
	@Override
	public int updateDepartament(DepartamentBean departament) {
		return	departamentDao.update( initDepartamentDomainByDepartamentBean(departament));
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.DepartamentService#getDepartamentById(java.lang.Long)
	 */
	@Override
	public DepartamentBean getDepartamentById(Long id) {
		return initDepartamentBeanByDepartamentDomain( departamentDao.getById(id));
	}
	/**
	 * Initialize List<CompanyDomain> to List<CompanyNameBean> 
	 * @param measurementDomainList
	 * @return List<MeasurementBean> 
	 */
	private List<DepartamentBean> initListBeanByDomainList(List<DepartamentDomain> domainList) {
		List<DepartamentBean> beanList = new ArrayList<DepartamentBean>();
		for(DepartamentDomain domain: domainList) {
			beanList.add(initDepartamentBeanByDepartamentDomain(domain));
		}
		return beanList;
	}
	
	/**
	 * Initialize Department Bean by Department Domain
	 * @param departamentDomain -  DepartamentDomain 
	 * @return departmentBean - DepartmentBean 
	 */
	private DepartamentBean initDepartamentBeanByDepartamentDomain(DepartamentDomain departamentDomain) {
		DepartamentBean departamentBean = new DepartamentBean();
		departamentBean.setId(departamentDomain.getId());
		departamentBean.setDepartamentName((departamentDomain.getDepartamentName()));
		departamentBean.setCompanyId(departamentDomain.getCompanyId());
		return departamentBean;		
	}
	
	/**
	 * This method for init domen by bean.
	 * @param departamentBean - DepartamentBean
	 * @return departamentDomain - DepartamentDomain
	 */
	private DepartamentDomain initDepartamentDomainByDepartamentBean(DepartamentBean departamentBean){
		DepartamentDomain departamentDomain = new DepartamentDomain();
		departamentDomain.setId(departamentBean.getId());
		departamentDomain.setDepartamentName((departamentBean.getDepartamentName()));
		departamentDomain.setCompanyId(departamentBean.getCompanyId());
		return departamentDomain;
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.DepartamentService#getDepartamentByCompanyId(java.lang.Long)
	 */
	@Override
	public List<DepartamentBean> getDepartamentByCompanyId(Long id) {
		return initListBeanByDomainList(departamentDao.getDepartamentsByCompany(id));		
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.DepartamentService#getDepartamentByDepartamentId(java.lang.Long)
	 */
	@Override
	public List<DepartamentBean> getDepartamentByDepartamentId(Long id) {
		return initListBeanByDomainList(departamentDao.getDepartamentsByDepartmentId(id));
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.DepartamentService#isDepartmentUnique(am.ucom.dinning.web.model.DepartamentBean)
	 */
	@Override
	public String isDepartmentUnique(DepartamentBean department) {
		return departamentDao.isDepartmentUnique(initDepartamentDomainByDepartamentBean(department));
	}
}