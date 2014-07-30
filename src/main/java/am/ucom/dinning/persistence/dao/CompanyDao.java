package am.ucom.dinning.persistence.dao;

import am.ucom.dinning.persistence.domain.CompanyDomain;

/**
 * Company Dao interface.
 * @author arthur
 *
 */
public interface CompanyDao extends GenericDao<CompanyDomain> {
	
	String COMPANY_TABLE_NAME = " company ";
	
	String COMPANY_NAME = " name ";
	
	String COMPANY_NAME_ID = " id ";
	
	String COMPANY_NAME_FOREGIN_ID = " company_id ";
	
	String DEPARTAMENT_TABLE_NAME = " department ";
	
	/**
	 * This method for check name unique or no 
	 * @param company - CompanyDomain
	 * @return Company name - String
	 */
	String isNameUnique(CompanyDomain company);
}