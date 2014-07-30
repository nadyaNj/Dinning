/*
 * EmployeeBasket		1.0 03/12/12 4:16 PM
 *
 * Copyright (c) UCom.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of UCom.
 * ("Confidential Information").  You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with UCom.
 */

package am.ucom.dinning.persistence.domain;

import am.ucom.dinning.persistence.annotation.CollectionOption;
import am.ucom.dinning.persistence.annotation.LazyCollectionOption;
import am.ucom.dinning.persistence.dao.impl.EmployeeBasketDaoImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Employee basket domain class.
 *
 * @author Hayk Hayryan
 * @version 1.0 03/12/12 4:16 PM
 */
public class EmployeeBasket extends BaseDomain {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;


    private Long cashierId;

    private Long userId;

    private Integer paymentTypeCode;

    private Date paymentDate;

    private BigDecimal paymentTotal;

    @CollectionOption(LazyCollectionOption.TRUE)
    private List<EmployeeBoughtItem> boughtItems;

    /**
     * Public constructor
     */
    public EmployeeBasket() {
    }

    /**
     * Cashier ID getter
     *
     * @return Long - cashier id.
     */
    public Long getCashierId() {
        return cashierId;
    }

    /**
     * Cashier ID setter
     *
     * @param cashierId - Long cashier id
     */
    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    /**
     * User ID getter
     *
     * @return Long - user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * User ID setter
     *
     * @param userId - Long
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Payment type code getter
     *
     * @return Integer - returns payment type code. Can be following values: 0 - cash payment, 1 - credit payment.
     */
    public Integer getPaymentTypeCode() {
        return paymentTypeCode;
    }

    /**
     * Payment type code setter
     *
     * @param paymentTypeCode - Integer values for indicating payment type
     */
    public void setPaymentTypeCode(Integer paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    /**
     * Payment date getter
     *
     * @return Date - payment date
     */
    public Date getPaymentDate() {
        return paymentDate;
    }


    /**
     * Payment date setter
     *
     * @param paymentDate - Date of payment
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Total payment getter
     *
     * @return BigDecimal - payment total value.
     */
    public BigDecimal getPaymentTotal() {
        return paymentTotal;
    }

    /**
     * Total payment setter
     *
     * @param paymentTotal - BigDecimal: total payment value
     */
    public void setPaymentTotal(BigDecimal paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    /**
     * Basket's sold products list getter.
     *
     * @return List<EmployeeBoughtItems> - sold products items list.
     */
    public List<EmployeeBoughtItem> getBoughtItems() {

        if(boughtItems == null && getId() != null) {
            boughtItems = EmployeeBasketDaoImpl.fetchCollectionField(EmployeeBasket.class, getId());
        }

        return boughtItems;
    }


    /**
     * Basket's sold products items list setter.
     *
     * @param boughtItems - List<EmployeeBoughtItem>: sold products items list.
     */
    public void setBoughtItems(List<EmployeeBoughtItem> boughtItems) {
        this.boughtItems = boughtItems;
    }
}
