package am.ucom.dinning.persistence.domain;

/**
 * The class have information about departament.
 * @author arthur
 */
public class DepartamentDomain extends BaseDomain {

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Public constructor.
     */
    public DepartamentDomain() {
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