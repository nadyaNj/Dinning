package am.ucom.dinning.service;

import am.ucom.dinning.web.model.ProductSearchCriteriaBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.RequestPage;

import java.util.List;

/**
 * 
 * @author arthur
 * this interface base ProductServiceImpl
 * 
 */
public interface ProductService {
    
    /**
     * Searching by selected criteria
     *
     * @param productsBean
     * @return
     */
    RequestPage<ProductsBean> productSearchBySelectedCriteria(ProductSearchCriteriaBean productsBean, Integer pageNumber);

    /**
     * Save given product.
     *
     * @param productsBean - ProductsBean
     */
    Long saveProduct(ProductsBean productsBean);

    /**
     * This method for get product Id.
     *
     * @param typeIdes - Long
     */
    int deleteProductById(Long id);

    /**
     * Get Product By selected id
     *
     * @param id - Long
     * @return ProductBean
     */
    ProductsBean getProductById(Long id);

    /**
     * update selected product
     *
     * @param productsBean - ProductsBean
     */
    int updateProduct(ProductsBean productsBean);

    /**
     * Method for get Ides and delete products.
     *
     * @param deleteAllByIds
     */
    void batchDeleteById(List<String> deleteAllByIds);

    /**
     * hide product by id
     * @param id - Long
     * @param hideFlag - Integer
     */
    void hideProductService(String id, Integer hideFlag);
	
	/**
	 * check is name and code unique
	 * @param name - String
	 * @param code - String
	 * @param id - Long
	 * @return boolean
	 */
	boolean isNameAndCodeUnique(String name, String code, Long id);
	
	/**
	 * 
	 * @param ids - List<String> deleted product ids
	 * @return List<Srting> - used product ids list
	 */
	List<String> usedProducts(List<String> ids);
	
	/**
	 * 
	 * @param pageNumber - Integer
	 * @return RequestPage<ProductsBean>
	 */
	RequestPage<ProductsBean> getProducts(Integer pageNumber);
	
	/**
	 * 
	 * @param typeId - String: type id, the ids searched products
	 * @return List<ProductsBean> - resultat search
	 */
	List<ProductsBean> searchByType(String typeId);

    /**
     * Return products list
     *
     * @return List<ProductsBean> - products list
     */
    List<ProductsBean> getCurrentDateAllBoughtAndMenuProducts();
	
	/**
	 * 
	 * @param code - String: product Code 
	 * @return ProductsBean - get productBean by product code
	 */
	ProductsBean searchByCode(String code);
}
