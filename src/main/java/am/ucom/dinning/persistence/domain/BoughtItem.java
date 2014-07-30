/*
 * BoughtItem		1.0 03/12/12 4:16 PM
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

import java.math.BigDecimal;
import java.util.Date;


/**
 * BoughtItem domain class.
 *
 * @author Hayk Hayryan
 * @version 1.0 03/12/12 4:16 PM
 */
public class BoughtItem extends BaseDomain {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal itemPrice;

    private Date boughtDate;

    private Long sharedBasketId;

    private ProductDomain product;


    /**
     * Public constructor.
     */
    public BoughtItem() {
    }

    /**
     * Item price getter.
     *
     * @return BigDecimal - product price value.
     */
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    /**
     * Item price setter.
     *
     * @param itemPrice - BigDecimal: item price value.
     */
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * Bought date getter.
     *
     * @return Date - product bought date.
     */
    public Date getBoughtDate() {
        return boughtDate;
    }

    /**
     * Bought date setter.
     *
     * @param boughtDate - Date: product bought date.
     */
    public void setBoughtDate(Date boughtDate) {
        this.boughtDate = boughtDate;
    }

    /**
     * Product basket getter.
     *
     * @return SharedBasket - product basket.
     */
    public Long getSharedBasketId() {
        return sharedBasketId;
    }

    /**
     * Product basket setter.
     *
     * @param sharedBasketId - SharedBasket products basket object instance.
     */
    public void setSharedBasketId(Long sharedBasketId) {
        this.sharedBasketId = sharedBasketId;
    }

    /**
     * Product domain getter
     *
     * @return ProductDomain - sold product
     */
    public ProductDomain getProduct() {
        return product;
    }

    /**
     * Product domain setter.
     *
     * @param product - ProductDomain: sold product.
     */
    public void setProduct(ProductDomain product) {
        this.product = product;
    }
}
