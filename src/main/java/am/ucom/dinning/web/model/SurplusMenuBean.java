package am.ucom.dinning.web.model;

import java.util.Date;
import java.util.List;


/**
 * @author siranush
 * 
 */
public class SurplusMenuBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Public constructor
	 */
	public SurplusMenuBean() {

	}

	private Date surplusMenuActualDate;

	private Long surplusMenuUsersId;

	private List<SurplusMenuItemsBean> surplusMenuItemsBeanList;

	/**
	 * @return the surplusMenuActualDate
	 */
	public Date getSurplusMenuActualDate() {
		return surplusMenuActualDate;
	}

	/**
	 * @param surplusMenuActualDate
	 *            the surplusMenuActualDate to set
	 */
	public void setSurplusMenuActualDate(Date surplusMenuActualDate) {
		this.surplusMenuActualDate = surplusMenuActualDate;
	}

	/**
	 * @return the surplusMenuUsersId
	 */
	public Long getSurplusMenuUsersId() {
		return surplusMenuUsersId;
	}

	/**
	 * @param surplusMenuUsersId
	 *            the surplusMenuUsersId to set
	 */
	public void setSurplusMenuUsersId(Long surplusMenuUsersId) {
		this.surplusMenuUsersId = surplusMenuUsersId;
	}

	/**
	 * @return the surplusMenuItemsBeanList
	 */
	public List<SurplusMenuItemsBean> getSurplusMenuItemsBeanList() {
		return surplusMenuItemsBeanList;
	}

	/**
	 * @param surplusMenuItemsBeanList the surplusMenuItemsBeanList to set
	 */
	public void setSurplusMenuItemsBeanList(List<SurplusMenuItemsBean> surplusMenuItemsBeanList) {
		this.surplusMenuItemsBeanList = surplusMenuItemsBeanList;
	}

	

}
