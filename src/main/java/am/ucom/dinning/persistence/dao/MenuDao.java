package am.ucom.dinning.persistence.dao;

import java.util.Date;

import am.ucom.dinning.persistence.domain.MenuDomain;
import am.ucom.dinning.persistence.domain.MenuItemsDomain;
import am.ucom.dinning.persistence.domain.ProductSearchResult;

/**
 * menu Dao interface
 * @author aram
 *
 */
public interface MenuDao extends GenericDao<MenuDomain> {
	
	String MENU_ACTUAL_DATE = " menu_actual_date ";
	
	String MENU_ID = " id ";
	
	String MENU_ITEMS_MENU_ID = " menu_id ";
	
	String MENU_USERS_ID = " users_id ";
	
	String MENU_NAME = " menu_name ";
	
	String MENU_PRODUCT_ID = " product_id ";
	
	String MENU_PRODUCT_COUNT = " product_count ";
	
	String MENU_ITEM_ACTIVE = " active ";
	
	String MENU_CREATION_DATE = " creation_date ";
	
	String MENU_TABLE = " menu ";
	
	String MENU_ITEMS_TABLE = " menu_items ";
	
	/**
	 * Get menu by given date
	 * @param date - Date
	 * @return menuDomain - MenuDomain
	 */
	MenuDomain getMenuByDate(Date date);
	
	/**
	 * Get all Menus by paging
	 * @param pageNumber
	 * @return
	 */
	ProductSearchResult<MenuDomain> getAll(Integer pageNumber);
	
	
}

   
