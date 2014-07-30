package am.ucom.dinning.web.model;

import java.sql.Timestamp;

/**
 * @author siranush
 * 
 */
public class SurplusMenuItemsBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * public constructor
	 */
	public SurplusMenuItemsBean() {

	}

	private Long surplusMenuId;

	private Long surplusProductId;

	private int surplusProductCount;

	private Timestamp surplusMenuCreationDate;

	/**
	 * @return the surplusMenuId
	 */
	public Long getSurplusMenuId() {
		return surplusMenuId;
	}

	/**
	 * @param surplusMenuId
	 *            the surplusMenuId to set
	 */
	public void setSurplusMenuId(Long surplusMenuId) {
		this.surplusMenuId = surplusMenuId;
	}

	/**
	 * @return the surplusProductId
	 */
	public Long getSurplusProductId() {
		return surplusProductId;
	}

	/**
	 * @param surplusProductId
	 *            the surplusProductId to set
	 */
	public void setSurplusProductId(Long surplusProductId) {
		this.surplusProductId = surplusProductId;
	}

	/**
	 * @return the surplusProductCount
	 */
	public int getSurplusProductCount() {
		return surplusProductCount;
	}

	/**
	 * @param surplusProductCount
	 *            the surplusProductCount to set
	 */
	public void setSurplusProductCount(int surplusProductCount) {
		this.surplusProductCount = surplusProductCount;
	}

	/**
	 * @return the surplusMenuCreationDate
	 */
	public Timestamp getSurplusMenuCreationDate() {
		return surplusMenuCreationDate;
	}

	/**
	 * @param surplusMenuCreationDate
	 *            the surplusMenuCreationDate to set
	 */
	public void setSurplusMenuCreationDate(Timestamp surplusMenuCreationDate) {
		this.surplusMenuCreationDate = surplusMenuCreationDate;
	}

}
