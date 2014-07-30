package am.ucom.dinning.web.model;

import java.util.Date;
import java.util.List;
/**
 * 
 * @author aram
 *
 */
public class MenuBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * default constructor
	 */
	public MenuBean() {
		
	}
	private Date menuActualDate;
	
	private Long menuUsersId;
	
	private String menuUserName;
	
	private List<MenuItemsBean> menuItemsBeanList;
	
	
	
	/**
	 * @return the menuActualDate
	 */
	public Date getMenuActualDate() {
		return menuActualDate;
	}

	/**
	 * @param menuActualDate the menuActualDate to set
	 */
	public void setMenuActualDate(Date menuActualDate) {
		this.menuActualDate = menuActualDate;
	}

	/**
	 * @return the menuUsersId
	 */
	public Long getMenuUsersId() {
		return menuUsersId;
	}

	/**
	 * @param menuUsersId the menuUsersId to set
	 */
	public void setMenuUsersId(Long menuUsersId) {
		this.menuUsersId = menuUsersId;
	}

	

	/**
	 * @return the menuItemsBeanList
	 */
	public List<MenuItemsBean> getMenuItemsBeanList() {
		return menuItemsBeanList;
	}

	/**
	 * @param menuItemsBeanList the menuItemsBeanList to set
	 */
	public void setMenuItemsBeanList(List<MenuItemsBean> menuItemsBeanList) {
		this.menuItemsBeanList = menuItemsBeanList;
	}

	/**
	 * @return the menuUserName
	 */
	public String getMenuUserName() {
		return menuUserName;
	}

	/**
	 * @param menuUserName the menuUserName to set
	 */
	public void setMenuUserName(String menuUserName) {
		this.menuUserName = menuUserName;
	}

}
