/**
 * 
 */
package am.ucom.dinning.persistence.dao.impl;

import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.closeResources;
import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import am.ucom.dinning.persistence.dao.SurplusMenuDao;
import am.ucom.dinning.persistence.domain.MenuDomain;
import am.ucom.dinning.persistence.domain.MenuItemsDomain;
import am.ucom.dinning.persistence.domain.SurplusMenuDomain;
import am.ucom.dinning.persistence.domain.SurplusMenuItemsDomain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author siranush
 *
 */
public class SurplusMenuDaoImpl implements SurplusMenuDao {

	public static final Logger LOG = LoggerFactory.getLogger(MenuDaoImpl.class);

	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.SurplusMenuDao
	 */
	@Override
	public SurplusMenuDomain getSurplusMenuByDate(Date date) {
		SurplusMenuDomain surplusMenuDomain = new SurplusMenuDomain();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			connection = getConnection();
			stmt = connection.createStatement();
			sql.append(SQL_SELECT)
				.append("*")
				.append(SQL_FROM)
				.append(SURPLUS_MENU_TABLE)
				.append(SQL_WHERE)
				.append(SURPLUS_MENU_ACTUAL_DATE)
				.append("= ")
				.append("'")
				.append(date)
				.append("'");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				surplusMenuDomain.setId(rs.getLong(1));
				surplusMenuDomain.setSurplusMenuActualDate(rs.getDate(2));
				surplusMenuDomain.setSurplusMenuUsersId(rs.getLong(3));
				surplusMenuDomain.setSurplusMenuItemsDomainList(getSurplusMenuItemsById(surplusMenuDomain.getId()));
			}

		} catch (SQLException e) {
			LOG.error("Get menu by date sql error: " + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Get menu by date naming error: " + e.getMessage(), e);
		} finally {
			closeResources(rs, stmt, connection);
		}
		return surplusMenuDomain;
	}
	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.GenericDao
	 */
	@Override
	public void batchDelete(List<String> id) {
		// TODO Auto-generated method stub

	}
	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.GenericDao
	 */
	@Override
	public void batchSave(List<SurplusMenuDomain> entity) {
		// TODO Auto-generated method stub

	}

	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.GenericDao
	 */
	@Override
	public void batchUpdate(List<SurplusMenuDomain> entities, Long[] id) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.GenericDao
	 */
	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.SurplusMenuDao
	 */
	@Override
	public List<SurplusMenuDomain> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.SurplusMenuDao
	 */
	@Override
	public List<String> getAllNames(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.SurplusMenuDao
	 */
	@Override
	public SurplusMenuDomain getById(Long id) {
		SurplusMenuDomain surplusMenuDomain = new SurplusMenuDomain();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			connection = getConnection();
			stmt = connection.createStatement();
			sql.append(SQL_SELECT)
				.append("*")
				.append(SQL_FROM)
				.append(SURPLUS_MENU_TABLE)
				.append(SQL_WHERE)
				.append(SURPLUS_MENU_ID)
				.append("= ")
				.append("'")
				.append(id)
				.append("'");
			rs = stmt.executeQuery(sql.toString());
			if (rs.next()) {
				surplusMenuDomain.setId(rs.getLong(1));
				surplusMenuDomain.setSurplusMenuActualDate(rs.getDate(2));
				surplusMenuDomain.setSurplusMenuUsersId(rs.getLong(3));
				surplusMenuDomain.setSurplusMenuItemsDomainList(getSurplusMenuItemsById(surplusMenuDomain.getId()));
			}

		} catch (SQLException e) {
			LOG.error("Get menu by date sql error: " + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Get menu by date naming error: " + e.getMessage(), e);
		} finally {
			closeResources(rs, stmt, connection);
		}
		return surplusMenuDomain;
	}

	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.SurplusMenuDao
	 */
	@Override
	public Long save(SurplusMenuDomain entity) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Long newSurplusMenuId = null;
		StringBuilder sql = new StringBuilder();
		ResultSet generatedKeys = null;
		List<SurplusMenuItemsDomain> surplusMenuItemsDomainList = null;

		sql.append(SQL_INSERT).append(SURPLUS_MENU_TABLE).append("(").append(
				SURPLUS_MENU_ACTUAL_DATE).append(",").append(
				SURPLUS_MENU_USERS_ID).append(")")
				.append(SQL_VALUES).append("(?,?)");

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, new java.sql.Date(entity.getSurplusMenuActualDate().getTime()));
			pstmt.setLong(2, entity.getSurplusMenuUsersId());

			pstmt.executeUpdate();
			entity.setId(getSurplusMenuIdByDate(entity));
			surplusMenuItemsDomainList = entity.getSurplusMenuItemsDomainList();

			for (SurplusMenuItemsDomain menuItemsDomain : surplusMenuItemsDomainList) {
				menuItemsDomain.setSurplusMenuId(getSurplusMenuIdByDate(entity));
			}

			insertMenuItems(surplusMenuItemsDomainList);
		} catch (SQLException e) {
			LOG.error("Menu save sql error: " + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Menu save naming error: " + e.getMessage(), e);
		} finally {
			closeResources(generatedKeys, pstmt, connection);
		}
		return newSurplusMenuId;

	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	private Long getSurplusMenuIdByDate(SurplusMenuDomain entity) {
		Long surplusMenuId = null;
		Date date = new java.sql.Date(entity.getSurplusMenuActualDate()
				.getTime());
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			StringBuilder querySelect = new StringBuilder();
			querySelect.append(SQL_SELECT)
					.append(SURPLUS_MENU_ID)
					.append(SQL_FROM)
					.append(SURPLUS_MENU_TABLE)
					.append(SQL_WHERE)
					.append(SURPLUS_MENU_ACTUAL_DATE)
					.append(" = ").append("'")
					.append(date).append("'");

			String sqlString = querySelect.toString();
			rs = statement.executeQuery(sqlString);
			
			if (rs.next()) {
				surplusMenuId = rs.getLong(1);
			}

		} catch (SQLException e) {
			LOG.error("Getting menu id sql error: " + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Getting menu id naming error: " + e.getMessage(), e);
		} finally {
			closeResources(rs, statement, connection);
		}
		return surplusMenuId;
	}

	/**
	 * 
	 * @param surplusMenuItemsDomainList
	 */
	private void insertMenuItems(List<SurplusMenuItemsDomain> surplusMenuItemsDomainList) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		Long newSurplusMenuId = null;
		StringBuilder sql = new StringBuilder();
		ResultSet generatedKeys = null;
	
		sql.append(SQL_INSERT)
				.append(SURPLUS_MENU_ITEMS_TABLE).append("(")
				.append(SURPLUS_MENU_ITEMS_MENU_ID).append(",")
				.append(SURPLUS_MENU_PRODUCT_ID).append(",")
				.append(SURPLUS_MENU_PRODUCT_COUNT).append(",")
				.append(SURPLUS_MENU_ITEMS_CREATION_DATE)
				.append(")")
				.append(SQL_VALUES).append("(?,?,?,?)");

		try {
			connection = getConnection();
			for (SurplusMenuItemsDomain surplusMenuItemsDomain : surplusMenuItemsDomainList) {
				pstmt = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setLong(1, surplusMenuItemsDomain.getSurplusMenuId());
				pstmt.setLong(2, surplusMenuItemsDomain.getSurplusProductId());
				pstmt.setInt(3, surplusMenuItemsDomain.getSurplusProductCount());
				pstmt.setTimestamp(4, surplusMenuItemsDomain.getSurplusMenuCreationDate());

				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			LOG.error("Menu save sql error: " + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Menu save naming error: " + e.getMessage(), e);
		} finally {
			closeResources(generatedKeys, pstmt, connection);
		}
	}

	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.SurplusMenuDao
	 */
	@Override
	public int update(SurplusMenuDomain entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * This method returns Menu Items List by given menu id
	 * 
	 * @param id - Long
	 * @return menuItemsDomainList - List<MenuItemsDomain>
	 */
	private List<SurplusMenuItemsDomain> getSurplusMenuItemsById(Long id) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<SurplusMenuItemsDomain> surplusMenuItemsDomainList = new ArrayList<SurplusMenuItemsDomain>();
		SurplusMenuItemsDomain surplusMenuItemsDomain = new SurplusMenuItemsDomain();

		try {
			connection = getConnection();
			stmt = connection.createStatement();
			StringBuilder sqlQuery = new StringBuilder();

			sqlQuery.append(SQL_SELECT).append("*").append(SQL_FROM).append(
					SURPLUS_MENU_ITEMS_TABLE).append(SQL_WHERE).append(
					SURPLUS_MENU_ITEMS_MENU_ID).append("= ").append(id);

			rs = stmt.executeQuery(sqlQuery.toString());

			while (rs.next()) {
				surplusMenuItemsDomain = new SurplusMenuItemsDomain();
				surplusMenuItemsDomain.setSurplusMenuId(rs.getLong(2));
				surplusMenuItemsDomain.setSurplusProductId(rs.getLong(3));
				surplusMenuItemsDomain.setSurplusProductCount(rs.getInt(4));
				surplusMenuItemsDomain.setSurplusMenuCreationDate(rs
						.getTimestamp(6));
				surplusMenuItemsDomainList.add(surplusMenuItemsDomain);
			}

		} catch (SQLException e) {
			LOG.error("Getting menu items sql error" + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Getting menu items naming error" + e.getMessage(), e);
		} finally {
			closeResources(rs, stmt, connection);
		}
		return surplusMenuItemsDomainList;
	}
	
	/**
	 * (non-Javadoc)
	 * @see am.ucom.dinning.persistence.dao.SurplusMenuDao
	 */
	@Override
	public List<SurplusMenuDomain> getSurplusMenuDomainList() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<SurplusMenuDomain> surplusMenuDomainList = new ArrayList<SurplusMenuDomain>();
		SurplusMenuDomain surplusMenuDomain = new SurplusMenuDomain();

		try {
			connection = getConnection();
			stmt = connection.createStatement();
			StringBuilder sqlQuery = new StringBuilder();

			sqlQuery.append(SQL_SELECT)
				.append("*")
				.append(SQL_FROM)
				.append(SURPLUS_MENU_TABLE)
				.append(SQL_ORDER_BY)
				.append(SURPLUS_MENU_ACTUAL_DATE)
				.append(ORDER_DESC);

			rs = stmt.executeQuery(sqlQuery.toString());

			while (rs.next()) {
				surplusMenuDomain = new SurplusMenuDomain();
				surplusMenuDomain.setId(rs.getLong(1));
				surplusMenuDomain.setSurplusMenuActualDate(rs.getDate(2));
				surplusMenuDomain.setSurplusMenuUsersId(rs.getLong(3));
				
				surplusMenuDomainList.add(surplusMenuDomain);
			}

		} catch (SQLException e) {
			LOG.error("Getting menu items sql error" + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Getting menu items naming error" + e.getMessage(), e);
		} finally {
			closeResources(rs, stmt, connection);
		}
		
		return surplusMenuDomainList;
	}
	
	
	@Override
	public List<SurplusMenuItemsDomain> getSurplusMenuItemsDomainListById(Long id) {
		SurplusMenuItemsDomain surplusMenuitemsDomain = null;
		List<SurplusMenuItemsDomain> surplusMenuitemsDomainList =  new ArrayList<SurplusMenuItemsDomain>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			StringBuilder querySelect = new StringBuilder();
			querySelect.append(SQL_SELECT)
					.append("*")
					.append(SQL_FROM)
					.append(SURPLUS_MENU_ITEMS_TABLE)
					.append(SQL_WHERE)
					.append(SURPLUS_MENU_ITEMS_MENU_ID)
					.append(" = ").append("'")
					.append(id).append("'");

			String sqlString = querySelect.toString();
			rs = statement.executeQuery(sqlString);
			
			while (rs.next()) {
				surplusMenuitemsDomain = new SurplusMenuItemsDomain();
				
				surplusMenuitemsDomain.setId(rs.getLong(1));
				surplusMenuitemsDomain.setSurplusMenuId(rs.getLong(2));
				surplusMenuitemsDomain.setSurplusProductId(rs.getLong(3));
				surplusMenuitemsDomain.setSurplusProductCount(rs.getInt(4));
				surplusMenuitemsDomain.setSurplusMenuCreationDate(rs.getTimestamp(5));
				
				surplusMenuitemsDomainList.add(surplusMenuitemsDomain);
			}

		} catch (SQLException e) {
			LOG.error("Getting menu id sql error: " + e.getMessage(), e);
		} catch (NamingException e) {
			LOG.error("Getting menu id naming error: " + e.getMessage(), e);
		} finally {
			closeResources(rs, statement, connection);
		}
		
		return surplusMenuitemsDomainList;
	}

}
