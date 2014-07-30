package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.dao.MenuDao;
import am.ucom.dinning.persistence.domain.MenuDomain;
import am.ucom.dinning.persistence.domain.MenuItemsDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.closeResources;
import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.getConnection;

import am.ucom.dinning.persistence.domain.ProductSearchResult;
/**
 * Menu DAO implementation class
 *
 * @author aram
 */

public class MenuDaoImpl implements MenuDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(MenuDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.MenuDao#getMenuByDate(java.util.Date)
	 */
	@Override
	public MenuDomain getMenuByDate(java.util.Date date) {
		MenuDomain menuDomain = new MenuDomain();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
				
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			sql.append(SQL_SELECT).append("*").append(SQL_FROM).append(MENU_TABLE).
				append(SQL_WHERE).append(MENU_ACTUAL_DATE).append("= ").append("'").append(new java.sql.Date(date.getTime())).append("'");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				menuDomain.setId(rs.getLong(1));
				menuDomain.setMenuActualDate(rs.getDate(2));
				menuDomain.setMenuUsersId(rs.getLong(3));
				menuDomain.setMenuItemsDomainList(getMenuItemsById(menuDomain.getId()));
			}			
						
		}catch (SQLException e) {
			LOG.error("Get menu by date sql error: " + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Get menu by date naming error: " + e.getMessage(), e);
		} finally {
			closeResources(rs, stmt, connection);
		} 	
		return menuDomain;
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public Long save(MenuDomain entity) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Long newMenuId = null;
		StringBuilder sql = new StringBuilder();
		ResultSet generatedKeys = null;
		List<MenuItemsDomain> menuItemsDomainList =null;
		
		sql.append(SQL_INSERT).append(MENU_TABLE).append("(").
			append(MENU_ACTUAL_DATE).append(",").append(MENU_USERS_ID).
			append(")").append(SQL_VALUES).append("(?,?)");
		
		try { 
			connection = getConnection();
			pstmt = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, new java.sql.Date(entity.getMenuActualDate().getTime()));
			pstmt.setLong(2, entity.getMenuUsersId());
			
			pstmt.executeUpdate();
			entity.setId(getMenuIdByDate(entity));
			menuItemsDomainList = entity.getMenuItemsDomainList();
			
			for (MenuItemsDomain menuItemsDomain: menuItemsDomainList) {
				menuItemsDomain.setMenuId(getMenuIdByDate(entity));
			}
			
			insertMenuItems(menuItemsDomainList);
		} catch (SQLException e) {
			LOG.error("Menu save sql error: " + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Menu save naming error: " + e.getMessage(), e);
		} finally {
			closeResources(generatedKeys, pstmt, connection);
		} 	
		return newMenuId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public int update(MenuDomain menuDomain) {

		List<MenuItemsDomain> list1 = new ArrayList<MenuItemsDomain>();
	
		Map<Long, MenuItemsDomain> hashMap = new HashMap<Long, MenuItemsDomain>();
		for (int i=0; i < menuDomain.getMenuItemsDomainList().size(); i++) {
			hashMap.put(menuDomain.getMenuItemsDomainList().get(i).getProductId(), menuDomain.getMenuItemsDomainList().get(i));
		}
		
		for (int j=0; j < getMenuItemsById(menuDomain.getId()).size(); j++) {
			if (!hashMap.containsKey(getMenuItemsById(menuDomain.getId()).get(j).getProductId())) {
				list1.add(getMenuItemsById(menuDomain.getId()).get(j));
			} else {
				hashMap.remove(getMenuItemsById(menuDomain.getId()).get(j).getProductId());
			}
		}
		List<MenuItemsDomain> list2 = new ArrayList<MenuItemsDomain>(hashMap.values());
		
		updateMenuItems(list1);
		insertMenuItems(list2);
		return 1;
	}
	
	/**
	 * 
	 * @param menuItemsDomainList
	 * @return
	 */
	private int updateMenuItems(List<MenuItemsDomain> menuItemsDomainList) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append(SQL_UPDATE).append(MENU_ITEMS_TABLE).append(SQL_SET).
			append(MENU_ITEM_ACTIVE).append("=").append("?").
			append(SQL_WHERE).append(MENU_ITEMS_MENU_ID).append("=").append("?").
			append(SQL_AND).append(MENU_PRODUCT_ID).append("=").append("?");
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql.toString());
			for (MenuItemsDomain menuItemsDomain: menuItemsDomainList) {
			pstmt.setInt(1, 0);
			pstmt.setLong(2, menuItemsDomain.getMenuId());
			pstmt.setLong(3, menuItemsDomain.getProductId());
			pstmt.executeUpdate();
			}
			
		}catch (SQLException e) {
			LOG.error("Menu items update sql error" + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Menu items update naming error" + e.getMessage(), e);
		} finally {
			closeResources( pstmt, connection);
		}
		
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.MenuDao#getAll(java.lang.Integer)
	 */
	@Override
	public ProductSearchResult<MenuDomain> getAll(Integer pageNumber) {
		
		ProductSearchResult<MenuDomain> menuResult = new ProductSearchResult<MenuDomain>();
		
		List<MenuDomain> menuDomainList = new ArrayList<MenuDomain>();
		MenuDomain menuDomain = new MenuDomain();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		Integer count = null;
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			StringBuilder sql = new StringBuilder();
			stmt = connection.createStatement();
			sql.append(SQL_SELECT).append("*").append(SQL_FROM).append(MENU_TABLE);
			
			StringBuilder sqlCount = new StringBuilder();
			sqlCount.append(SQL_SELECT).append("COUNT(*)").append(SQL_FROM).append("(").
					 append(sql).append(")").append("as Result");
			rs = stmt.executeQuery(sqlCount.toString());
			if (rs.next()) {
				count = rs.getInt(1);
			}
			int countPage = pageNumber * 10;			
			if(countPage >= count && countPage != 0) {
				pageNumber = pageNumber - 1;
			}
			
			rs = null;
			String sqlCommand = sql.toString() + getByPaging(pageNumber);
			rs = stmt.executeQuery(sqlCommand.toString());
			while (rs.next()) {
				menuDomain = new MenuDomain();
				menuDomain.setId(rs.getLong(1));
				menuDomain.setMenuActualDate(rs.getDate(2));
				menuDomain.setMenuUsersId(rs.getLong(3));
				menuDomain.setMenuItemsDomainList(getMenuItemsById(menuDomain.getId()));
				
				menuDomainList.add(menuDomain);
			}
			menuResult.setDomainList(menuDomainList);
			menuResult.setCount(count);
			menuResult.setPageNumber(pageNumber);
		
		}catch (SQLException e) {
			LOG.error("Get all menus sql error" + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Get all menus naming error" + e.getMessage(), e);
		} finally {
			closeResources(rs, stmt, connection);
		}
		return menuResult;
	}
	
	/*
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.GenericDao#getById(java.lang.Long)
	 */
	@Override
	public MenuDomain getById(Long id) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		MenuDomain menuDomain = new MenuDomain();
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append(SQL_SELECT).append("*").append(SQL_FROM).append(MENU_TABLE).
				append(SQL_WHERE).append(MENU_ID).append("= ").append(id);
			
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				menuDomain.setId(rs.getLong(1));
				menuDomain.setMenuActualDate(rs.getDate(2));
				menuDomain.setMenuUsersId(rs.getLong(3));
				menuDomain.setMenuItemsDomainList(getMenuItemsById(menuDomain.getId()));
			}
			
		}catch (SQLException e) {
			LOG.error("Getting menu by id sql error" + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Getting menu by id naming error" + e.getMessage(), e);
		} finally {
			closeResources(rs, stmt, connection);
		}
		return menuDomain;
	}
	
	/**
	 * This method returns Menu Items List by given menu id
	 * @param id - Long
	 * @return menuItemsDomainList - List<MenuItemsDomain>
	 */
	private List<MenuItemsDomain> getMenuItemsById(Long id) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MenuItemsDomain> menuItemsDomainList = new ArrayList<MenuItemsDomain>();
		MenuItemsDomain menuItemsDomain = new MenuItemsDomain();
		
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			StringBuilder sqlQuery = new StringBuilder();
			
			sqlQuery.append(SQL_SELECT).append("*").append(SQL_FROM).append(MENU_ITEMS_TABLE).
					 append(SQL_WHERE).append(MENU_ITEMS_MENU_ID).append("= ").append(id).
					 append(SQL_AND).append(MENU_ITEM_ACTIVE).append("= 1");
			
			rs = stmt.executeQuery(sqlQuery.toString());
			
			while (rs.next()) {
				menuItemsDomain = new MenuItemsDomain();
				menuItemsDomain.setId(rs.getLong(1));
				menuItemsDomain.setMenuId(rs.getLong(2));
				menuItemsDomain.setProductId(rs.getLong(3));
				menuItemsDomain.setProductCount(rs.getInt(4));
				menuItemsDomain.setActive(rs.getBoolean(5));
				menuItemsDomain.setCreationDate(rs.getTimestamp(6));
				menuItemsDomainList.add(menuItemsDomain);
			}
						
		}catch (SQLException e) {
			LOG.error("Getting menu items sql error" + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Getting menu items naming error" + e.getMessage(), e);
		} finally {
			closeResources(rs, stmt, connection);
		}
		return menuItemsDomainList;		
	}
	
	
	
	/**
	 * This method return menu Id by given domain's date
	 * @param entity - MenuDoamin
	 * @return menuId - Long
	 */
	private Long getMenuIdByDate(MenuDomain entity){
		Long menuId=null;	
		Date date = new java.sql.Date(entity.getMenuActualDate().getTime());
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();			
			StringBuilder querySelect = new StringBuilder();
			querySelect.append(SQL_SELECT).append(MENU_ID).append(SQL_FROM).append(MENU_TABLE).
						append(SQL_WHERE).append(MENU_ACTUAL_DATE).append(" = ").append("'").append(date).append("'");
			
			String sqlString = querySelect.toString();
	        rs = statement.executeQuery(sqlString);
	        if (rs.next()) {
	        	menuId = rs.getLong(1);
	        }
			
		} catch (SQLException e) {
			LOG.error("Getting menu id sql error: " + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Getting menu id naming error: " + e.getMessage(), e);
		} finally {
            closeResources(rs, statement, connection);
        } 
		return menuId;
	}
	
	/**
	 * This is private method to insert menu items to database
	 * @param menuItemsDomainList
	 * 
	 */
	private void insertMenuItems(List<MenuItemsDomain> menuItemsDomainList) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append(SQL_INSERT).append(MENU_ITEMS_TABLE).append("(").
			append(MENU_ITEMS_MENU_ID).append(",").append(MENU_PRODUCT_ID).append(",").
			append(MENU_PRODUCT_COUNT).append(",").append(MENU_ITEM_ACTIVE).append(",").append(MENU_CREATION_DATE).
			append(")").append(SQL_VALUES).append("(?, ?, ?, ?, ?)");
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql.toString());
			for (MenuItemsDomain menuItemsDomain: menuItemsDomainList) {
				pstmt = connection.prepareStatement(sql.toString());
				pstmt.setLong(1, menuItemsDomain.getMenuId());
				pstmt.setLong(2, menuItemsDomain.getProductId());
				pstmt.setInt(3, menuItemsDomain.getProductCount());
				pstmt.setInt(4, 1);
				pstmt.setTimestamp(5, menuItemsDomain.getCreationDate());
			
				pstmt.executeUpdate();
			}
			
		}catch (SQLException e) {
			LOG.error("Inserting menu items sql error :" + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Inserting menu items naming error :" + e.getMessage(), e);
		} finally {
			closeResources(pstmt, connection);
		}
	}
	
	/**
	 * get by page limit
	 * @param pageId
	 * @param sql
	 */
	private String  getByPaging (Integer pageId){
		StringBuilder sql = new StringBuilder();
 		Integer limitMin = pageId * 10;
 		Integer limitMax = 10; 		
 		sql.append(SQL_ORDER_BY).append(MENU_ACTUAL_DATE).append(ORDER_DESC).
 			append(SQL_LIMIT).append(limitMin).append(" , ").append(limitMax);
 	    return sql.toString().trim();
	}
	

	
	@Override
	public void batchUpdate(List<MenuDomain> entities, Long[] id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void batchDelete(List<String> id) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public List<String> getAllNames(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void batchSave(List<MenuDomain> entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MenuDomain> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
