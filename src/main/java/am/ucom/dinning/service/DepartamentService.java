package am.ucom.dinning.service;

import java.util.List;

import am.ucom.dinning.web.model.DepartamentBean;
/**
 * Departament service interface.
 * @author arthur
 *
 */
public interface DepartamentService {
	
	/**
	 * This method for save Department .
	 * @param departament - DepartamentBean
	 * @return id - Long
	 */
	Long saveDepartament(DepartamentBean departament); 
	
	/**
	 * Get all Departments.
	 * @param  id - Long
	 * @return Department List - List<DepartamentBean>
	 */
	List<DepartamentBean> getAllDepartament(Long id);
	
	/**
	 * This method for delete department.
	 * @param id - Long
	 * @return id - int
	 */
	int deleteDepartament(Long id);
	
	/**
	 * This method for update department.
	 * @param departament - DepartamentBean
	 * @return id - int
	 */
	int updateDepartament(DepartamentBean departament);
	
	/**
	 * This method for get department by Id.
	 * @param id - Long
	 * @return department bean - DepartamentBean
	 */	
	DepartamentBean getDepartamentById(Long id);
	
	/**
	 * Get departamenBean list by company id	
	 * @param id - Long
	 * @return Department bean list - List<DepartamentBean>
	 */
	List<DepartamentBean> getDepartamentByCompanyId(Long id);
	
	/**
	 * Get department by department id
	 * @param id - Long
	 * @return department list - List<DepartamentBean> 
	 */
	List<DepartamentBean> getDepartamentByDepartamentId(Long id);
	
	/**
	 * This method for check unique or no.
	 * @param department - DepartamentBean
	 * @return department name - String 
	 */
	String isDepartmentUnique(DepartamentBean department);
}