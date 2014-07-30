package am.ucom.dinning.persistence.domain;

/**
 * The class have information
 * about measumremet
 *
 * @author arthur
 */
public class ProductMeasurementDomain extends BaseDomain {

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * default constructor
     */
    public ProductMeasurementDomain() {
    }

    private String measurmentName;

    /**
     * @return the measurment
     */
    public String getMeasurmentName() {
        return measurmentName;
    }

    /**
     * @param measurment the measurment to set
     */
    public void setMeasurmentName(String measurmentName) {
        this.measurmentName = measurmentName;
    }
}
