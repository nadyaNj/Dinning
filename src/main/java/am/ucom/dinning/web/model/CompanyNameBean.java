package am.ucom.dinning.web.model;

import java.util.List;
/**
 * This class extends Basebean
 * @author arthur
 *
 */
public class CompanyNameBean extends BaseBean {

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private String companyName;

    private List<DepartamentBean> departmentsId;


    /**
     * Public constructor.
     */
    public CompanyNameBean() {
    }

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
    public void setDepartmentsId(List<DepartamentBean> departmentsId) {
        this.departmentsId = departmentsId;
    }

    /**
     * @return the departmentsId
     */
    public List<DepartamentBean> getDepartmentsId() {
        return departmentsId;
    }
}
