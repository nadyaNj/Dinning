package am.ucom.dinning.service;

import java.util.Date;
import java.util.List;

import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.RequestPage;

/**
 *Menu create service
 * @author arthur
 *
 */
public interface CreateMenuService {
	
	/**
	 * for save menu.
	 * @param menuBean
	 * @return id - Long: saved menu id
	 */
	Long saveMenu(MenuBean menuBean);

	/**
	 * Get All Menus
	 * @return  List<MenuBean>
	 */
	RequestPage<MenuBean> getAllMenus(Integer pageNumber);
	
	/**
	 * This method for get products by id.
	 * @param menuBean - MenuBean instance
	 * @return prodList - List<ProductsBean>
	 */
	List<ProductsBean> getProductByMenu(MenuBean menuBean);
	
	/**
	 * This method return MenuBean by given actual date
	 * @param date - Date
	 * @return MenuBean
	 */
	MenuBean getMenuByDate(Date date);
	
	/**
	 * This method return Menu by given menu id
	 * @param id - Long
	 * @return MenuBean
	 */
	MenuBean getMenuById(Long id);
	
	/**
	 * This method for update menu
	 * @param menuBean
	 * @return
	 */
	int updateMenu(MenuBean menuBean);
}
