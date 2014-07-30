/**
 * 
 */
package am.ucom.dinning.persistence.domain;

import java.util.Date;
import java.util.List;

/**
 * @author siranush
 *
 */
public class SurplusMenuDomain extends BaseDomain {

	/**
	 * Default UUID for serialization...
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Public constructor
	 */
	public SurplusMenuDomain() {

	}

	private Date surplusMenuActualDate;

	private Long surplusMenuUsersId;

	private List<SurplusMenuItemsDomain> surplusMenuItemsDomainList;

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
	 * @return the surplusMenuItemsDomainList
	 */
	public List<SurplusMenuItemsDomain> getSurplusMenuItemsDomainList() {
		return surplusMenuItemsDomainList;
	}

	/**
	 * @param surplusMenuItemsDomainList
	 *            the surplusMenuItemsDomainList to set
	 */
	public void setSurplusMenuItemsDomainList(
			List<SurplusMenuItemsDomain> surplusMenuItemsDomainList) {
		this.surplusMenuItemsDomainList = surplusMenuItemsDomainList;
	}

}
