package am.ucom.dinning.persistence.domain;

import java.util.List;

/**
 * The class have information about company.
 * @author arthur
 */
public class CompanyDomain extends BaseDomain {

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;


    /**
     * Public constructor
     */
    public CompanyDomain() {
    }

    private String companyName;

    private List<DepartamentDomain> departmentsId;

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @param departmentsId the departmentsId to set
     */
    public void setDepartmentsId(List<DepartamentDomain> departmentsId) {
        this.departmentsId = departmentsId;
    }

    /**
     * @return the departmentsId
     */
    public List<DepartamentDomain> getDepartmentsId() {
        return departmentsId;
    }
}
