package am.ucom.dinning.persistence.dao;

import am.ucom.dinning.persistence.domain.ProductMeasurementDomain;

import java.util.List;

/**
 * @author Siranush
 */
public interface ProductMeasurementDao extends GenericDao<ProductMeasurementDomain> {

    String MEASURMENT_TABLE_NAME = " mesurement ";

    String MEASURMENT_ID = " mes_id ";

    String MEASURMENT_NAME = " mes_name ";

    String MEASURMENT_CREATED_DATE = " mes_createat ";

    String MEASURMENT_CHANGED_DATE = " mes_changeat ";

    String PRODUCT_MEASUREMENT_MEASUREMENT_ID = "pr_mes_id";

    String PRODUCT_TABLE_NAME = "products";

	/**
	 * get all measurements by page number
	 * 
	 * @param pageNumber -the current page number
	 * @return List<ProductMeasurementDomain> List of 10 ProductMeasurementDomain instances with id started at pageNumber
	 */
    List<ProductMeasurementDomain> getAll(Integer pageNumber);

    /**
     * get mesurments count
     *
     * @return int the count of measurment data in DB
     */
    int getMeasurementCount();
}