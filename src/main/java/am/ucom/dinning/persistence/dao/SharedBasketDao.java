package am.ucom.dinning.persistence.dao;

import am.ucom.dinning.persistence.domain.SharedBasket;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SharedBasketDao extends GenericDao<SharedBasket> {

    String SHARED_BASKET_TABLE = "shared_basket";

    String BOUGHT_TABLE = "bought_items";

    String BASKET_ID = "id";

    String CASHIER_ID = "cashier_id";

    String PAYMENT_DATE = "payment_date";

    String PAYMENT_TOTAL = "payment_total";

    /**
     * Returns cashier's saved baskets list
     *
     * @param cashierId - User id with cashier role privileges
     * @return List<EmployeeBasket> - all baskets list saved by cashier
     */
    List<SharedBasket> getBasketsByCashierId(Long cashierId);

    /**
     * Returns cashier's saved baskets filtered list by date
     *
     * @param cashierId - User id with cashier role privileges
     * @param date      - basket creation date
     * @return List<EmployeeBasket> - all baskets list saved by cashier
     */
    List<SharedBasket> getBasketByCashierIdAndDate(Long cashierId, Date date);

    /**
     * Returns baskets list by created date
     *
     * @param date - Date: created date
     * @return List<EmployeeBasket> - all baskets according provided created date value as search parameter
     */
    List<SharedBasket> getBasketsByDate(Date date);

    /**
     * Returns baskets list by created date range
     *
     * @param startDate - Date: created date
     * @param endDate   - Date: created date
     * @return List<EmployeeBasket> - all baskets according provided created date start end range values as search parameters
     */
    List<SharedBasket> getBasketsByDateRange(Date startDate, Date endDate);

    /**
     * Return baskets list by tot price range
     *
     * @param minPrice - BigDecimal: total price minimum
     * @param maxPrice - BigDecimal: total price maximum
     * @return List<EmployeeBasket> - all baskets according total price min - max values
     */
    List<SharedBasket> getBasketsByTotalPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Returns baskets list according generic parameters of search
     *
     * @param searchParams - Map<String, String>: search parameters where key is column name and value is column value
     * @return List<EmployeeBasket> - all baskets according provided generic parameters for search
     */
    List<SharedBasket> getBasketsByGenericParams(Map<String, String> searchParams);
}
