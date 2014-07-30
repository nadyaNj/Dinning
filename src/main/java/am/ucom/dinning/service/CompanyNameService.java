package am.ucom.dinning.service;

import java.util.List;

import am.ucom.dinning.web.model.CompanyNameBean;

/**
 * Company Name Service interface.
 * @author arthur
 *
 */
public interface CompanyNameService {
	
	/**
	 * This method for save Company name.
	 * @param companyName - CompanyNameBean object instance
	 * @return Saved company ID - Long
	 */
	Long saveCompanyName(CompanyNameBean companyName); 
	
	/**
	 * Get all company names
	 * @return Company Bean list - List<CompanyNameBean>
	 */
	List<CompanyNameBean> getAllCompanyNames();
	
	/**
	 * This method for delete Company name.
	 * @param id - Long
	 * @return deleted company ID - int
	 */
	int deleteCompanyName(Long id);
	
	/**
	 * This method for update company name.
	 * @param companyName - CompanyNameBean object instance
	 * @return company ID - int
	 */
	int updateCompanyName(CompanyNameBean companyName);
	
	/**
	 * This method for give company id and company name
	 * @param company - CompanyNameBean object instance
	 * @return company name - String
	 */
	String isCompanyUnique(CompanyNameBean company);
}