package am.ucom.dinning.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import am.ucom.dinning.ServiceProperties;
import am.ucom.dinning.persistence.dao.ProductDao;
import am.ucom.dinning.persistence.dao.impl.ProductDaoImpl;
import am.ucom.dinning.persistence.domain.*;
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.web.model.*;

/**
 * @author arthur
 * 
 * This class Implements ProductService interface
 * initilize istance productDao in constructor
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    /**
     * public constructor inicilize productDao
     */
    public ProductServiceImpl() {
        productDao = new ProductDaoImpl();
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.service.ProductService#updateProduct(am.ucom.dinning.web.model.ProductsBean, java.lang.Long)
     */
    @Override
    public int updateProduct(ProductsBean productsBean) {
        ProductDomain productDomain = initDomainByBean(productsBean);
        String imgUrl = productDao.getImgUrlById(productsBean.getId());
        if (productsBean.getImgUrl().equals("noImage.jpg")) {
            productDomain.setImgUrl(imgUrl);
        }
        return productDao.update(productDomain);
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.service.ProductService#getById(java.lang.Long)
     */
    @Override
    public ProductsBean getProductById(Long id) {
        return initBeanByDomain(productDao.getById(id));        
    }    

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.service.ProductService#getProducts(java.lang.Integer)
     */
	@Override
	public RequestPage<ProductsBean> getProducts(Integer pageNumber) {
		return initRequestPageBySearchResult(productDao.getProducts(pageNumber));
	}

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.service.ProductService#productSearchBySelectedCriteria(am.ucom.dinning.web.model.ProductSearchCriteriaBean, java.lang.Integer)
     */
    @Override
    public RequestPage<ProductsBean> productSearchBySelectedCriteria(ProductSearchCriteriaBean productSearchCriteriaBean, Integer pageNumber) {
        return initRequestPageBySearchResult(productDao.searchProduct(productSearchCriteriaBean, pageNumber));
    }
   
    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.service.ProductService#saveProduct(am.ucom.dinning.web.model.ProductsBean)
     */
    @Override
    public Long saveProduct(ProductsBean productBean) {
        return productDao.save(initDomainByBean(productBean));
    }


    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.service.ProductService#getProductId(am.ucom.dinning.web.model.ProductSearchCriteriaBean)
     */
    @Override
    public int deleteProductById(Long id) {
        ProductDomain productDomain = productDao.getById(id);
        if (!productDomain.getImgUrl().equals("noImage.jpg")) {
            FileManagementServiceImpl deletePicture = new FileManagementServiceImpl();
            deletePicture.deleteFile(ServiceProperties.getImageSavePath() + productDomain.getImgUrl());
        }
        return productDao.delete(id);
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.service.ProductService#batchDeleteById(java.util.List)
     */
    @Override
    public void batchDeleteById(List<String> deleteAllByIds) {
        FileManagementServiceImpl deletePicture = new FileManagementServiceImpl();
        for (String id : deleteAllByIds) {
            String imgUrl = productDao.getImgUrlById(Long.parseLong(id));
            if (imgUrl != null && imgUrl.equals("noImage.jpg")) {
                deletePicture.deleteFile(ServiceProperties.getImageSavePath() + imgUrl);
            }
        }
        productDao.batchDelete(deleteAllByIds);
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.service.ProductService#initUserDomainByUserBean(am.ucom.dinning.web.model.UserBean)
     */
    @Override
    public void hideProductService(String id, Integer hideFlag) {
        productDao.hideProduct(Long.parseLong(id), hideFlag);
    }
   
    /*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.ProductService#isNameAndCodeUnique(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isNameAndCodeUnique(String name, String code, Long id) {
		return productDao.isNameAndCodeUnique(name, code, id);
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.ProductService#usedProducts(java.util.List)
	 */
	@Override
	public List<String> usedProducts(List<String> ids) {
		return productDao.usedProductsIdList(ids);
	}	

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.ProductService#searchByType(java.lang.String)
	 */
	@Override
	public List<ProductsBean> searchByType(String typeId) {
		return initBeanListByDomainList(productDao.searchByType(typeId));		
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.ProductService#searchByCode(java.lang.String)
	 */
	@Override
	public ProductsBean searchByCode(String code) {
		ProductDomain productDomain = productDao.searchByCode(code);
		ProductsBean prodBean = null;
		if(productDomain != null){
			prodBean = initBeanByDomain(productDomain);
		} 
		return prodBean;
	}

    @Override
    public List<ProductsBean> getCurrentDateAllBoughtAndMenuProducts() {
        ProductDao dao = new ProductDaoImpl();
        List<ProductDomain> productDomainList = dao.getCurrentDateMenuAndBoughtProductList();

        return initBeanListByDomainList(productDomainList);
    }

    /**
     * Initialize bean list by given domain list...
     *
     * @param productDomainList
     */
    private List<ProductsBean> initBeanListByDomainList(List<ProductDomain> productDomainList) {
        List<ProductsBean> productBeanList = new ArrayList<ProductsBean>();
        ProductsBean productBean;
        for (ProductDomain productDomain : productDomainList) {
            productBean = initBeanByDomain(productDomain);
            productBeanList.add(productBean);
        }
        return productBeanList;
    }

    /**
     * Initialize bean object by given domain object
     *
     * @param domain
     * @return
     */
    private ProductsBean initBeanByDomain(final ProductDomain domain) {
        ProductsBean bean = new ProductsBean();
        bean.setId(domain.getId());
        bean.setName(domain.getName());
        bean.setPrice(Integer.toString(domain.getPrice()));
        bean.setDescription(domain.getDescription());
        bean.setMeasurementId(domain.getMeasurementId().toString());
        bean.setImgUrl(domain.getImgUrl());
        bean.setCode(domain.getCode());
        bean.setHidden(domain.isHidden());
        bean.setProductTypes(initBeanTypeByDomain(domain.getProductTypeList()));
        bean.setProductTypesString(initListToString(domain.getProductTypeList()));
        bean.setDiscountPrice(domain.getDiscountPrice().toString());
        bean.setChangeDate(domain.getChangedAt());
        bean.setCreateDate(domain.getCreatedAt());
        return bean;
    }

    /**
     * @param typeDomain List<ProductTypeDomain>
     * @return String[] - change list string in array string 
     * 					  and return array string
     */
    private String[] initBeanTypeByDomain(List<ProductTypeDomain> typeDomain) {
        List<String> ids = new ArrayList<String>();
        for (ProductTypeDomain typeList : typeDomain) {
            ids.add(typeList.getId().toString());
        }
        String[] id = (String[]) ids.toArray(new String[0]);
        return id;
    }

    /**
     * 
     * @param types - List
     * @return String
     */
    private String initListToString(List<ProductTypeDomain> types) {
        StringBuilder typeBuilder = new StringBuilder();
    	for(ProductTypeDomain type: types) {
    		typeBuilder.append(type.getType()).append(",");
        }
    	// substring end , and return this string
        return typeBuilder.substring(0, typeBuilder.length() - 1).toString();
    }

    /**
     * Initialize domain object by given bean object.
     *
     * @param productBean
     * @return
     */
    private ProductDomain initDomainByBean(final ProductsBean productBean) {
        ProductDomain productDomain = new ProductDomain();
        productDomain.setId(productBean.getId());
        productDomain.setName(productBean.getName());
        productDomain.setPrice(Integer.parseInt(productBean.getPrice()));
        productDomain.setImgUrl(productBean.getImgUrl());
        productDomain.setDescription(productBean.getDescription());
        productDomain.setCode(productBean.getCode());
        productDomain.setMeasurementId(Long.parseLong(productBean.getMeasurementId()));
        productDomain.setHidden(productBean.isHidden());
        productDomain.setDiscountPrice(Integer.parseInt(productBean.getDiscountPrice()));
        productDomain.setProductTypeList(initDomainTypeIdByBeanTypeId(productBean.getProductTypes()));
        productDomain.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        productDomain.setChangedAt(new Timestamp(new java.util.Date().getTime()));
        return productDomain;
    }

    /**
     * Initialize domain type by given bean type
     *
     * @param productTypeBeanListId
     * @return
     */
    private List<ProductTypeDomain> initDomainTypeIdByBeanTypeId(String[] productTypeBeanListId) {
        List<ProductTypeDomain> productTypeDomainList = new ArrayList<ProductTypeDomain>();
        ProductTypeDomain productTypeDomain = null;
        for (String productTypeBean : productTypeBeanListId) {
            productTypeDomain = new ProductTypeDomain();
            productTypeDomain.setId(Long.parseLong(productTypeBean));
            productTypeDomainList.add(productTypeDomain);
        }
        return productTypeDomainList;
    }
    
    /**
     * 
     * @param products - ProductSearchResult<ProductDomain>
     * @return RequestPage<ProductsBean>
     */
    private RequestPage<ProductsBean> initRequestPageBySearchResult(
    						ProductSearchResult<ProductDomain> products) {
    	Integer count = products.getCount();
        RequestPage<ProductsBean> requestPage = new RequestPage<ProductsBean>();
        Integer pageCount = 0;
        if (count % 10 == 0) {
            pageCount = count / 10;
        } else {
            pageCount = count / 10 + 1;
        }
        requestPage.setPageCount(pageCount.toString());
        requestPage.setProductList(initBeanListByDomainList(products.getDomainList()));
        requestPage.setPageNumber(products.getPageNumber() + 1);
        return requestPage;
    }

}
