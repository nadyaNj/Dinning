package am.ucom.dinning.service.impl;

import am.ucom.dinning.persistence.dao.ProductMeasurementDao;
import am.ucom.dinning.persistence.dao.impl.ProductMeasurementDaoImpl;
import am.ucom.dinning.persistence.domain.ProductMeasurementDomain;
import am.ucom.dinning.service.MeasurementService;
import am.ucom.dinning.web.model.MeasurementBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MeasurementServiceImpl implements MeasurementService {

    private ProductMeasurementDao measurementDao;

    /**
     * cretate object measurementDao
     */
    public MeasurementServiceImpl() {
        measurementDao = new ProductMeasurementDaoImpl();
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.MeasurementService#getMeasurementDomainList(java.util.List)
      */
    @Override
    public List<ProductMeasurementDomain> getMeasurementDomainList(
            List<MeasurementBean> beanList) {
        return null;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.MeasurementService#updateMeasurement(am.ucom.dinning.web.model.MeasurementBean)
      */
    public int updateMeasurement(MeasurementBean measurementBean) {
        ProductMeasurementDomain productMeasurementDomain = initMeasurementDomainByMeasurementBean(measurementBean);
        return measurementDao.update(productMeasurementDomain);
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.MeasurementService#getMeasurementById(java.lang.Long)
      */
    public MeasurementBean getMeasurementById(Long id) {
    	return initBeanMeasurementByMeasurementDomain(measurementDao.getById(id));
         
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.MeasurementService#getMeasurementBeanList()
      */
    @Override
    public List<MeasurementBean> getMeasurementBeanList() {
        List<ProductMeasurementDomain> measurementDomainList = measurementDao.getAll();
        return initListBeanByDomainList(measurementDomainList);
    }


    @Override
    public List<MeasurementBean> getMeasurementBeanListbyPage(Integer pageNumber) {

        List<ProductMeasurementDomain> measurementDomainList = measurementDao.getAll(pageNumber);
        return initListBeanByDomainList(measurementDomainList);
    }


    private ProductMeasurementDomain initMeasurementDomainByMeasurementBean(MeasurementBean measurementBean) {
        ProductMeasurementDomain productMeasurementDomain = new ProductMeasurementDomain();
        productMeasurementDomain.setId(measurementBean.getId());
        productMeasurementDomain.setMeasurmentName(measurementBean.getMeasurement());
        productMeasurementDomain.setChangedAt(new Timestamp(new java.util.Date().getTime()));
        productMeasurementDomain.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        return productMeasurementDomain;
    }


    /**
     * Change measurmentDomain to measurmentBean
     *
     * @param measurementDomain
     * @return
     */
    private MeasurementBean initBeanMeasurementByMeasurementDomain(ProductMeasurementDomain measurementDomain) {
        MeasurementBean measurementBean = new MeasurementBean();
        measurementBean.setId(measurementDomain.getId());
        measurementBean.setMeasurement(measurementDomain.getMeasurmentName());
        measurementBean.setCreateDate(measurementDomain.getCreatedAt());
        measurementBean.setChangeDate(measurementDomain.getCreatedAt());
        return measurementBean;
    }

    /**
     * change List<ProductMeasurementDomain> to List<MeasurementBean>
     *
     * @param measurementDomainList
     * @return List<MeasurementBean>
     */
    private List<MeasurementBean> initListBeanByDomainList(List<ProductMeasurementDomain> measurementDomainList) {
        List<MeasurementBean> listMeasurementBean = new ArrayList<MeasurementBean>();
        for (ProductMeasurementDomain measurementDomain : measurementDomainList) {
            listMeasurementBean.add(initBeanMeasurementByMeasurementDomain(measurementDomain));
        }
        return listMeasurementBean;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.MeasurementService#saveMeasurement(am.ucom.dinning.web.model.MeasurementBean)
      */
    @Override
    public Long saveMeasurement(MeasurementBean measurementBean) {
        ProductMeasurementDomain productMeasurementDomain = new ProductMeasurementDomain();
        productMeasurementDomain.setMeasurmentName(measurementBean.getMeasurement());
        productMeasurementDomain.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        productMeasurementDomain.setChangedAt(new Timestamp(new java.util.Date().getTime()));

        return measurementDao.save(productMeasurementDomain);
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.MeasurementService#measurementNames()
      */
    @Override
    public List<String> measurementNames(Long id) {
        return measurementDao.getAllNames(id);
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.service.MeasurementService#deleteMeasurement(java.lang.Long)
      */
    @Override
    public int deleteMeasurement(Long mesId) {
        return measurementDao.delete(mesId);
    }

    @Override
    public int getMeasurementCount() {
        return measurementDao.getMeasurementCount();
    }
}
