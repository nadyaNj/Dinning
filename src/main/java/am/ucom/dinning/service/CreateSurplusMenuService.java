/**
 * 
 */
package am.ucom.dinning.service;

import java.util.List;

import am.ucom.dinning.web.model.SurplusMenuBean;
import am.ucom.dinning.web.model.SurplusMenuItemsBean;

/**
 * @author siranush
 *
 */
public interface CreateSurplusMenuService {
	/**
	 * for save menu.
	 * 
	 * @param menuBean
	 * @return id - Long: saved menu id
	 */
	Long saveSurplusMenu(SurplusMenuBean surplusMenuBean);
	
	/**
	 * get surplus menu domain list
	 * 
	 * @return List<SurplusMenuBean> - list of SurplusMenuBean class
	 */
	List<SurplusMenuBean> getSurplusMenuBeanList();
	
	
	/**
	 * get surplus menu parameters by given id
	 * 
	 * @param id - Long: surplus menu bean id
	 * @return SurplusMenuBean - SurplusMenuBean: SurplusMenuBean class
	 */
	SurplusMenuBean getSurplusMenuById(Long id);

}
