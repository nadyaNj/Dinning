package am.ucom.dinning.persistence.domain;

public class RolesDomain extends BaseDomain {

    /**
     * Default UUID for serialization...
     */
    private static final long serialVersionUID = 1L;

    /**
     * default constructor
     */
    public RolesDomain() {

    }

    private String role;

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
