package am.ucom.dinning.persistence.dao;

import am.ucom.dinning.persistence.domain.BoughtItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface BoughtItemDao extends GenericDao<BoughtItem> {

    String BOUGHT_ITEM_TABLE = "bought_items";

    String ITEM_ID = "id";

    String ITEM_PRICE = "item_price";

    String BOUGHT_DATE = "bought_date";

    String SHARED_BASKET_ID = "shared_basket_id";

    String PRODUCT_ID = "product_id";

    /**
     * Return bought items by price
     *
     * @param price - BigDecimal: bought item price
     * @return List<EmployeeBoughtItem> - list of bought items by price
     */
    List<BoughtItem> getBoughtItemsByPrice(BigDecimal price);

    /**
     * Return bought items by price range
     *
     * @param minPrice - BigDecimal: bought item price min value
     * @param maxPrice - BigDecimal: bought item price max value
     *
     * @return List<EmployeeBoughtItem> - list of bought items by min max price
     */
    List<BoughtItem> getBoughtItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Return bought item by date
     *
     * @param date - Date: bought date
     * @return List<EmployeeBoughtItem> - list of bought items according date parameter value
     */
    List<BoughtItem> getBoughtItemsByDate(Date date);

    /**
     * Return basket's bought item
     *
     * @param basketId - Long: basket id
     * @return List<EmployeeBoughtItem> - list of bought items of basket
     */
    List<BoughtItem> getBoughtItemsByBasketId(Long basketId);

    /**
     * Return bought item by product
     *
     * @param productId - Long: product id
     * @return List<EmployeeBoughtItem> - list of bought items by product
     */
    List<BoughtItem> getBoughtItemsByProductId(Long productId);
}
