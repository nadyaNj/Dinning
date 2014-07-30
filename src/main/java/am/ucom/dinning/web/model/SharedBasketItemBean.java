package am.ucom.dinning.web.model;

import java.math.BigDecimal;

public class SharedBasketItemBean {

    /**
     * UUID for serialization
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal itemPrice;

    private Long productId;

    public SharedBasketItemBean() {
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
