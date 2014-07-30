package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.dao.EmployeeBoughtItemDao;
import am.ucom.dinning.persistence.dao.ProductDao;
import am.ucom.dinning.persistence.domain.EmployeeBoughtItem;
import am.ucom.dinning.persistence.domain.ProductDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.closeResources;
import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.getConnection;

public class EmployeeBoughtItemDaoImpl implements EmployeeBoughtItemDao {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeBoughtItemDaoImpl.class);

    public EmployeeBoughtItemDaoImpl() {
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.EmployeeBoughtItemDao
     */
    @Override
    public List<EmployeeBoughtItem> getBoughtItemsByPrice(BigDecimal price) {
        List<EmployeeBoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(ITEM_PRICE)
                    .append(" = ")
                    .append(price);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Employee Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.EmployeeBoughtItemDao
     */
    @Override
    public List<EmployeeBoughtItem> getBoughtItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<EmployeeBoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(ITEM_PRICE)
                    .append(SQL_BETWEEN)
                    .append(minPrice)
                    .append(SQL_AND)
                    .append(maxPrice);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Employee Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.EmployeeBoughtItemDao
     */
    @Override
    public List<EmployeeBoughtItem> getBoughtItemsByDiscountPrice(BigDecimal discountPrice) {
        List<EmployeeBoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(ITEM_DISCOUNT_PRICE)
                    .append(" = ")
                    .append(discountPrice);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Employee Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.EmployeeBoughtItemDao
     */
    @Override
    public List<EmployeeBoughtItem> getBoughtItemsByDiscountPriceRange(BigDecimal minDiscountPrice, BigDecimal maxDiscountPrice) {
        List<EmployeeBoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(ITEM_DISCOUNT_PRICE)
                    .append(SQL_BETWEEN)
                    .append(minDiscountPrice)
                    .append(SQL_AND)
                    .append(maxDiscountPrice);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Employee Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.EmployeeBoughtItemDao
     */
    @Override
    public List<EmployeeBoughtItem> getBoughtItemsByDate(Date date) {
        List<EmployeeBoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(BOUGHT_DATE)
                    .append(" = ")
                    .append("'")
                    .append(new java.sql.Date(date.getTime()))
                    .append("'");

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Employee Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.EmployeeBoughtItemDao
     */
    @Override
    public List<EmployeeBoughtItem> getBoughtItemsByBasketId(Long basketId) {
        List<EmployeeBoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(BASKET_ID)
                    .append(" = ")
                    .append(basketId);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Employee Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.EmployeeBoughtItemDao
     */
    @Override
    public List<EmployeeBoughtItem> getBoughtItemsByProductId(Long productId) {
        List<EmployeeBoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(PRODUCT_ID)
                    .append(" = ")
                    .append(productId);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Employee Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public Long save(EmployeeBoughtItem entity) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT).append(EMPLOYEE_BOUGHT_ITEM_TABLE).append("(").
                append(ITEM_PRICE).append(",").
                append(ITEM_DISCOUNT_PRICE).append(",").
                append(BOUGHT_DATE).append(",").
                append(BASKET_ID).append(",").
                append(PRODUCT_ID)
                .append(SQL_VALUES).append("(?,?,?,?,?)");
        ResultSet generatedKeys = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long newBasketId = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pstmt.setBigDecimal(1, entity.getItemPrice());
            pstmt.setBigDecimal(2, entity.getItemDiscountPrice());
            pstmt.setDate(3, new java.sql.Date(entity.getBoughtDate().getTime()));
            pstmt.setLong(4, entity.getEmployeeBasketId());
            pstmt.setLong(5, entity.getProduct().getId());
            pstmt.executeUpdate();
            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                newBasketId = generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("Save Employee Bought Item SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Save Employee Bought Item Naming Exception: " + e.getMessage(), e);
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
    public void batchSave(List<EmployeeBoughtItem> entity) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = getConnection();
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(SQL_INSERT).append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                    .append(" (")
                    .append(ITEM_PRICE).append(", ")
                    .append(ITEM_DISCOUNT_PRICE).append(", ")
                    .append(BOUGHT_DATE).append(", ")
                    .append(BASKET_ID).append(", ")
                    .append(PRODUCT_ID).append(")")
                    .append(SQL_VALUES).append("(?,?,?,?,?)");

            pstmt = connection.prepareStatement(sqlBuilder.toString(), Statement.RETURN_GENERATED_KEYS);

            for (EmployeeBoughtItem item : entity) {
                sqlBuilder.append(SQL_INSERT).append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                        .append(SQL_VALUES).append("(")
                        .append(item.getItemPrice()).append(", ")
                        .append(item.getItemDiscountPrice()).append(", ")
                        .append(item.getBoughtDate()).append(", ")
                        .append(item.getEmployeeBasketId()).append(", ")
                        .append(item.getProduct().getId()).append(" )");

                pstmt.setBigDecimal(1, item.getItemPrice());
                pstmt.setBigDecimal(2, item.getItemDiscountPrice());
                pstmt.setDate(3, new java.sql.Date(item.getBoughtDate().getTime()));
                pstmt.setLong(4, item.getEmployeeBasketId());
                pstmt.setLong(5, item.getProduct().getId());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            LOG.error("Batch Save Employee Bought Items SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Batch Save Employee Bought Items Naming Exception: " + e.getMessage(), e);
        } finally {
            closeResources(pstmt, connection);
        }
    }


    @Override
    public int update(EmployeeBoughtItem entity) {
        return 0;
    }

    @Override
    public void batchUpdate(List<EmployeeBoughtItem> entities, Long[] id) {
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public void batchDelete(List<String> id) {
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public EmployeeBoughtItem getById(Long Id) {
        EmployeeBoughtItem basketItem = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE).append(ITEM_ID).append(" = ").append(Id);

            rs = statement.executeQuery(sql.toString());
            if (rs.next()) {
                basketItem = new EmployeeBoughtItem();
                basketItem.setId(rs.getLong(1));
                basketItem.setItemPrice(rs.getBigDecimal(2));
                basketItem.setItemDiscountPrice(rs.getBigDecimal(3));
                basketItem.setBoughtDate(rs.getDate(4));
                basketItem.setEmployeeBasketId(rs.getLong(5));
                basketItem.setProduct(getProductById(rs.getLong(6)));
            }
        } catch (SQLException e) {
            LOG.error("Employee Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItem;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public List<EmployeeBoughtItem> getAll() {
        List<EmployeeBoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(EMPLOYEE_BOUGHT_ITEM_TABLE);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Employee Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Employee Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    @Override
    public List<String> getAllNames(Long id) {
        return null;
    }

    private ProductDomain getProductById(final Long id) {
        ProductDao dao = new ProductDaoImpl();

        return dao.getById(id);
    }

    /**
     * Initialization of EmployeeBoughtItem object instances list
     *
     * @param rs - ResultSet: results values retrieving from database
     * @return List<EmployeeBoughtItem> - initialized EmployeeBasket objects instances
     * @throws SQLException - throws if result set not initialized
     */
    private List<EmployeeBoughtItem> initItems(ResultSet rs) throws SQLException {
        List<EmployeeBoughtItem> list = new ArrayList<EmployeeBoughtItem>();
        while (rs.next()) {
            EmployeeBoughtItem item = new EmployeeBoughtItem();
            item.setId(rs.getLong(1));
            item.setItemPrice(rs.getBigDecimal(2));
            item.setItemDiscountPrice(rs.getBigDecimal(3));
            item.setBoughtDate(rs.getDate(4));
            item.setEmployeeBasketId(rs.getLong(5));
            item.setProduct(getProductById(rs.getLong(6)));
            list.add(item);
        }

        return list;
    }
}
