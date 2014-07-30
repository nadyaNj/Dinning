package am.ucom.dinning.web.model;

import javax.servlet.http.HttpServletRequest;

/**
 * extends BaseBean
 *
 * @author arthur
 */
public class MeasurementBean extends BaseBean {

    /**
     * default constructor
     */
    public MeasurementBean() {
    }

    /**
     * Default UUID for serialization...
     */
    private static final long serialVersionUID = 1L;

    private String measurement;

    /**
     * the constructor set parameters
     */
    public MeasurementBean(HttpServletRequest request) {
        measurement = request.getParameter("type").trim();
    }

    /**
     * @return the measurement
     */
    public String getMeasurement() {
        return measurement;
    }

    /**
     * @param measurement the measurement to set
     */
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

}