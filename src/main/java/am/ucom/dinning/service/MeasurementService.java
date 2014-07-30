package am.ucom.dinning.service;

import am.ucom.dinning.persistence.domain.ProductMeasurementDomain;
import am.ucom.dinning.web.model.MeasurementBean;

import java.util.List;

/**
 * @author arthur
 */
public interface MeasurementService {

    /**
     * get list objects ProductMeasurementDomain instances
     *
     * @param beanList
     * @return List<ProductMeasurementDomain>
     */
    List<ProductMeasurementDomain> getMeasurementDomainList(List<MeasurementBean> beanList);

    /**
     * get list objects MeasurementBean instances
     *
     * @return List<ProductMeasurementDomain>
     */
    List<MeasurementBean> getMeasurementBeanList();

    /**
     * getting all products types
     *
     * @return List<ProductTypeBean>
     */
    List<MeasurementBean> getMeasurementBeanListbyPage(Integer pageNumber);

    /**
     * Get measurement by given id
     *
     * @param id of measurement to return
     * @return
     */
    MeasurementBean getMeasurementById(Long id);

    /**
     * Update measurements method in DB 
     *
     * @param measurementBean instance of MeasurementBean
     * @return
     */
    int updateMeasurement(MeasurementBean measurementBean);

    /**
     * This method for save measurement in DB
     *
     * @param measurementBean
     * @return
     */
    Long saveMeasurement(MeasurementBean measurementBean);

    /**
     * This method for delete Measurement.
     *
     * @param mesId
     * @return
     */
    int deleteMeasurement(Long mesId);

    /**
     * get all measurement names
     *
     * @return
     */
    List<String> measurementNames(Long id);

    /**
     * get count of types
     *
     * @return
     */
    int getMeasurementCount();
}
