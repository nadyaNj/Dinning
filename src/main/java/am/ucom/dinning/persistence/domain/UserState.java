package am.ucom.dinning.persistence.domain;

/**
 * extends BaseDomain
 *
 * @author arthur
 */
public class UserState extends BaseDomain {

    /**
     * default constructor
     */
    public UserState() {
    }

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private String userState;

    /**
     * @return the userState
     */
    public String getUserState() {
        return userState;
    }

    /**
     * @param userState the userState to set
     */
    public void setUserState(String userState) {
        this.userState = userState;
    }

}
