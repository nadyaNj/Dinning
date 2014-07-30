package am.ucom.dinning.persistence.dao;

import am.ucom.dinning.persistence.domain.EmployeeBoughtItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface EmployeeBoughtItemDao extends GenericDao<EmployeeBoughtItem> {
    String EMPLOYEE_BOUGHT_ITEM_TABLE = "employee_bought_items";

    String ITEM_ID = "id";

    String ITEM_PRICE = "item_price";

    String ITEM_DISCOUNT_PRICE = "item_discount_price";

    String BOUGHT_DATE = "bought_date";

    String BASKET_ID = "employee_basket_id";

    String PRODUCT_ID = "product_id";

    /**
     * Return bought items by price
     *
     * @param price - BigDecimal: bought item price
     * @return List<EmployeeBoughtItem> - list of bought items by price
     */
    List<EmployeeBoughtItem> getBoughtItemsByPrice(BigDecimal price);

    /**
     * Return bought items by price range
     *
     * @param minPrice - BigDecimal: bought item price min value
     * @param maxPrice - BigDecimal: bought item price max value
     *
     * @return List<EmployeeBoughtItem> - list of bought items by min max price
     */
    List<EmployeeBoughtItem> getBoughtItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Return bought items according discountPrice
     * 
     * @param discountPrice - BigDecimal: discount price
     * @return List<EmployeeBoughtItem> - list of bought items according discount price
     */
    List<EmployeeBoughtItem> getBoughtItemsByDiscountPrice(BigDecimal discountPrice);

    /**
     * Return bought items according discount price range
     * 
     * @param minDiscountPrice - BigDecimal: discount price min value
     * @param maxDiscountPrice - BigDecimal: discount price max value
     *
     * @return List<EmployeeBoughtItem> - list of bought items by min max discount price
     */
    List<EmployeeBoughtItem> getBoughtItemsByDiscountPriceRange(BigDecimal minDiscountPrice, BigDecimal maxDiscountPrice);

    /**
     * Return bought item by date
     *
     * @param date - Date: bought date
     * @return List<EmployeeBoughtItem> - list of bought items according date parameter value
     */
    List<EmployeeBoughtItem> getBoughtItemsByDate(Date date);

    /**
     * Return basket's bought item
     *
     * @param basketId - Long: basket id
     * @return List<EmployeeBoughtItem> - list of bought items of basket
     */
    List<EmployeeBoughtItem> getBoughtItemsByBasketId(Long basketId);

    /**
     * Return bought item by product
     *
     * @param productId - Long: product id
     * @return List<EmployeeBoughtItem> - list of bought items by product
     */
    List<EmployeeBoughtItem> getBoughtItemsByProductId(Long productId);
}
