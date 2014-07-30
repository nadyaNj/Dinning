package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.dao.ProductMeasurementDao;
import am.ucom.dinning.persistence.dao.ProductTypeDao;
import am.ucom.dinning.persistence.domain.ProductTypeDomain;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.closeResources;
import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.getConnection;

/**
 * extends BaseImpl and implemented ProductTypeDao
 *
 * @author arthur
 */
public class ProductTypeDaoImpl implements ProductTypeDao {

	public static final Logger LOG = LoggerFactory.getLogger(ProductMeasurementDao.class);
    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#save(java.lang.Object)
      */
    @Override
    public Long save(ProductTypeDomain entity) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT).append(CATEGORY_TABLE_NAME).append("(").
                append(CATEGORY_NAME).append(",").append(CATEGORY_CREATED_DATE).
                append(",").append(CATEGORY_CHANGED_DATE).append(")").
                append(SQL_VALUES).append("(?,?,?)");

        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.setString(1, entity.getType());
            pstmt.setTimestamp(2, entity.getCreatedAt());
            pstmt.setTimestamp(3, entity.getChangedAt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	LOG.error("Save type sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Save type naming error: " + e.getMessage(), e);
        } finally {
            closeResources(pstmt, connection);
        }
        return (long) 1;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchSave(java.util.List)
      */
    @Override
    public void batchSave(List<ProductTypeDomain> entity) {

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#update(java.lang.Object)
      */
    @Override
    public int update(ProductTypeDomain entity) {
        StringBuilder sql = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long id = entity.getId();
        try {
            connection = getConnection();

            sql = new StringBuilder();

            sql.append(SQL_UPDATE).append(CATEGORY_TABLE_NAME).append(SQL_SET)
                    .append(CATEGORY_NAME).append("=").append("?").append(",")
                    .append(CATEGORY_CHANGED_DATE).append("=").append("?")
                    .append(SQL_WHERE).append(CATEGORY_ID).append("=").append("?");

            String sqlString = sql.toString();
            pstmt = connection.prepareStatement(sqlString);

            pstmt.setString(1, entity.getType());
            pstmt.setTimestamp(2, entity.getChangedAt());
            pstmt.setLong(3, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
        	LOG.error("Update type sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Update type naming error: " + e.getMessage(), e);
        } finally {
            closeResources(pstmt, connection);
        }
        return 1;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#delete(java.lang.Integer)
      */
    @Override
    public int delete(Long id) {
        if (!idDeleteProductType(id)) {
            return 0;
        }
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_DELETE).append(CATEGORY_TABLE_NAME).
                append(SQL_WHERE).append(CATEGORY_ID).append("=").append(id);
        Connection connection = null;
        Statement st = null;
        try {
            connection = getConnection();
            st = connection.createStatement();
            st.executeUpdate(sql.toString());
        } catch (SQLException e) {
        	LOG.error("Delete type sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Delete type naming error: " + e.getMessage(), e);
        } finally {
            closeResources(st, connection);
        }
        return 1;
    }

    /**
     * this method checks if the product type is used in products table or not in order to delete
     *
     *
     * @param id is the id of type to be deleted
     * @return
     */
    private boolean idDeleteProductType(Long id) {
        boolean deleteBool = true;
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(PRODUCT_CATEGORY_CATEGORY_ID).
                append(SQL_FROM).append(PRODUCT_CATEGORY_TABLE_NAME).
                append(SQL_WHERE).append(PRODUCT_CATEGORY_CATEGORY_ID).
                append("=").append(id);
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            if (rs.next()) {
                deleteBool = false;
            }
        } catch (SQLException e) {
        	LOG.error("Delete type sql error: " + e.getMessage(), e);
            deleteBool = false;
        } catch (NamingException e) {
        	LOG.error("Delete type naming error: " + e.getMessage(), e);
            deleteBool = false;
        } finally {
            closeResources(st, connection);
        }
        return deleteBool;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getById(java.lang.Long)
      */
    @Override
    public ProductTypeDomain getById(Long Id) {
        Connection connection = null;
        Statement statement = null;
        ProductTypeDomain typeDomain = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).append(CATEGORY_TABLE_NAME)
                    .append(SQL_WHERE).append(CATEGORY_ID).append("= ").append(Id);

            String sqlString = querySelect.toString();
            rs = statement.executeQuery(sqlString);

            if (rs.next()) {
                typeDomain = new ProductTypeDomain();
                typeDomain.setId(rs.getLong(1));
                typeDomain.setType(rs.getString(2));
                typeDomain.setCreatedAt(rs.getTimestamp(3));
                typeDomain.setChangedAt(rs.getTimestamp(4));
            }
        } catch (SQLException e) {
        	LOG.error("Get ProductTypeDomain by ID sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get ProductTypeDomain by ID naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return typeDomain;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAll()
      */
    @Override
    public List<ProductTypeDomain> getAll() {
        ProductTypeDomain typeDomain = null;
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        List<ProductTypeDomain> typeDomainList = new ArrayList<ProductTypeDomain>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).
                    append(CATEGORY_TABLE_NAME);

            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                typeDomain = new ProductTypeDomain();

                typeDomain.setId(rs.getLong(1));
                typeDomain.setType(rs.getString(2));
                typeDomain.setCreatedAt(rs.getTimestamp(3));
                typeDomain.setChangedAt(rs.getTimestamp(4));

                typeDomainList.add(typeDomain);
            }
        } catch (SQLException e) {
        	LOG.error("Get All ProductTypeDomain sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get All ProductTypeDomain naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return typeDomainList;
    }

    @Override
    public List<ProductTypeDomain> getAll(Integer pageNumber) {
        ProductTypeDomain typeDomain = null;
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        Integer limitMin = pageNumber * 10;
        Integer limitMax = 10;


        List<ProductTypeDomain> typeDomainList = new ArrayList<ProductTypeDomain>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).
                    append(CATEGORY_TABLE_NAME)
                    .append(SQL_ORDER_BY).append(CATEGORY_CREATED_DATE).append(ORDER_DESC)
                    .append(SQL_LIMIT).append(limitMin).append(" , ").append(limitMax);

            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                typeDomain = new ProductTypeDomain();

                typeDomain.setId(rs.getLong(1));
                typeDomain.setType(rs.getString(2));
                typeDomain.setCreatedAt(rs.getTimestamp(3));
                typeDomain.setChangedAt(rs.getTimestamp(4));

                typeDomainList.add(typeDomain);
            }
        } catch (SQLException e) {
        	LOG.error("Get All ProductTypeDomain by page number sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get All ProductTypeDomain by page number naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return typeDomainList;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchUpdate(java.util.List, java.lang.Long[])
      */
    @Override
    public void batchUpdate(List<ProductTypeDomain> entities, Long[] id) {
        // TODO Auto-generated method stub

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchDelete(java.util.List)
      */
    @Override
    public void batchDelete(List<String> id) {
        // TODO Auto-generated method stub

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAllNames()
      */
    @Override
    public List<String> getAllNames(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(CATEGORY_NAME).append(SQL_FROM).append(CATEGORY_TABLE_NAME);
        if (id != null) {
            sql.append(SQL_WHERE).append(CATEGORY_ID).append("!=").append(id);
        }
        List<String> typeNames = null;
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            typeNames = new ArrayList<String>();
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            while (rs.next()) {
                typeNames.add(rs.getString(1));
            }
        } catch (SQLException e) {
        	LOG.error("Get All names of type sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get All names of type naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return typeNames;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductTypeDao#getProductTypeCount()
      */
    @Override
    public int getProductTypeCount() {

        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        int count = 0;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append("COUNT").append("(*)").append(SQL_FROM).
                    append(CATEGORY_TABLE_NAME);
            String st = querySelect.toString();

            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
        	LOG.error("Get Count of type sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get Count of type naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }


        return count;
    }
}