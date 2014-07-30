package am.ucom.dinning.web.model;

import java.math.BigDecimal;

public class EmployeeBasketItemBean extends BaseBean {

    /**
     * UUID for serialization
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal itemPrice;

    private BigDecimal itemDiscountPrice;

    private Long productId;

    public EmployeeBasketItemBean() {
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getItemDiscountPrice() {
        return itemDiscountPrice;
    }

    public void setItemDiscountPrice(BigDecimal itemDiscountPrice) {
        this.itemDiscountPrice = itemDiscountPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
