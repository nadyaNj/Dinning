package am.ucom.dinning.web.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SharedBasketBean extends BaseBean {

    /**
     * UUID for serialization
     */
    private static final long serialVersionUID = 1L;

    private Long cashierId;

    private Date paymentDate;

    private BigDecimal paymentTotal;

    private List<SharedBasketItemBean> sharedBasketItemBeanList;

    public SharedBasketBean() {
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(BigDecimal paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public List<SharedBasketItemBean> getSharedBasketItemBeanList() {
        return sharedBasketItemBeanList;
    }

    public void setSharedBasketItemBeanList(List<SharedBasketItemBean> sharedBasketItemBeanList) {
        this.sharedBasketItemBeanList = sharedBasketItemBeanList;
    }
}
