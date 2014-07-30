package am.ucom.dinning.service.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import am.ucom.dinning.persistence.dao.UserDao;
import am.ucom.dinning.persistence.dao.impl.UserDaoImpl;
import am.ucom.dinning.persistence.domain.ProductSearchResult;
import am.ucom.dinning.persistence.domain.UserDomain;
import am.ucom.dinning.service.UserService;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.util.SecurityUtil;
import am.ucom.dinning.web.model.RequestPage;
import am.ucom.dinning.web.model.UserBean;

import javax.security.sasl.AuthenticationException;

public class UserServiceImpl implements UserService {
	
    public static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	
	private UserDao userDao;
	
	/**
	 * default constructor
	 */
	public UserServiceImpl() {
		userDao = new UserDaoImpl() ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#getUsernamePassword()
	 */
	@Override
	public List<UserBean> getUserParameters(boolean bool) {
		return initListBeanByDomain(userDao.getUserParameters(bool));
	}

    /**
     * @param username - String: users registrated name
     * @param password - String: encrypted passwoord based on SHA1 algorithm hexadecimal value
     *
     * @throws AuthenticationException - if user not match
     * @return UserBean - instance object with authenticate user's values
     */
	@Override
    public UserBean authenticateUser(final String username, final String password) throws AuthenticationException {

        UserDomain domain = userDao.getUserByUsernamePassword(username, password);
        UserBean userBean;

        if (domain != null) {
            userBean = initBeanByDomain(domain);
        } else {
            throw new AuthenticationException("Authentication failed, incorrect password or username");
        }

        return userBean;
    }
	
	/**
	 * init bean list by domain list
	 * @param listUser
	 * @return
	 */
	private List<UserBean> initListBeanByDomain(List<UserDomain> listUser) {
		List<UserBean> beanList = new ArrayList<UserBean>();
		for(UserDomain user : listUser) {
			beanList.add(initBeanByDomain(user));
		}
		return beanList;
	}
	
	/**
	 * init Bean parameters by Domain
	 * @param user
	 * @return UserBean
	 */
	private UserBean initBeanByDomain(UserDomain user) {
		UserBean userBean = new UserBean();
		userBean.setId(user.getId());			
		userBean.setRoleId(user.getRoleId().toString());
		userBean.setUserDepId(user.getUserDepId().toString());
		userBean.setUserEmail(user.getUserEmail());
		userBean.setUserPosition(user.getUserPosition());
		userBean.setStateId(user.getStateId());
		userBean.setDepartmentName(user.getDepartamentName());
		userBean.setCompanyName(user.getCompanyName());
		userBean.setId(user.getId());
		userBean.setUserName(user.getUsername());
		userBean.setPassword(user.getPassword());
		userBean.setRoleId(user.getRoleId().toString());
		userBean.setDiscountCode(user.getDicountCode());
		userBean.setRoleId(user.getRoleId().toString());		
		return userBean;
	}
	/*
     * (non-Javadoc)
     * @see am.ucom.dinning.service.ProductService#saveProduct(am.ucom.dinning.web.model.ProductsBean)
     */
    @Override
    public Long saveUser(UserBean userBean) {
    	UserDomain domain = initDomainByBean(userBean);
    	return userDao.save(domain);  	
    } 
    
    /**
	 * init bean by domain
	 * @param userBean
	 * @return UserDomain
	 */
    private UserDomain initDomainByBean(final UserBean userBean) {
    	UserDomain userDomain = new UserDomain();
    	userDomain.setId(userBean.getId());
    	userDomain.setUsername(userBean.getUserName());
    	try {
			userDomain.setPassword(SecurityUtil.encryptString(userBean.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	userDomain.setRoleId(Long.parseLong(userBean.getRoleId()));
    	userDomain.setUserDepId(Long.parseLong(userBean.getUserDepId()));
    	userDomain.setUserEmail(userBean.getUserEmail());
    	userDomain.setUserPosition(userBean.getUserPosition());
    	userDomain.setStateId(userBean.getStateId());
    	userDomain.setDicountCode(userBean.getDiscountCode());
    	   	
    	userDomain.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    	userDomain.setChangedAt(new Timestamp(new java.util.Date().getTime()));
    	
    	return userDomain;
    }
    /*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.ProductService#getProductNames()
	 */
	@Override
	public List<String> getUserNames(Long id) {
		return userDao.getAllNames(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#deleteUserById(java.lang.Long)
	 */
	public void deleteUserById(Long deleteUserId){
		 userDao.delete(deleteUserId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#updateStatus(am.ucom.dinning.web.model.UserBean)
	 */
	public int updateStatus(UserBean userBean){
		UserDomain userDomain = userDao.getById(userBean.getId());
		if(userDomain.getStateId() == Constants.USER_STATE_IN_ACTIVE) {
			userDomain.setStateId(Constants.USER_STATE_ACTIVE);
		} else if(userDomain.getStateId() == Constants.USER_STATE_ACTIVE) {
			userDomain.setStateId(Constants.USER_STATE_IN_ACTIVE);			
		}				
		return userDao.setStatusId(userDomain);		
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#setPassword(am.ucom.dinning.web.model.UserBean)
	 */
	public int setPassword(UserBean userBean){
		UserDomain userDomain =userDao.getById(userBean.getId());
		Long id = userBean.getId();
		userDomain.setId(id);
		userDomain.setStateId(userBean.getStateId());
		userDomain.setPassword(userBean.getPassword());
		userDomain.setChangedAt(new Timestamp(new java.util.Date().getTime()));
		return userDao.setPassword(userDomain);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#update(am.ucom.dinning.web.model.UserBean)
	 */
	public int update(UserBean userBean){
		UserDomain userDomain =userDao.getById(userBean.getId());
		Long id = userBean.getId();
		userDomain.setId(id);
		userDomain.setUsername(userBean.getUserName());
    	
    	if (userBean.getRoleId()!=null){
    		userDomain.setRoleId(Long.parseLong(userBean.getRoleId()));
    	}
    	if (userBean.getUserDepId()!=null){
    		userDomain.setUserDepId(Long.parseLong(userBean.getUserDepId()));
    	}
    	userDomain.setUserEmail(userBean.getUserEmail());
    	userDomain.setUserPosition(userBean.getUserPosition());
    	userDomain.setDicountCode(userBean.getDiscountCode());
		
		return userDao.update(userDomain);
		
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#updateUserPassword(java.lang.String, java.lang.Long)
	 */
	@Override
	public int updateUserPassword(String password, Long userId, int state) {
		String cashPassword = null;
		try {
			cashPassword = SecurityUtil.encryptString(password);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("update user password error: " + e.getMessage(), e);	
		}
		return userDao.updateUserPassword(cashPassword, userId, state);
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#getUserParametersByName(java.lang.String)
	 */
	@Override
	public UserBean getUserParametersByName(String name) {
	
		return initBeanByDomain(userDao.getUserParametersByName(name));
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#getUsersByNumber(java.lang.Integer)
	 */
	@Override
	public List<UserBean> getUsersByNumber(Integer pageNumber) {
		List<UserBean> userBeanList = new ArrayList<UserBean>();
		List<UserDomain> userDomainList = userDao.getUsersByNumber(pageNumber);
		userBeanList = initListBeanByDomain(userDomainList);		
		return userBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#getUsersCount()
	 */
	@Override
	public int getUsersCount() {
		return userDao.getUsersCount();
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#getRequestPage(java.lang.Integer)
	 */
	@Override
	public RequestPage<UserBean> getRequestPage(Integer pageNumber) {
		ProductSearchResult<UserDomain> result = userDao.getProductSearchResult(pageNumber);
		return initRequestPagebyProductSearchResult(result);
	}

	/**
	 * ProductSearchResult
	 * @param result
	 * @return RequestPage<UserBean>
	 */
	private RequestPage<UserBean> initRequestPagebyProductSearchResult(
			ProductSearchResult<UserDomain> result) {
		RequestPage<UserBean> reqPage = new RequestPage<UserBean>();
		reqPage.setPageCount((result.getCount()).toString());
		reqPage.setPageNumber(result.getPageNumber());
		reqPage.setProductList(initListBeanByDomain(result.getDomainList()));
		return reqPage;
	}
		
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#getUserById(java.lang.Long)
	 */
	@Override
	public UserBean getUserById(Long userId) {
		UserDomain userDomain = userDao.getById(userId);
		return initBeanByDomain(userDomain);		
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#deleteUser(java.lang.Long)
	 */
	@Override
	public int deleteUser(Long id) {
		return userDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#validUserName(java.lang.String)
	 */
	@Override
	public boolean validUserName(String userName, Long id) {
		return userDao.validUserName(userName, id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.UserService#getUserByCode(java.lang.String)
	 */
	@Override
	public UserBean getUserByCode(String code) {
		UserBean userBean = null;
		UserDomain userDomain = userDao.getUserByCode(code);
		if(userDomain != null) {
			userBean = initBeanByDomain(userDomain);
		}
		return userBean;
	}
}
