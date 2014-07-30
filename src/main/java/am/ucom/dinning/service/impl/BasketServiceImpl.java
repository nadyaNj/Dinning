package am.ucom.dinning.service.impl;

import am.ucom.dinning.persistence.dao.EmployeeBasketDao;
import am.ucom.dinning.persistence.dao.SharedBasketDao;
import am.ucom.dinning.persistence.dao.impl.EmployeeBasketDaoImpl;
import am.ucom.dinning.persistence.dao.impl.SharedBasketDaoImpl;
import am.ucom.dinning.persistence.domain.BoughtItem;
import am.ucom.dinning.persistence.domain.EmployeeBasket;
import am.ucom.dinning.persistence.domain.EmployeeBoughtItem;
import am.ucom.dinning.persistence.domain.SharedBasket;
import am.ucom.dinning.service.BasketService;
import am.ucom.dinning.web.model.EmployeeBasketBean;
import am.ucom.dinning.web.model.EmployeeBasketItemBean;
import am.ucom.dinning.web.model.SharedBasketBean;
import am.ucom.dinning.web.model.SharedBasketItemBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BasketServiceImpl implements BasketService {

    private static final Logger LOG = LoggerFactory.getLogger(BasketServiceImpl.class);

    public BasketServiceImpl() {
    }

    @Override
    public Long saveEmployeeBasket(EmployeeBasketBean bean) {
        EmployeeBasketDao dao = new EmployeeBasketDaoImpl();
        return dao.save(initEmployeeBasketDomain(bean));
    }

    @Override
    public Long saveSharedBasket(SharedBasketBean bean) {
        SharedBasketDao dao = new SharedBasketDaoImpl();
        return dao.save(initSharedBasketDomain(bean));
    }

    private EmployeeBasket initEmployeeBasketDomain(EmployeeBasketBean bean) {
        List<EmployeeBoughtItem> boughtItemList = new ArrayList<EmployeeBoughtItem>();
        EmployeeBasket basket = new EmployeeBasket();
        basket.setCashierId(bean.getCashierId());
        basket.setPaymentTypeCode(bean.getPaymentTypeCode());
        basket.setPaymentDate(bean.getPaymentDate());
        basket.setPaymentTotal(bean.getPaymentTotal());
        basket.setUserId(bean.getEmployeeId());

        for(EmployeeBasketItemBean item : bean.getItemsList()) {
            EmployeeBoughtItem boughtItem = new EmployeeBoughtItem();
            boughtItem.setItemPrice(item.getItemPrice());
            boughtItem.setItemDiscountPrice(item.getItemDiscountPrice());
            boughtItem.setBoughtDate(bean.getPaymentDate());
            boughtItem.setProduct(EmployeeBasketDaoImpl.getBoughtProductDomain(item.getProductId()));
            boughtItemList.add(boughtItem);
        }

        basket.setBoughtItems(boughtItemList);

        return basket;
    }

    private SharedBasket initSharedBasketDomain(SharedBasketBean bean) {
        List<BoughtItem> itemList = new ArrayList<BoughtItem>();
        SharedBasket basket = new SharedBasket();
        basket.setCashierId(bean.getCashierId());
        basket.setPaymentDate(bean.getPaymentDate());
        basket.setPaymentTotal(bean.getPaymentTotal());

        for(SharedBasketItemBean item : bean.getSharedBasketItemBeanList()) {
            BoughtItem boughtItem = new BoughtItem();
            boughtItem.setItemPrice(item.getItemPrice());
            boughtItem.setBoughtDate(basket.getPaymentDate());
            boughtItem.setProduct(SharedBasketDaoImpl.getBoughtProductDomain(item.getProductId()));
            itemList.add(boughtItem);
        }

        basket.setBoughtItems(itemList);

        return basket;
    }
}
