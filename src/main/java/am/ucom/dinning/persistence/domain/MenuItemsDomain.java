package am.ucom.dinning.persistence.domain;

import java.sql.Timestamp;

/**
 * Menu Items Domain
 *
 * @author nadya
 */
public class MenuItemsDomain extends BaseDomain {

    /**
     * Default UUID for serialization...
     */
    private static final long serialVersionUID = 1L;

    /**
     * public constructor
     */
    public MenuItemsDomain() {

    }

    private Long menuId;

    private Long productId;

    private int productCount;

    private boolean active;

    private Timestamp creationDate;

    /**
     * @return the menuId
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * @return the productId
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return the productCount
     */
    public int getProductCount() {
        return productCount;
    }

    /**
     * @param productCount the productCount to set
     */
    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the creationDate
     */
    public Timestamp getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
