package am.ucom.dinning.web.action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import am.ucom.dinning.service.DepartamentService;
import am.ucom.dinning.service.impl.*;
import am.ucom.dinning.util.Constants;
import am.ucom.dinning.util.SecurityUtil;
import am.ucom.dinning.web.action.BaseAction;
import am.ucom.dinning.web.model.*;

/**
 * extends baseAction
 * @author arthur
 *
 */
public abstract class UserAction extends BaseAction {
	
	/**
	 * return list CompanyNameBean
	 * @return List<CompanyNameBean>
	 */
	protected List<CompanyNameBean> companyView() {
		return  new CompanyNameServiceImpl().getAllCompanyNames();
	}
	
	/**
	 * return list departments by company id
	 * @param departmentId
	 * @return List<DepartamentBean>
	 */
	protected List<DepartamentBean> departmenView(Long departmentId) {
		DepartamentService service = new DepartamentServiceImpl();
		return service.getDepartamentByDepartamentId(departmentId);		
	}
	
	/**
	 * setting userBean user parameters in page
	 * @param request
	 * @return UserBean
	 */
	protected UserBean setUserParameters(HttpServletRequest request) {
		UserBean userBean = new UserBean();
		String id = request.getParameter("id");
		if(id != null && !id.equals("")) {
			userBean.setId(Long.parseLong(id));
		} else {
			userBean.setPassword(SecurityUtil.generateTmpPassword());
		}
		userBean.setUserName(request.getParameter("username").trim());		
		userBean.setRoleId(request.getParameter("role").trim());
		userBean.setStateId(Constants.USER_STATE_PENDING);
		userBean.setUserDepId(request.getParameter("department").trim());
		userBean.setUserPosition(request.getParameter("position").trim());
		userBean.setDiscountCode("none");
		userBean.setUserEmail(request.getParameter("useremail").trim());		
		return userBean;
	}
	
	/**
	 * requested in page parameters for user
	 * @param pageNumber
	 */
	protected RequestPage<UserBean> requestPaginationParameter(Integer pageNumber) {
		return new UserServiceImpl().getRequestPage(pageNumber);
	}
	
	/**
	 * 
	 * @param userBean
	 * @return List<String> - error list
	 */
	protected List<String> validUser(UserBean userBean) {
		List<String> errors = new ArrayList<String>();
		
		// check if valid email
		if(!validEmail(userBean.getUserEmail())) {
			errors.add("invalid email: ");
		}
		// check is input username
		if(!validUsername(userBean.getUserName(), userBean.getId())) {
			errors.add("please insert unique userName: ");
		}
		// check is select user role
		if(!isSelecet(userBean.getRoleId())) {
			errors.add("please select role: ");
		}
		// check is select department  
		if(!isSelecet(userBean.getUserDepId())) {
			errors.add("please select departament or/and company: ");
		}
		return errors;		
	}
	
	/**
	 * if valid email return true else return false
	 * @param email
	 * @return boolean 
	 */
	private boolean validEmail(String email) {
		if(email != null && !email.equals("")) {
			String validStr = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(validStr);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		} else {
			return true;
		}
	}
	
	/**
	 * if role valid return true else return false
	 * @param selectParam
	 * @return boolean
	 */
	private boolean isSelecet(String selectParam) {
		return !(selectParam == null || selectParam.equals("0"));		
	}
	
	/**
	 * check user unique
	 * @return boolean
	 */
	private boolean validUsername(String userName, Long id) {
		if(userName == null || userName.equals("")) {
			return false;
		}		
		return new UserServiceImpl().validUserName(userName, id);		
	}
}
