package am.ucom.dinning.persistence.domain;

import java.util.Date;
import java.util.List;


/**
 * Menu Domain
 *
 * @author nadya
 */
public class MenuDomain extends BaseDomain {

    /**
     * Default UUID for serialization...
     */
    private static final long serialVersionUID = 1L;

    /**
     * public constructor
     */
    public MenuDomain() {

    }


    private Date menuActualDate;

    private Long menuUsersId;

    private List<MenuItemsDomain> menuItemsDomainList;

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
     * @return the menuItemsDomainList
     */
    public List<MenuItemsDomain> getMenuItemsDomainList() {
        return menuItemsDomainList;
    }

    /**
     * @param menuItemsDomainList the menuItemsDomainList to set
     */
    public void setMenuItemsDomainList(List<MenuItemsDomain> menuItemsDomainList) {
        this.menuItemsDomainList = menuItemsDomainList;
    }
}
