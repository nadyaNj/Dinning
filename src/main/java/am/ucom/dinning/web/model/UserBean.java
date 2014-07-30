package am.ucom.dinning.web.model;

import javax.servlet.http.HttpServletRequest;

/**
 * extends BaseBean
 *
 * @author arthur
 */
public class UserBean extends BaseBean {

    /**
     * default constructor
     */
    public UserBean() {
    }

    public UserBean(HttpServletRequest request) {
        userName = request.getParameter("username").trim();
        password = request.getParameter("password").trim();
    }


    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;

    private String roleId;

    private String userEmail;

    private String userDepId;

    private String discountCode;

    private String userPosition;

    private Integer stateId;

    private String departmentName;

    private String companyName;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
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
    public String getUserDepId() {
        return userDepId;
    }

    /**
     * @param userDepId the userDepId to set
     */
    public void setUserDepId(String userDepId) {
        this.userDepId = userDepId;
    }

    /**
     * @return the discountCode
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode the discountCode to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
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
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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