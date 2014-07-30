package am.ucom.dinning.persistence.dao;

import am.ucom.dinning.persistence.domain.ProductSearchResult;
import am.ucom.dinning.persistence.domain.UserDomain;

import java.util.List;

/**
 * UserDao interface.
 * @author arthur
 *
 */
public interface UserDao extends GenericDao<UserDomain> {

	String USER_TABLE_NAME = " users ";
	
	String USER_ID = " usr_id ";
	
	String USER_NAME = " usr_username ";
	
	String USER_PASSWORD = " usr_password ";
	
	String USER_ROLE_ID = " usr_role_id ";
	
	String USER_DEP_ID = " usr_dep_id ";
	
	String USER_EMAIL = " usr_email ";
	
	String USER_POSITION = " usr_position ";
	
	String USER_CHANGED_DATE = " usr_changeat ";
	
	String USER_CREATED_DATE = " usr_createat ";
	
	String USER_STATE_ID = " usr_status ";
	
	String USER_DISC_CODE = " usr_discount ";
	
	String COMPANY_TABLE_NAME = " company ";

	String COMPANY_NAME = " name ";
		
			
	/**
	 * This method for get Id .
	 * @return
	 */	
	List<UserDomain> getUserParameters(boolean bool);
	
	/**
	 * This method for set StatusId .
	 * @return
	 */	
	int setStatusId(UserDomain entity);
	
	/**
	 * This method for set Password.
	 * @return
	 */	
	int setPassword(UserDomain entity);
	
	/**
	 * updated user password
	 * @return
	 */
	int updateUserPassword(String password, Long userId, int state);
	
	/**
	 * get User Parameters by selected name
	 * @param name
	 * @return UserDomain
	 */
	UserDomain getUserParametersByName(String name);
	
	/**
	 * method for getting users list by selected page number
	 * @param pageNumber
	 * @return
	 */
	List<UserDomain> getUsersByNumber(Integer pageNumber);
	
	/**
	 * method for getting products count
	 * @return
	 */
	int getUsersCount();	
	
	/**
	 * get 10 users by pageNumber, get pageNumber and get users count
	 * @param pageNumber
	 * @return
	 */
	ProductSearchResult<UserDomain> getProductSearchResult(Integer pageNumber);
	
	/**
	 * 
	 * @param name - userName
	 * @return boolean
	 */
	boolean validUserName(String name, Long id);
	
	 /**
     * Retrieve user by username & password
     *
     * @param username - String
     * @param password - String
     * @return UserDomain - object instance with database initialized values
     */
    UserDomain getUserByUsernamePassword(String username, String password);
    
    /**
     * This method for get user by code.
     * @param code - String user discount code
     * @return userDomain - UserDomain 
     */
    UserDomain getUserByCode(String code);
}