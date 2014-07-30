package am.ucom.dinning.service.impl;

import am.ucom.dinning.persistence.dao.ProductTypeDao;
import am.ucom.dinning.persistence.dao.impl.ProductTypeDaoImpl;
import am.ucom.dinning.persistence.domain.ProductTypeDomain;
import am.ucom.dinning.service.ProductTypeService;
import am.ucom.dinning.web.model.ProductTypeBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * this class implements ProductTypeService interface
 */
public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeDao productTypeDao;

    /**
     * create object productTypeDao
     */
    public ProductTypeServiceImpl() {
        productTypeDao = new ProductTypeDaoImpl();
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.ProductTypeService#getAllProductTypeList()
      */
    @Override
    public List<ProductTypeBean> getAllProductTypeList() {

        List<ProductTypeDomain> productTypeList = productTypeDao.getAll();
        return initTypeBeanListByTypeDomainList(productTypeList);
    }

    @Override
    public List<ProductTypeBean> getAllProductTypeListbyPage(Integer pageNumber) {

        List<ProductTypeDomain> productTypeList = productTypeDao.getAll(pageNumber);
        return initTypeBeanListByTypeDomainList(productTypeList);
    }

    /**
     * initialize bean type list from domain type list
     *
     * @param productTypeDomainList
     * @return
     */
    private List<ProductTypeBean> initTypeBeanListByTypeDomainList(List<ProductTypeDomain> productTypeDomainList) {
        List<ProductTypeBean> productBeanList = new ArrayList<ProductTypeBean>();
        for (ProductTypeDomain productTypeDomain : productTypeDomainList) {
            ProductTypeBean productTypeBean = initBeanTypeByDomaintype(productTypeDomain);

            productBeanList.add(productTypeBean);
        }

        return productBeanList;
    }

    /**
     * Initialize bean type by domain type
     *
     * @param domain
     * @return
     */
    private ProductTypeBean initBeanTypeByDomaintype(ProductTypeDomain typeDomain) {
        ProductTypeBean typeBean = new ProductTypeBean();

        typeBean.setId(typeDomain.getId());
        typeBean.setType(typeDomain.getType());
        typeBean.setId(typeDomain.getId());
        typeBean.setChangeDate(typeDomain.getChangedAt());
        typeBean.setCreateDate(typeDomain.getCreatedAt());

        return typeBean;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.ProductTypeService#createProductType(am.ucom.dinning.web.model.ProductTypeBean)
      */
    @Override
    public long createProductType(ProductTypeBean typeBean) {
        return productTypeDao.save(initDomainTypeByBeanType(typeBean));
    }

    /**
     * Initialize domain type by bean type
     *
     * @param bean
     * @return
     */
    private ProductTypeDomain initDomainTypeByBeanType(ProductTypeBean typeBean) {
        ProductTypeDomain typeDomain = new ProductTypeDomain();

        typeDomain.setId(typeBean.getId());
        typeDomain.setType(typeBean.getType());
        typeDomain.setChangedAt(new Timestamp(new java.util.Date().getTime()));
        typeDomain.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        return typeDomain;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.ProductTypeService#updateType(am.ucom.dinning.web.model.ProductTypeBean)
      */
    @Override
    public int updateType(ProductTypeBean productTypeBean) {
        return productTypeDao.update(initDomainTypeByBeanType(productTypeBean));
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.ProductTypeService#getProductById(java.lang.Long)
      */
    @Override
    public ProductTypeBean getTypeById(Long id) {
    	return initBeanTypeByDomaintype(productTypeDao.getById(id));
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.ProductTypeService#deleteType(java.lang.Long)
      */
    @Override
    public int deleteType(Long typeId) {
        return productTypeDao.delete(typeId);
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.ProductTypeService#getTypeNames()
      */
    @Override
    public List<String> getTypeNames(Long id) {
        return productTypeDao.getAllNames(id);
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.ProductTypeService#deleteType(java.lang.Long)
      */
    @Override
    public int getProductTypeCount() {
        return productTypeDao.getProductTypeCount();
    }


}