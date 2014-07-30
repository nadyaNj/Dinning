package am.ucom.dinning.web.model;

import java.util.Date;

/**
 * @author aram
 */
public class MenuItemsBean extends BaseBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * default constructor
     */
    public MenuItemsBean() {

    }

    private Long menuId;

    private Long productId;

    private int productCount;

    private boolean active;

    private Date creationDate;

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
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


}
