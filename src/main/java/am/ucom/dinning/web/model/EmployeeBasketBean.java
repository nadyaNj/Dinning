package am.ucom.dinning.web.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EmployeeBasketBean extends BaseBean {

    /**
     * UUID for serialization
     */
    private static final long serialVersionUID = 1L;

    private Long cashierId;

    private Long employeeId;

    private Integer paymentTypeCode;

    private Date paymentDate;

    private BigDecimal paymentTotal;

    private List<EmployeeBasketItemBean> itemsList;

    public EmployeeBasketBean() {
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(Integer paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
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

    public List<EmployeeBasketItemBean> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<EmployeeBasketItemBean> itemsList) {
        this.itemsList = itemsList;
    }
}
