package am.ucom.dinning.service;

import am.ucom.dinning.web.model.EmployeeBasketBean;
import am.ucom.dinning.web.model.SharedBasketBean;

public interface BasketService {

    /**
     * Save employee basket
     *
     * @param bean - EmployeeBasketBean object instance with initialized values
     * @return Long - serialized basket's database ID
     */
    Long saveEmployeeBasket(EmployeeBasketBean bean);

    /**
     * Save shared basket
     *
     * @param bean - SharedBasketBean object instance with initialized values
     * @return Long - serialized basket's database ID
     */
    Long saveSharedBasket(SharedBasketBean bean);
}
