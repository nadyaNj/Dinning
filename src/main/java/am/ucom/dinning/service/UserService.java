package am.ucom.dinning.service;

import am.ucom.dinning.web.model.RequestPage;
import am.ucom.dinning.web.model.UserBean;

import javax.security.sasl.AuthenticationException;
import java.util.List;

public interface UserService {

    /**
     * Get all username and password.
     *
     * @param boolean
     * @return List<UserBean>
     */
    List<UserBean> getUserParameters(boolean bool);

    /**
     * Save given user.
     *
     * @param productsBean return Long
     */
    Long saveUser(UserBean userBean);

    /**
     * getting userNames by selected Id
     *
     * @param id
     * @return List<String>
     */
    List<String> getUserNames(Long id);

    /**
     * delete user by selected Id
     *
     * @param id
     */
    void deleteUserById(Long deleteUserId);

    /**
     * Get all username and password.
     *
     * @param id
     * @return int
     */
    int updateStatus(UserBean userBean);

    /**
     * updating password
     *
     * @param id
     * @return int
     */
    int setPassword(UserBean userBean);


    /**
     * update user
     *
     * @param id
     * @return int
     */
    int update(UserBean userBean);

    /**
     * update user password
     *
     * @param password
     * @param userId
     * @return int
     */
    int updateUserPassword(String password, Long userId, int state);

    /**
     * get User Parameters by selected name
     *
     * @param name
     * @return UserBean
     */
    UserBean getUserParametersByName(String name);

    /**
     * get Users parameters by delected page number
     *
     * @param pageNumber
     * @return
     */
    List<UserBean> getUsersByNumber(Integer pageNumber);

    /**
     * method for getting products count
     *
     * @return
     */
    int getUsersCount();

    /**
     * get RequestPage by pageNumber
     *
     * @param pageNumber
     * @return
     */
    RequestPage<UserBean> getRequestPage(Integer pageNumber);

    /**
     * get UserBean by user id
     *
     * @param userId
     * @return UserBean
     */
    UserBean getUserById(Long userId);

    /**
     * deleted user by id
     *
     * @param id
     * @return id deleted user
     */
    int deleteUser(Long id);
    
    
	/**
	 * is username unique return true else return false
	 * @param String - userName
	 * @return boolean
	 */
	boolean validUserName(String userName, Long id);

    /**
     * Authenticate user based on username password parameters
     *
     * @param username - String
     * @param password - SHA1 encrypted hexadecimal string value
     */
    UserBean authenticateUser(String username, String password) throws AuthenticationException;
    
    /**
     * This method for get user by code.
     * @param code - String user discount code
     * @return userBean - UserBean 
     */
    UserBean getUserByCode(String code);
}
