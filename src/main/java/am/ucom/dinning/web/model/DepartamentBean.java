package am.ucom.dinning.web.model;

/**
 * This class extends Basebean
 * @author arthur
 */
public class DepartamentBean extends BaseBean {

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Public constructor.
     */
    public DepartamentBean() {
    }

    private String departamentName;

    private Long companyId;

    /**
     * @return the departamentName
     */
    public String getDepartamentName() {
        return departamentName;
    }

    /**
     * @param departamentName the departamentName to set
     */
    public void setDepartamentName(String departamentName) {
        this.departamentName = departamentName;
    }

    /**
     * @return the companyId
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
