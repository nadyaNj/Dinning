/*
 * SharedBasket		1.0 03/12/12 4:16 PM
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
import am.ucom.dinning.persistence.dao.impl.SharedBasketDaoImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SharedBasket domain class.
 *
 * @author Hayk Hayryan
 * @version 1.0 03/12/12 4:16 PM
 */
public class SharedBasket extends BaseDomain {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;

    private Long cashierId;

    private Date paymentDate;

    private BigDecimal paymentTotal;

    @CollectionOption(LazyCollectionOption.TRUE)
    private List<BoughtItem> boughtItems;

    /**
     * Public constructor
     */
    public SharedBasket() {
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
     * @return List<BoughtItem> - sold products items list.
     */
    public List<BoughtItem> getBoughtItems() {
        if (boughtItems == null && getId() != null) {
            boughtItems = SharedBasketDaoImpl.fetchCollectionField(SharedBasket.class, getId());
        }
        return boughtItems;
    }

    /**
     * Basket's sold products items list setter.
     *
     * @param boughtItems - List<BoughtItem>: sold products items list.
     */
    public void setBoughtItems(List<BoughtItem> boughtItems) {
        this.boughtItems = boughtItems;
    }
}
