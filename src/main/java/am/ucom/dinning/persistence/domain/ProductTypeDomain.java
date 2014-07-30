package am.ucom.dinning.persistence.domain;


/**
 * @author arthur
 */
public class ProductTypeDomain extends BaseDomain {
    /**
     * default constructor
     */
    public ProductTypeDomain() {
    }

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private String type;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }


}
