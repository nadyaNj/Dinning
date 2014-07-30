package am.ucom.dinning.service.impl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import am.ucom.dinning.persistence.dao.MenuDao;
import am.ucom.dinning.persistence.dao.impl.MenuDaoImpl;
import am.ucom.dinning.persistence.domain.MenuDomain;
import am.ucom.dinning.persistence.domain.MenuItemsDomain;
import am.ucom.dinning.persistence.domain.ProductSearchResult;
import am.ucom.dinning.service.CreateMenuService;
import am.ucom.dinning.service.ProductService;
import am.ucom.dinning.service.UserService;
import am.ucom.dinning.web.model.MenuBean;
import am.ucom.dinning.web.model.MenuItemsBean;
import am.ucom.dinning.web.model.ProductsBean;
import am.ucom.dinning.web.model.RequestPage;

/**
 * Menu create service implementation
 * @author aram
 *
 */

public class CreateMenuServiceImpl implements CreateMenuService {
	
	private MenuDao menuDao;
	
	/**
	 * public constructor
	 */
	public CreateMenuServiceImpl() {
		menuDao = new MenuDaoImpl();
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CreateMenuService#updateMenu(am.ucom.dinning.web.model.MenuBean)
	 */
	@Override
	public int updateMenu(MenuBean menuBean) {
		
		return menuDao.update(initMenuDomainByBean(menuBean));
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CreateMenuService#getProductById(am.ucom.dinning.web.model.MenuBean)
	 */
	@Override
	public List<ProductsBean> getProductByMenu(MenuBean menuBean) {
		List<ProductsBean> prodList = new ArrayList<ProductsBean>();
		List<MenuItemsBean> menuItemsBeanList = menuBean.getMenuItemsBeanList();
		ProductService prodService = new ProductServiceImpl();
		for (MenuItemsBean menuItemsBean : menuItemsBeanList) {
			ProductsBean productsBean = prodService.getProductById(menuItemsBean.getProductId());
			prodList.add(productsBean);
		}
		
		return prodList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CreateMenuService#getMenuByDate(java.util.Date)
	 */
	@Override
	public MenuBean getMenuByDate(Date date) {
		MenuDomain menuDomain = menuDao.getMenuByDate(date);
				
		return initMenuBeanByDomain(menuDomain);
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CreateMenuService#getMenuById(java.lang.Long)
	 */
	@Override
	public MenuBean getMenuById(Long id) {
		MenuDomain menuDomain = menuDao.getById(id);
		
		return initMenuBeanByDomain(menuDomain);
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CreateMenuService#saveMenu(am.ucom.dinning.web.model.MenuBean)
	 */
	@Override
	public Long saveMenu(MenuBean menuBean) {
		MenuDomain menuDomain = initMenuDomainByBean(menuBean);
		
		return menuDao.save(menuDomain);
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.service.CreateMenuService#getAllMenus(java.lang.Integer)
	 */
	@Override
	public RequestPage<MenuBean> getAllMenus(Integer pageNumber) {
		ProductSearchResult<MenuDomain> menuResult = menuDao.getAll(pageNumber);
		RequestPage<MenuBean> requestPage = new RequestPage<MenuBean>();
		Integer count = menuResult.getCount();
		Integer pageCount = 0;
        if (count % 10 == 0) {
            pageCount = count / 10;
        } else {
            pageCount = count / 10 + 1;
        }
        requestPage.setPageCount(pageCount.toString());
        requestPage.setProductList(initMenuBeanListByDomainList(menuResult.getDomainList()));
        requestPage.setPageNumber(menuResult.getPageNumber() + 1);
        return requestPage;
	}
	
	/**
	 * Initialize menuBeanList by given menuDomainList
	 * @param menuDomainList
	 * @return
	 */
	private List<MenuBean> initMenuBeanListByDomainList(List<MenuDomain> menuDomainList) {
		List<MenuBean> menuBeanList = new ArrayList<MenuBean>();
		for (MenuDomain menuDomain: menuDomainList) {
			MenuBean menuBean = initMenuBeanByDomain(menuDomain);
			menuBeanList.add(menuBean);
		}
		return menuBeanList;
	}
	
	/**
	 * initialize menu bean by given menu domain
	 * @param menuDomain
	 * @return
	 */
	private MenuBean initMenuBeanByDomain(MenuDomain menuDomain) {
		MenuBean menuBean = new MenuBean();
		menuBean.setId(menuDomain.getId());
		menuBean.setMenuActualDate(menuDomain.getMenuActualDate());
		menuBean.setMenuUsersId(menuDomain.getMenuUsersId());
		if (menuDomain.getId() != null) {
			UserService userService = new UserServiceImpl();
			menuBean.setMenuUserName(userService.getUserById(menuDomain.getMenuUsersId()).getUserName());
		}
		menuBean.setMenuItemsBeanList(initMenuItemsBeanListByDomainList(menuDomain.getMenuItemsDomainList()));
		
		return menuBean;
	}
	
	/**
	 * initialize menu items bean by given menu items domain
	 * @param menuItemsDomain
	 * @return
	 */
	private MenuItemsBean initMenuItemsBeanByMenuItemsDomain(MenuItemsDomain menuItemsDomain) {
		MenuItemsBean menuItemsBean = new MenuItemsBean();
		menuItemsBean.setId(menuItemsDomain.getId());
		menuItemsBean.setMenuId(menuItemsDomain.getMenuId());
		menuItemsBean.setProductCount(menuItemsDomain.getProductCount());
		menuItemsBean.setProductId(menuItemsDomain.getProductId());
		menuItemsBean.setActive(menuItemsDomain.isActive());
		menuItemsBean.setCreationDate(menuItemsDomain.getCreationDate());
		return menuItemsBean;
	}
	
	/**
	 * initialize menuItemsBeanList By given menuItemsDomainList
	 * @param menuItemsDomainList
	 * @return
	 */
	private List<MenuItemsBean> initMenuItemsBeanListByDomainList(List<MenuItemsDomain> menuItemsDomainList) {
		List<MenuItemsBean> menuItemsBeanList = new ArrayList<MenuItemsBean>();
		if (menuItemsDomainList != null) {
			for (MenuItemsDomain menuItemsDomain: menuItemsDomainList) {
				MenuItemsBean menuItemsBean = initMenuItemsBeanByMenuItemsDomain(menuItemsDomain);
				menuItemsBeanList.add(menuItemsBean);
			}
		} else {
			menuItemsBeanList = null;
		}
		return menuItemsBeanList;
	}
	
	/**
	 * Initialize Menu Domain by given menu Bean
	 * @param menuBean
	 * @return
	 */
	private MenuDomain initMenuDomainByBean(MenuBean menuBean) {
		MenuDomain menuDomain = new MenuDomain();
		menuDomain.setId(menuBean.getId());
		menuDomain.setMenuActualDate(menuBean.getMenuActualDate());
		menuDomain.setMenuUsersId(menuBean.getMenuUsersId());
		menuDomain.setMenuItemsDomainList(initMenuItemsDomainListByBeanList(menuBean.getMenuItemsBeanList()));
		
		return menuDomain;
		
	}
	
	/**
	 * Initialize menuitemsdomain by given menuItemsBean
	 * @param menuItemsBean
	 * @return
	 */
	private MenuItemsDomain initMenuItemsDomainByBean(MenuItemsBean menuItemsBean) {
		MenuItemsDomain menuItemsDomain = new MenuItemsDomain();
		menuItemsDomain.setId(menuItemsBean.getId());
		menuItemsDomain.setMenuId(menuItemsBean.getMenuId());
		menuItemsDomain.setProductCount(menuItemsBean.getProductCount());
		menuItemsDomain.setProductId(menuItemsBean.getProductId());
		menuItemsDomain.setActive(menuItemsBean.isActive());
		menuItemsDomain.setCreationDate(new Timestamp(new java.util.Date().getTime()));
		return menuItemsDomain;
	}
	
	/**
	 * initialize menuItemsDomainList by given menuItemsBeanList
	 * @param menuItemsBeanList
	 * @return
	 */
	private List<MenuItemsDomain> initMenuItemsDomainListByBeanList(List<MenuItemsBean> menuItemsBeanList) {
		List<MenuItemsDomain> menuItemsDomainList = new ArrayList<MenuItemsDomain>();
		for (MenuItemsBean menuItemsBean: menuItemsBeanList) {
			MenuItemsDomain menuItemsDomain = initMenuItemsDomainByBean(menuItemsBean);
			menuItemsDomainList.add(menuItemsDomain);
		}
		
		return menuItemsDomainList;
		
	}


		
}
