/**
 * 
 */
package am.ucom.dinning.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import am.ucom.dinning.persistence.dao.SurplusMenuDao;
import am.ucom.dinning.persistence.dao.impl.SurplusMenuDaoImpl;
import am.ucom.dinning.persistence.domain.SurplusMenuDomain;
import am.ucom.dinning.persistence.domain.SurplusMenuItemsDomain;
import am.ucom.dinning.service.CreateSurplusMenuService;
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.MenuItemsBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.SurplusMenuBean;
import am.ucom.dinning.web.model.SurplusMenuItemsBean;

/**
 * @author siranush
 *
 */
public class CreateSurplusMenuServiceImpl implements CreateSurplusMenuService {

	private SurplusMenuDao surplusMenuDao;

	/**
	 * public constructor
	 */
	public CreateSurplusMenuServiceImpl() {
		surplusMenuDao = new SurplusMenuDaoImpl();
	}

	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CreateSurplusMenuService
	 */
	@Override
	public Long saveSurplusMenu(SurplusMenuBean surplusMenuBean) {
		SurplusMenuDomain surplusMenuDomain = initSurplusMenuDomainByBean(surplusMenuBean);

		return surplusMenuDao.save(surplusMenuDomain);
	}
	
	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CreateSurplusMenuService
	 */
	@Override
	public List<SurplusMenuBean> getSurplusMenuBeanList() {
		return initSurplusMenuBeanListByDomainList(surplusMenuDao.getSurplusMenuDomainList());
	}
	
	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CreateSurplusMenuService
	 */
	@Override
	public SurplusMenuBean getSurplusMenuById(Long id) {
		return initSurplusMenuBeanByDomain(surplusMenuDao.getById(id));
	}
	
	public List<SurplusMenuItemsBean> getSurplusMenuItemsById(Long id) {		
		return initSurplusMenuItemsBeanListByDomainList(surplusMenuDao.getSurplusMenuItemsDomainListById(id));
	}
	
	public List<ProductsBean> getProductBySurplusMenu(Long id) {
		List<ProductsBean> prodList = new ArrayList<ProductsBean>();
		List<SurplusMenuItemsBean> surplusMenuItemsBeans = 
				initSurplusMenuItemsBeanListByDomainList(surplusMenuDao.getSurplusMenuItemsDomainListById(id));
		//List<MenuItemsBean> menuItemsBeanList = menuBean.getMenuItemsBeanList();
		ProductService prodService = new ProductServiceImpl();
		for (SurplusMenuItemsBean surplusMenuItemsBean : surplusMenuItemsBeans) {
			ProductsBean productsBean = prodService.getProductById(surplusMenuItemsBean.getSurplusProductId());
			prodList.add(productsBean);
		}
		
		return prodList;
	}

	/**
	 * initialize menu bean by given menu domain
	 * 
	 * @param surplusMenuDomain
	 * @return SurplusMenuBean
	 */
	private SurplusMenuBean initSurplusMenuBeanByDomain(SurplusMenuDomain surplusMenuDomain) {
		SurplusMenuBean surplusMenuBean = new SurplusMenuBean();
		surplusMenuBean.setId(surplusMenuDomain.getId());
		surplusMenuBean.setSurplusMenuActualDate(surplusMenuDomain
				.getSurplusMenuActualDate());
		surplusMenuBean.setSurplusMenuUsersId(surplusMenuDomain
				.getSurplusMenuUsersId());
		surplusMenuBean.setSurplusMenuItemsBeanList(initSurplusMenuItemsBeanListByDomainList(surplusMenuDomain
						.getSurplusMenuItemsDomainList()));

		return surplusMenuBean;
	}

	/**
	 * initialize menu bean by given menu domain
	 * 
	 * @param surplusMenuDomain
	 * @return SurplusMenuBean
	 */
	private List<SurplusMenuBean> initSurplusMenuBeanListByDomainList(
				List<SurplusMenuDomain> surplusMenuDomainList) {
		List<SurplusMenuBean> surplusMenuBeanList = new ArrayList<SurplusMenuBean>();
		
		if (surplusMenuDomainList != null) {
			for (SurplusMenuDomain surplusMenuDomain : surplusMenuDomainList) {
				SurplusMenuBean surplusMenuBean = initSurplusMenuBeanByDomain(surplusMenuDomain);
				surplusMenuBeanList.add(surplusMenuBean);
			}
		} else {
			surplusMenuBeanList = null;
		}
		return surplusMenuBeanList;
	}
	
	
	
	/**
	 * initialize menu items bean by given menu items domain
	 * 
	 * @param menuItemsDomain
	 * @return
	 */
	private SurplusMenuItemsBean initSurplusMenuItemsBeanByMenuItemsDomain(
			SurplusMenuItemsDomain surplusMenuItemsDomain) {
		SurplusMenuItemsBean surplusmenuItemsBean = new SurplusMenuItemsBean();
		surplusmenuItemsBean.setId(surplusMenuItemsDomain.getId());
		surplusmenuItemsBean.setSurplusMenuCreationDate(surplusMenuItemsDomain.getSurplusMenuCreationDate());
		surplusmenuItemsBean.setSurplusMenuId(surplusMenuItemsDomain.getSurplusMenuId());
		surplusmenuItemsBean.setSurplusProductCount(surplusMenuItemsDomain.getSurplusProductCount());
		surplusmenuItemsBean.setSurplusProductId(surplusMenuItemsDomain.getSurplusProductId());
		
		return surplusmenuItemsBean;
	}

	/**
	 * initialize menuItemsBeanList By given menuItemsDomainList
	 * 
	 * @param menuItemsDomainList
	 * @return
	 */
	private List<SurplusMenuItemsBean> initSurplusMenuItemsBeanListByDomainList(
			List<SurplusMenuItemsDomain> surplusMenuItemsDomainList) {
		List<SurplusMenuItemsBean> surplusMenuItemsBeanList = new ArrayList<SurplusMenuItemsBean>();
		if (surplusMenuItemsDomainList != null) {
			for (SurplusMenuItemsDomain surplusMenuItemsDomain : surplusMenuItemsDomainList) {
				SurplusMenuItemsBean surplusMenuItemsBean = initSurplusMenuItemsBeanByMenuItemsDomain(surplusMenuItemsDomain);
				surplusMenuItemsBeanList.add(surplusMenuItemsBean);
			}
		} else {
			surplusMenuItemsBeanList = null;
		}
		return surplusMenuItemsBeanList;
	}

	/**
	 * Initialize Menu Domain by given menu Bean
	 * 
	 * @param menuBean
	 * @return
	 */
	private SurplusMenuDomain initSurplusMenuDomainByBean(SurplusMenuBean surplusMenuBean) {
		SurplusMenuDomain surplusMenuDomain = new SurplusMenuDomain();
		surplusMenuDomain.setId(surplusMenuBean.getId());
		surplusMenuDomain.setSurplusMenuActualDate(surplusMenuBean.getSurplusMenuActualDate());
		surplusMenuDomain.setSurplusMenuUsersId(surplusMenuBean.getSurplusMenuUsersId());
		surplusMenuDomain.setSurplusMenuItemsDomainList(initSurplusMenuItemsDomainListByBeanList(surplusMenuBean
						.getSurplusMenuItemsBeanList()));

		return surplusMenuDomain;

	}

	/**
	 * Initialize menuitemsdomain by given menuItemsBean
	 * 
	 * @param menuItemsBean
	 * @returnM
	 */
	private SurplusMenuItemsDomain initSurplusMenuItemsDomainByBean(
			SurplusMenuItemsBean surplusMenuItemsBean) {
		SurplusMenuItemsDomain surplusMenuItemsDomain = new SurplusMenuItemsDomain();
		
		surplusMenuItemsDomain.setId(surplusMenuItemsBean.getId());
		surplusMenuItemsDomain.setSurplusMenuId(surplusMenuItemsBean.getSurplusMenuId());
		surplusMenuItemsDomain.setSurplusProductCount(surplusMenuItemsBean.getSurplusProductCount());
		surplusMenuItemsDomain.setSurplusProductId(surplusMenuItemsBean.getSurplusProductId());
		surplusMenuItemsDomain.setSurplusMenuCreationDate(new Timestamp(new java.util.Date().getTime()));
		
		return surplusMenuItemsDomain;
	}

	/**
	 * initialize menuItemsDomainList by given menuItemsBeanList
	 * 
	 * @param menuItemsBeanList
	 * @return
	 */
	private List<SurplusMenuItemsDomain> initSurplusMenuItemsDomainListByBeanList(
			List<SurplusMenuItemsBean> surplusMenuItemsBeanList) {
		List<SurplusMenuItemsDomain> surplusMenuItemsDomainList = new ArrayList<SurplusMenuItemsDomain>();
		
		for (SurplusMenuItemsBean surpluMenuItemsBean : surplusMenuItemsBeanList) {
			SurplusMenuItemsDomain surplusMenuItemsDomain = initSurplusMenuItemsDomainByBean(surpluMenuItemsBean);
			surplusMenuItemsDomainList.add(surplusMenuItemsDomain);
		}

		return surplusMenuItemsDomainList;

	}

}
