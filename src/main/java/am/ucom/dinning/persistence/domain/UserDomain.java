package am.ucom.dinning.persistence.domain;

/**
 * The class have iformation about user
 *
 * @author arthur
 */
public class UserDomain extends BaseDomain {

    /**
     * default constructor
     */
    public UserDomain() {
    }

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private Long roleId;

    private String userEmail;

    private Long userDepId;

    private String dicountCode;

    private String userPosition;

    private Integer stateId;

    private String departamentName;

    private String companyName;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roleId
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return the userDepId
     */
    public Long getUserDepId() {
        return userDepId;
    }

    /**
     * @param userDepId the userDepId to set
     */
    public void setUserDepId(Long userDepId) {
        this.userDepId = userDepId;
    }

    /**
     * @return the dicountCode
     */
    public String getDicountCode() {
        return dicountCode;
    }

    /**
     * @param dicountCode the dicountCode to set
     */
    public void setDicountCode(String dicountCode) {
        this.dicountCode = dicountCode;
    }

    /**
     * @return the userPosition
     */
    public String getUserPosition() {
        return userPosition;
    }

    /**
     * @param userPosition the userPosition to set
     */
    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }


    /**
     * @return the stateId
     */
    public Integer getStateId() {
        return stateId;
    }

    /**
     * @param stateId the stateId to set
     */
    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

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

}