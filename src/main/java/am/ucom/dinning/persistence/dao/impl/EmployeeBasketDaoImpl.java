/*
 * EmployeeBasketDaoImpl		1.0 03/12/12 4:16 PM
 *
 * Copyright (c) UCom.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of UCom.
 * ("Confidential Information").  You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with UCom.
 */

package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.annotation.CollectionOption;
import am.ucom.dinning.persistence.annotation.LazyCollectionOption;
import am.ucom.dinning.persistence.dao.EmployeeBasketDao;
import am.ucom.dinning.persistence.dao.EmployeeBoughtItemDao;
import am.ucom.dinning.persistence.dao.ProductDao;
import am.ucom.dinning.persistence.domain.EmployeeBasket;
import am.ucom.dinning.persistence.domain.EmployeeBoughtItem;
import am.ucom.dinning.persistence.domain.ProductDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.closeResources;
import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.getConnection;

/**
 * EmployeeBasketDaoImpl class provides Database CRUD functionality for EmployeeBasket domain object instance.
 *
 * @author Hayk Hayryan
 * @version 1.0 03/12/12 4:16 PM
 */
public class EmployeeBasketDaoImpl implements EmployeeBasketDao {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeBasketDaoImpl.class);

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketsByCashierId(final Long cashierId) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(CASHIER_ID).append("= ").append(cashierId);

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketByCashierIdAndDate(final Long cashierId, final Date date) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(CASHIER_ID).append(" = ").append(cashierId)
                    .append(SQL_AND).append(PAYMENT_DATE).append(" = ")
                    .append("'").append(new java.sql.Date(date.getTime())).append("'");

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketsByPaymentType(final Integer paymentType) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(PAYMENT_TYPE).append(" = ").append(paymentType);

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketsByPaymentTypeAndDate(final Integer paymentType, final Date date) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(PAYMENT_TYPE).append(" = ").append(paymentType)
                    .append(SQL_AND).append(PAYMENT_DATE).append(" = ")
                    .append("'").append(new java.sql.Date(date.getTime())).append("'");

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }


    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketsByDate(final Date date) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(PAYMENT_DATE).append(" = ")
                    .append("'").append(new java.sql.Date(date.getTime())).append("'");

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketsByDateRange(final Date startDate, final Date endDate) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(PAYMENT_DATE).append(SQL_BETWEEN)
                    .append("'").append(new java.sql.Date(startDate.getTime())).append("'")
                    .append(SQL_AND).append("'").append(new java.sql.Date(endDate.getTime())).append("'");

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketsByUserId(final Long userId) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(USER_ID).append(" = ").append(userId);

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketsByUserIdAndDate(final Long userId, final Date date) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(CASHIER_ID).append(" = ").append(userId)
                    .append(SQL_AND).append(PAYMENT_DATE).append(" = ")
                    .append("'").append(new java.sql.Date(date.getTime())).append("'");

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketsByTotalPriceRange(final BigDecimal minPrice, final BigDecimal maxPrice) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(PAYMENT_TOTAL).append(SQL_BETWEEN)
                    .append(minPrice)
                    .append(SQL_AND).append(maxPrice);

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.EmployeeBasketDao
     */
    @Override
    public List<EmployeeBasket> getBasketsByGenericParams(final Map<String, String> searchParams) {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE);

            for (String key : searchParams.keySet()) {
                sql.append(key).append(" = ").append(searchParams.get(key)).append(SQL_AND);
            }

            rs = statement.executeQuery(sql.toString().substring(0, sql.toString().indexOf(SQL_AND)).trim());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public Long save(final EmployeeBasket entity) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT).append(EMPLOYEE_BASKET_TABLE).append("(").
                append(CASHIER_ID).append(",").
                append(PAYMENT_TYPE).append(",").
                append(PAYMENT_DATE).append(",").
                append(PAYMENT_TOTAL).append(",").
                append(USER_ID).append(") ")
                .append(SQL_VALUES).append("(?,?,?,?,?)");
        ResultSet generatedKeys = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long newBasketId = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, entity.getCashierId());
            pstmt.setInt(2, entity.getPaymentTypeCode());
            pstmt.setDate(3, new java.sql.Date(entity.getPaymentDate().getTime()));
            pstmt.setBigDecimal(4, entity.getPaymentTotal());
            pstmt.setLong(5, entity.getUserId());
            pstmt.executeUpdate();
            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                newBasketId = generatedKeys.getLong(1);
            }
            saveBasketBoughtItems(entity.getBoughtItems(), newBasketId);
            LOG.info("Employee bought new basket serialized with database ID: " + newBasketId);
        } catch (SQLException e) {
            LOG.error("Save Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Save Employee Basket Naming Exception: " + e.getMessage(), e);
        } finally {
            closeResources(generatedKeys, pstmt, connection);
        }
        return newBasketId;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public void batchSave(final List<EmployeeBasket> entity) {
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = getConnection();

            stmt = connection.createStatement();
            StringBuilder sqlBuilder = new StringBuilder();
            for (EmployeeBasket item : entity) {
                sqlBuilder.append(SQL_INSERT).append(EMPLOYEE_BASKET_TABLE)
                        .append(SQL_VALUES).append("(")
                        .append(item.getCashierId()).append(", ")
                        .append(item.getPaymentTypeCode()).append(", ")
                        .append(item.getPaymentDate()).append(", ")
                        .append(item.getPaymentTotal()).append(", ")
                        .append(item.getUserId()).append(" )");
                stmt.addBatch(sqlBuilder.toString());
            }
            int[] ids = stmt.executeBatch();
            for (int i = 0; i < ids.length; i++) {
                saveBasketBoughtItems(entity.get(i).getBoughtItems(), (long) ids[i]);
            }

        } catch (SQLException e) {
            LOG.error("Batch Save Employee Bought Items SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Batch Save Employee Bought Items Naming Exception: " + e.getMessage(), e);
        } finally {
            closeResources(stmt, connection);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public int update(final EmployeeBasket entity) {
        return 0;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public void batchUpdate(final List<EmployeeBasket> entities, Long[] id) {
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public int delete(final Long id) {
        Connection connection = null;
        int status = -1;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();

            StringBuilder querySelect = new StringBuilder();

            querySelect.append(SQL_DELETE).append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(BASKET_ID).append("=?");
            preparedStatement = connection.prepareStatement(querySelect.toString());
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Delete Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Delete Employee Basket Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(preparedStatement, connection);
        }
        return status;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public void batchDelete(final List<String> id) {
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public EmployeeBasket getById(final Long Id) {
        EmployeeBasket employeeBasket = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE)
                    .append(SQL_WHERE).append(BASKET_ID).append(" = ").append(Id);

            rs = statement.executeQuery(sql.toString());
            if (rs.next()) {
                employeeBasket = new EmployeeBasket();
                employeeBasket.setId(rs.getLong(1));
                employeeBasket.setCashierId(rs.getLong(2));
                employeeBasket.setPaymentTypeCode(rs.getInt(3));
                employeeBasket.setPaymentDate(rs.getDate(4));
                employeeBasket.setPaymentTotal(rs.getBigDecimal(5));
                employeeBasket.setUserId(rs.getLong(6));
                employeeBasket.setBoughtItems(fetchCollectionField(EmployeeBasket.class, employeeBasket.getId()));
            }
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasket;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public List<EmployeeBasket> getAll() {
        List<EmployeeBasket> employeeBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BASKET_TABLE);

            rs = statement.executeQuery(sql.toString());
            employeeBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Employee Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return employeeBasketList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public List<String> getAllNames(Long id) {
        return null;
    }

    /**
     * Initialization of EmployeeBasket object instances list
     *
     * @param rs - ResultSet: results values retrieving from database
     * @return List<EmployeeBasket> - initialized EmployeeBasket objects instances
     * @throws SQLException - throws if result set not initialized
     */
    private List<EmployeeBasket> initResultSet(ResultSet rs) throws SQLException {
        List<EmployeeBasket> employeeBasketList = new ArrayList<EmployeeBasket>();
        while (rs.next()) {
            EmployeeBasket basket = new EmployeeBasket();
            basket.setId(rs.getLong(1));
            basket.setCashierId(rs.getLong(2));
            basket.setPaymentTypeCode(rs.getInt(3));
            basket.setPaymentDate(rs.getDate(4));
            basket.setPaymentTotal(rs.getBigDecimal(5));
            basket.setUserId(rs.getLong(6));
            basket.setBoughtItems(fetchCollectionField(EmployeeBasket.class, basket.getId()));
            employeeBasketList.add(basket);
        }

        return employeeBasketList;
    }

    /**
     * Initialize basket's bought items
     *
     * @param rs - ResultSet: results values retrieving from database
     * @return List<EmployeeBoughtItem> - initialized EmployeeBoughtItem objects instances
     * @throws SQLException - throws if result set not initialized
     */
    private static List<EmployeeBoughtItem> initBoughtItems(ResultSet rs) throws SQLException {
        List<EmployeeBoughtItem> boughtItemsList = new ArrayList<EmployeeBoughtItem>();
        while (rs.next()) {
            EmployeeBoughtItem item = new EmployeeBoughtItem();
            item.setId(rs.getLong(1));
            item.setItemPrice(rs.getBigDecimal(2));
            item.setItemDiscountPrice(rs.getBigDecimal(3));
            item.setBoughtDate(rs.getDate(4));
            item.setEmployeeBasketId(rs.getLong(5));
            item.setProduct(getBoughtProductDomain(rs.getLong(6)));
            boughtItemsList.add(item);
        }

        return boughtItemsList;
    }

    /**
     * Fetch list according CollectionOption annotation parameter lazy or eager
     *
     * @param clazz   - Class: reflection class for annotation handling
     * @param fetchId - Long: fk id for lazy or eger initialization
     * @return List<EmployeeBoughtItem>  - initialized EmployeeBoughtItem object instances list if annotated as eager.
     */
    public static List<EmployeeBoughtItem> fetchCollectionField(Class<EmployeeBasket> clazz, final Long fetchId) {
        Field fields[] = clazz.getFields();
        List<EmployeeBoughtItem> list = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(CollectionOption.class)) {
                CollectionOption option = field.getAnnotation(CollectionOption.class);

                if (option.value() == LazyCollectionOption.FALSE) {
                    try {
                        connection = getConnection();
                        statement = connection.createStatement();
                        StringBuilder sql = new StringBuilder();

                        sql.append("SELECT ").append("*")
                                .append(" FROM ")
                                .append(EMPLOYEE_BOUGHT_TABLE)
                                .append(" WHERE ").append(EMPLOYEE_BASKET_ID).append("= ").append(fetchId);

                        rs = statement.executeQuery(sql.toString());
                        list = initBoughtItems(rs);
                    } catch (SQLException e) {
                        LOG.error("Collection Binder SQL Exception: " + e.getMessage(), e);
                    } catch (NamingException e) {
                        LOG.error("Collection Binder SQL Naming Exception" + e.getMessage(), e);
                    } finally {
                        closeResources(rs, statement, connection);
                    }
                }
            }
        }

        return list;
    }

    public static ProductDomain getBoughtProductDomain(final Long productId) {
        ProductDao productDao = new ProductDaoImpl();

        return productDao.getById(productId);
    }

    private void saveBasketBoughtItems(List<EmployeeBoughtItem> boughtItemList, Long newBasketId) {
        EmployeeBoughtItemDao dao = new EmployeeBoughtItemDaoImpl();
        for (EmployeeBoughtItem item : boughtItemList) {
            item.setEmployeeBasketId(newBasketId);
        }

        dao.batchSave(boughtItemList);
    }
}
