package am.ucom.dinning.persistence.dao;

import java.util.List;

import am.ucom.dinning.persistence.domain.ProductDomain;
import am.ucom.dinning.persistence.domain.ProductSearchResult;
import am.ucom.dinning.web.model.ProductSearchCriteriaBean;

/**
 * Product DAO interface
 *
 * @author nadya
 */

public interface ProductDao extends GenericDao<ProductDomain> {

    String PRODUCT_ID = " pr_id ";

    String PRODUCT_NAME = " pr_name ";

    String PRODUCT_PRICE = " pr_price ";

    String PRODUCT_PICTURE_URL = " pr_picurl ";

    String PRODUCT_DESCRIPTION = " pr_desc ";

    String PRODUCT_CODE = " pr_code ";

    String PRODUCT_MASURMENT_ID = " pr_mes_id ";

    String PRODUCT_CREATED_DATE = " pr_createat ";

    String PRODUCT_CHANGED_DATE = " pr_changeat ";

    String PRODUCT_HIDE = " pr_hide ";
    
    String PRODUCT_DISC_PRICE = " pr_disc_price ";

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.GenericDao#getById(java.lang.Long)
     */
    ProductDomain getById(Long Id);

    /**
     * hided product
     *
     * @param id - Long
     * @param hideFlag - Integer
     */
    void hideProduct(Long id, Integer hideFlag);

    /**
     * get search result by pageNumber searchBean
     * 
     * @param searchBean - ProductSearchCriteriaBean
     * @param pageNumber - Integer
     * @return ProductSearchResult<ProductDomain>
     */
    ProductSearchResult<ProductDomain> searchProduct(ProductSearchCriteriaBean searchBean, Integer pageNumber);
    
	/**
	 * return is name and code unique
	 * @param name - String
	 * @param code - String
	 * @param id - Long
	 * @return boolean
	 */
	boolean isNameAndCodeUnique(String name, String code, Long id);
	
	/**
	 * get img url by product id
	 * @param id - Long
	 * @return String
	 */
	String getImgUrlById(Long id);
	
	/**
	 * 
	 * @param ids - String: products ids
	 * @return boolean - is products used return true or false
	 */
	List<String> usedProductsIdList(List<String> ids);
	
	/**
	 * 
	 * @param pageNumber - Integer: by pageNUmber  get 10 products
	 * @return ProductSearchResult<ProductDomain>			
	 */
	ProductSearchResult<ProductDomain> getProducts(Integer pageNumber);
	
	/**
	 * 
	 * @param typeId - Long: search type id
	 * @return List<ProductDomain> - search result
	 */
	List<ProductDomain> searchByType(String typeId);

    /**
     * Return current date bought and menu items products list
     *
     * @return List<ProductDomain> - urrent date bought and menu items all products.
     */
    List<ProductDomain> getCurrentDateMenuAndBoughtProductList();
	
	/**
	 * 
	 * @param code - String: searched code
	 * @return ProductDomain - search code
	 */
	ProductDomain searchByCode(String code);
}
