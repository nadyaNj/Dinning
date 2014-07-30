package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.dao.ProductMeasurementDao;
import am.ucom.dinning.persistence.domain.ProductMeasurementDomain;
import am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.closeResources;
import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.getConnection;

/**
 * the class for delete, update, inserte, getById, getAll for measurement
 *
 * @author siranush
 */
public class ProductMeasurementDaoImpl implements ProductMeasurementDao {

	
	public static final Logger LOG = LoggerFactory.getLogger(ProductMeasurementDao.class);
    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#save(java.lang.Object)
      */
    @Override
    public Long save(ProductMeasurementDomain entity) {
        StringBuilder sql = new StringBuilder();

        sql.append(SQL_INSERT).append(MEASURMENT_TABLE_NAME).append("(").append(MEASURMENT_NAME).
                append(",").append(MEASURMENT_CREATED_DATE).append(",").append(MEASURMENT_CHANGED_DATE).append(")").
                append(SQL_VALUES).append("(?,?,?)");
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.setString(1, entity.getMeasurmentName());
            pstmt.setTimestamp(2, entity.getCreatedAt());
            pstmt.setTimestamp(3, entity.getChangedAt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Save measurement sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Save measurement naming error: " + e.getMessage(), e);
        } finally {
            closeResources(pstmt, connection);
        }
        return (long) 1;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAllNames()
      */
    @Override
    public List<String> getAllNames(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(MEASURMENT_NAME).append(SQL_FROM).
                append(MEASURMENT_TABLE_NAME);
        if (id != null) {
            sql.append(SQL_WHERE).append(MEASURMENT_ID).append("!=").append(id);
        }
        List<String> measurementNameList = null;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            measurementNameList = new ArrayList<String>();
            connection = getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            while (rs.next()) {
                measurementNameList.add(rs.getString(1));
            }

        } catch (SQLException e) {
        	LOG.error("Get All names of measurement sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get All names of measurement naming error: " + e.getMessage(), e);
        } finally {
            closeResources(st, connection);
        }
        return measurementNameList;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchSave(java.util.List)
      */
    @Override
    public void batchSave(List<ProductMeasurementDomain> entity) {

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#update(java.lang.Object, java.lang.Integer)
      */
    @Override
    public int update(ProductMeasurementDomain entity) {
        StringBuilder sql = new StringBuilder();
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long id = entity.getId();

        sql.append(SQL_UPDATE).append(MEASURMENT_TABLE_NAME).append(SQL_SET)
                .append(MEASURMENT_NAME).append("=").append("?").append(",")
                .append(MEASURMENT_CHANGED_DATE).append("=").append("?")
                .append(SQL_WHERE).append(MEASURMENT_ID).append("=").append("?");
        try {
            connection = getConnection();
            String sqlString = sql.toString();
            pstmt = connection.prepareStatement(sqlString);

            pstmt.setString(1, entity.getMeasurmentName());
            pstmt.setTimestamp(2, entity.getChangedAt());
            pstmt.setLong(3, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
        	LOG.error("Update measurement sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Update measurement naming error: " + e.getMessage(), e);
        } finally {
            closeResources(pstmt, connection);
        }
        return 1;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchUpdate(java.util.List, java.lang.Integer[])
      */
    @Override
    public void batchUpdate(List<ProductMeasurementDomain> entities, Long[] id) {

    }

    /*
      *
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#delete(java.lang.Integer)
      */
    @Override
    public int delete(Long id) {
        if (!idDeleteProductMeasurement(id)) {
            return 0;
        }
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_DELETE).append(MEASURMENT_TABLE_NAME).append(SQL_WHERE).
                append(MEASURMENT_ID).append(" = ").append(id);
        Connection connection = null;
        Statement st = null;
        try {
            connection = getConnection();
            st = connection.createStatement();
            st.executeUpdate(sql.toString());
        } catch (SQLException e) {
        	LOG.error("Delete measurement sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Delete measurement naming error: " + e.getMessage(), e);
        } finally {
            closeResources(st, connection);
        }
        return 1;
    }

    /**
     * This method is for checking if the measurement is used in products table in order to delete.
     *
     * @param id is the measurement id which will be checked to be deleted
     * @return
     */
    private boolean idDeleteProductMeasurement(Long id) {
        boolean deleteBool = true;
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(PRODUCT_MEASUREMENT_MEASUREMENT_ID).append(SQL_FROM).append(PRODUCT_TABLE_NAME).
                append(SQL_WHERE).append(PRODUCT_MEASUREMENT_MEASUREMENT_ID).
                append(" = ").append(id);
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
        	LOG.error("Delete measurement sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Delete measurement sql error: " + e.getMessage(), e);
        } finally {
            closeResources(st, connection);
        }
        return deleteBool;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchDelete(java.lang.Integer[])
      */
    @Override
    public void batchDelete(List<String> id) {

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getById(java.lang.Long)
      */
    @Override
    public ProductMeasurementDomain getById(Long Id) {
        Statement statement = null;
        ProductMeasurementDomain measurementDomain = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();

            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).append(MEASURMENT_TABLE_NAME)
                    .append(SQL_WHERE).append(MEASURMENT_ID).append("= ").append(Id);

            String sqlString = querySelect.toString();
            rs = statement.executeQuery(sqlString);

            if (rs.next()) {
                measurementDomain = new ProductMeasurementDomain();
                measurementDomain.setId(rs.getLong(1));
                measurementDomain.setMeasurmentName(rs.getString(2));
                measurementDomain.setCreatedAt(rs.getTimestamp(3));
                measurementDomain.setChangedAt(rs.getTimestamp(4));
            }
        } catch (SQLException e) {
        	LOG.error("Get ProductMeasurementDomain by ID sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get ProductMeasurementDomain by ID naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return measurementDomain;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAll()
      */
    @Override
    public List<ProductMeasurementDomain> getAll() {
        Statement statement = null;
        ProductMeasurementDomain mesurmentDomain = null;
        List<ProductMeasurementDomain> measurmentDomainList = new ArrayList<ProductMeasurementDomain>();
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();

            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).append(MEASURMENT_TABLE_NAME);

            String sqlString = querySelect.toString();
            rs = statement.executeQuery(sqlString);
            while (rs.next()) {
                mesurmentDomain = new ProductMeasurementDomain();
                mesurmentDomain.setId(rs.getLong(1));
                mesurmentDomain.setMeasurmentName(rs.getString(2));
                mesurmentDomain.setCreatedAt(rs.getTimestamp(3));
                mesurmentDomain.setChangedAt(rs.getTimestamp(4));

                measurmentDomainList.add(mesurmentDomain);
            }
        } catch (SQLException e) {
        	LOG.error("Get All ProductMeasurementDomain sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get All ProductMeasurementDomain naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return measurmentDomainList;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductMeasurementDao#getAll(java.lang.Integer)
      */
    @Override
    public List<ProductMeasurementDomain> getAll(Integer pageNumber) {
        ProductMeasurementDomain mesurmentDomain = null;
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        Integer limitMin = pageNumber * 10;
        Integer limitMax = 10;


        List<ProductMeasurementDomain> measurementDomainList = new ArrayList<ProductMeasurementDomain>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();

            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM)
                    .append(MEASURMENT_TABLE_NAME)
                    .append(SQL_ORDER_BY).append(MEASURMENT_CREATED_DATE).append(ORDER_DESC)
                    .append(SQL_LIMIT).append(limitMin).append(" , ").append(limitMax);

            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                mesurmentDomain = new ProductMeasurementDomain();
                mesurmentDomain.setId(rs.getLong(1));
                mesurmentDomain.setMeasurmentName(rs.getString(2));
                mesurmentDomain.setCreatedAt(rs.getTimestamp(3));
                mesurmentDomain.setChangedAt(rs.getTimestamp(4));
                measurementDomainList.add(mesurmentDomain);
            }
        } catch (SQLException e) {
        	LOG.error("Get All ProductMeasurementDomain by page number sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get All ProductMeasurementDomain by page number naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return measurementDomainList;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductMeasurementDao#getMeasurementCount()
      */
    @Override
    public int getMeasurementCount() {

        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        int count = 0;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append("COUNT").append("(*)").append(SQL_FROM).
                    append(MEASURMENT_TABLE_NAME);
            String st = querySelect.toString();

            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
        	LOG.error("Get Count of Measurement sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
        	LOG.error("Get Count of Measurement naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }


        return count;
    }


}
