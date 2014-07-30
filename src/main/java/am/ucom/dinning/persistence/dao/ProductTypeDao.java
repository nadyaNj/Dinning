package am.ucom.dinning.persistence.dao;

import am.ucom.dinning.persistence.domain.ProductTypeDomain;

import java.util.List;

/**
 * The interface implements GenericDao and
 * add methods ...
 *
 * @author Siranush
 */
public interface ProductTypeDao extends GenericDao<ProductTypeDomain> {

    String CATEGORY_ID = " cat_id ";

    String CATEGORY_NAME = " cat_name ";

    String CATEGORY_CREATED_DATE = " cat_createat ";

    String CATEGORY_CHANGED_DATE = " cat_changeat ";

    /**
     * get all types by page number
     *
     * @param pageNumber
     * @return List<ProductTypeDomain> return List of 10 ProductTypeDomain instances with id started at pageNumber
     */
    List<ProductTypeDomain> getAll(Integer pageNumber);

    /**
     * get all types count
     *
     * @return int the quantity of count data in DB
     */
    int getProductTypeCount();

}
