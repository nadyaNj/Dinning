package am.ucom.dinning.web.model;

import javax.servlet.http.HttpServletRequest;

/**
 * extends BaseBean
 *
 * @author arthur
 */
public class ProductTypeBean extends BaseBean {

    /**
     * Default UUID for serialization...
     */
    private static final long serialVersionUID = 1L;

    /**
     * default constructor
     */
    public ProductTypeBean() {
    }

    private String type;

    /**
     * the constructor set parameters
     */
    public ProductTypeBean(HttpServletRequest request) {
        type = request.getParameter("type").trim();
    }

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