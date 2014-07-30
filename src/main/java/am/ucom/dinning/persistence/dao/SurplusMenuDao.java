/**
 * 
 */
package am.ucom.dinning.persistence.dao;

import java.util.Date;
import java.util.List;

import am.ucom.dinning.persistence.domain.SurplusMenuDomain;
import am.ucom.dinning.persistence.domain.SurplusMenuItemsDomain;

/**
 * @author siranush
 *
 */
public interface SurplusMenuDao extends GenericDao<SurplusMenuDomain> {

	String SURPLUS_MENU_ACTUAL_DATE = " sm_menu_actual_date ";

	String SURPLUS_MENU_ID = " sm_id ";

	String SURPLUS_MENU_ITEMS_MENU_ID = "smi_menu_id ";

	String SURPLUS_MENU_USERS_ID = " sm_users_id ";

	String SURPLUS_MENU_PRODUCT_ID = " smi_product_id ";

	String SURPLUS_MENU_PRODUCT_COUNT = " smi_product_count ";

	String SURPLUS_MENU_ITEMS_CREATION_DATE = " smi_creation_date ";

	String SURPLUS_MENU_TABLE = " surplus_menu ";

	String SURPLUS_MENU_ITEMS_TABLE = " surplus_menu_items ";

	/**
	 * Get surplus menu by given date
	 * 
	 * @param date - Date
	 * @return surplusMenuDomain - instance of SurplusMenuDomain class
	 */
	SurplusMenuDomain getSurplusMenuByDate(Date date);
	
	/**
	 * get surplus menu domain list
	 * 
	 * @return List<SurplusMenuDomain> - list of SurplusMenuDomain class
	 */
	List<SurplusMenuDomain> getSurplusMenuDomainList();
	
	
	List<SurplusMenuItemsDomain> getSurplusMenuItemsDomainListById(Long id);

}
