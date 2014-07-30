/*
 * EmployeeBasketDao		1.0 03/12/12 4:16 PM
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

package am.ucom.dinning.persistence.dao;

import am.ucom.dinning.persistence.domain.EmployeeBasket;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * EmployeeBasketDao domain interface.
 *
 * @author Hayk Hayryan
 * @version 1.0 03/12/12 4:16 PM
 */
public interface EmployeeBasketDao extends GenericDao<EmployeeBasket> {

    String EMPLOYEE_BASKET_TABLE = "employee_basket";

    String EMPLOYEE_BOUGHT_TABLE = "employee_bought_items";

    String BASKET_ID = "id";

    String CASHIER_ID = "cashier_id";

    String PAYMENT_DATE = "payment_date";

    String USER_ID = "usr_id";

    String PAYMENT_TOTAL = "payment_total";

    String PAYMENT_TYPE = "payment_type_code";

    String EMPLOYEE_BASKET_ID = "employee_basket_id";

    /**
     * Returns cashier's saved baskets list
     *
     * @param cashierId - User id with cashier role privileges
     * @return List<EmployeeBasket> - all baskets list saved by cashier
     */
    List<EmployeeBasket> getBasketsByCashierId(Long cashierId);

    /**
     * Returns cashier's saved baskets filtered list by date
     *
     * @param cashierId - User id with cashier role privileges
     * @param date - basket creation date
     *
     * @return List<EmployeeBasket> - all baskets list saved by cashier
     */
    List<EmployeeBasket> getBasketByCashierIdAndDate(Long cashierId, Date date);

    /**
     * Returns baskets by payment type
     *
     * @param paymentType - Integer: payment type values can be 0 - cash or 1 - credit
     *
     * @return List<EmployeeBasket> - all baskets according provided payment type value as search parameter
     */
    List<EmployeeBasket> getBasketsByPaymentType(Integer paymentType);

    /**
     * Returns baskets by payment type and created date
     *
     * @param paymentType - Integer: payment type values can be 0 - cash or 1 - credit
     * @param date - Date: created date
     *
     * @return List<EmployeeBasket> - all baskets according provided payment type value
     *                                and created date as search parameter
     */
    List<EmployeeBasket> getBasketsByPaymentTypeAndDate(Integer paymentType, Date date);

    /**
     * Returns baskets list by created date
     *
     * @param date - Date: created date
     * @return List<EmployeeBasket> - all baskets according provided created date value as search parameter
     */
    List<EmployeeBasket> getBasketsByDate(Date date);

    /**
     * Returns baskets list by created date range
     *
     * @param startDate - Date: created date
     * @param endDate - Date: created date
     *
     * @return List<EmployeeBasket> - all baskets according provided created date start end range values as search parameters
     */
    List<EmployeeBasket> getBasketsByDateRange(Date startDate, Date endDate);

    /**
     * Returns baskets list bought by employee
     *
     * @param userId - Long: employee id
     *
     * @return List<EmployeeBasket> - all baskets bought by user
     */
    List<EmployeeBasket> getBasketsByUserId(Long userId);

    /**
     * Return baskets list bought by employee at provided date
     *
     * @param userId - Long: employee id
     * @param date - Date: bought date
     *
     * @return List<EmployeeBasket> - all baskets bought by user at provided date
     */
    List<EmployeeBasket> getBasketsByUserIdAndDate(Long userId, Date date);

    /**
     * Return baskets list by tot price range
     *
     * @param minPrice - BigDecimal: total price minimum
     * @param maxPrice - BigDecimal: total price maximum
     *
     * @return List<EmployeeBasket> - all baskets according total price min - max values
     */
    List<EmployeeBasket> getBasketsByTotalPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Returns baskets list according generic parameters of search
     *
     * @param searchParams - Map<String, String>: search parameters where key is column name and value is column value
     * @return List<EmployeeBasket> - all baskets according provided generic parameters for search
     */
    List<EmployeeBasket> getBasketsByGenericParams(Map<String, String> searchParams);
}
