package am.ucom.dinning.service;

import am.ucom.dinning.web.model.ProductTypeBean;

import java.util.List;

/**
 * returns all product type list
 *
 * @author aram
 */
public interface ProductTypeService {

    /**
     * getting all products types
     *
     * @return List<ProductTypeBean>
     */
    List<ProductTypeBean> getAllProductTypeList();

    /**
     * getting all products types
     *
     * @return List<ProductTypeBean>
     */
    List<ProductTypeBean> getAllProductTypeListbyPage(Integer pageNumber);

    /**
     * Get Type By selected id
     *
     * @param id
     * @return ProductTypeBean
     */
    ProductTypeBean getTypeById(Long id);

    /**
     * update selected type
     *
     * @param productTypeBean
     */
    int updateType(ProductTypeBean productTypeBean);

    /**
     * create new type if created return 1 else return 0
     *
     * @param typeBean
     * @return
     */
    long createProductType(ProductTypeBean typeBean);

    /**
     * delete type by id
     *
     * @param typeId
     */
    int deleteType(Long typeId);

    /**
     * get type names
     *
     * @return
     */
    List<String> getTypeNames(Long id);

    /**
     * get count of types
     *
     * @return
     */
    int getProductTypeCount();

}