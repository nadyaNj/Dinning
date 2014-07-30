package am.ucom.dinning.persistence.dao;


import java.util.List;

import am.ucom.dinning.persistence.domain.DepartamentDomain;
/**
 * Department Dao interface.
 * @author arthur
 *
 */
public interface DepartamentDao extends GenericDao<DepartamentDomain>{
	
	String DEPARTAMENT_NAME = " name ";
	
	String DEPARTAMENT_NAME_ID = " id ";
	
	String COMPANY_NAME_FOREGIN_ID = " company_id ";
	
	String DEPARTAMENT_TABLE_NAME = " department ";
	
	String DEPARTAMENT_FOREGIN_ID = " usr_dep_id ";
	
	String USERS_TABLE_NAME = " users ";
	
	/**
	 * Get all departments
	 * @param id - Long
	 * @return domainList - List<DepartamentDomain>
	 */
	List<DepartamentDomain> getAllDepartaments(Long id);
	
	/**
	 * This method for check department unique or no
	 * @param  department - DepartamentDomain
	 * @return department name - String
	 */
	String isDepartmentUnique(DepartamentDomain department);
	
	/**
	 * This method get departments by company id
	 * @param id - Long
	 * @return departmentDomain - List<DepartamentDomain>
	 */
	List<DepartamentDomain> getDepartamentsByCompany(Long id);
	
	/**
	 * Get departments by department id
	 * @param id - Long 
	 * @return departmentList - List<DepartamentDomain>
	 */
	List<DepartamentDomain> getDepartamentsByDepartmentId(Long id);
}