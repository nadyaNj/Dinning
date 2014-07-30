/*
 * EmployeeBoughtItem		1.0 03/12/12 4:16 PM
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
 * EmployeeBoughtItem domain class.
 *
 * @author Hayk Hayryan
 * @version 1.0 03/12/12 4:16 PM
 */
public class EmployeeBoughtItem extends BaseDomain {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal itemPrice;

    private BigDecimal itemDiscountPrice;

    private Date boughtDate;

    private Long employeeBasketId;

    private ProductDomain product;


    /**
     * Public constructor
     */
    public EmployeeBoughtItem() {
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
     * Item discounted price value getter.
     *
     * @return BigDecimal - product discount price value.
     */
    public BigDecimal getItemDiscountPrice() {
        return itemDiscountPrice;
    }

    /**
     * Item discount price value setter.
     *
     * @param itemDiscountPrice - BigDecimal: discount price value.
     */
    public void setItemDiscountPrice(BigDecimal itemDiscountPrice) {
        this.itemDiscountPrice = itemDiscountPrice;
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
     * @return EmployeeBasket - product basket.
     */
    public Long getEmployeeBasketId() {
        return employeeBasketId;
    }

    /**
     * Product basket setter.
     *
     * @param employeeBasketId - EmployeeBasket products basket object instance.
     */
    public void setEmployeeBasketId(Long employeeBasketId) {
        this.employeeBasketId = employeeBasketId;
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
