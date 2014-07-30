package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.dao.BoughtItemDao;
import am.ucom.dinning.persistence.dao.ProductDao;
import am.ucom.dinning.persistence.domain.BoughtItem;
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

public class BoughtItemDaoImpl implements BoughtItemDao {

    private static final Logger LOG = LoggerFactory.getLogger(BoughtItemDaoImpl.class);

    public BoughtItemDaoImpl() {
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.BoughtItemDao
     */
    @Override
    public List<BoughtItem> getBoughtItemsByPrice(BigDecimal price) {
        List<BoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(ITEM_PRICE)
                    .append(" = ")
                    .append(price);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Bought Item SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Bought Item SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.BoughtItemDao
     */
    @Override
    public List<BoughtItem> getBoughtItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<BoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(ITEM_PRICE)
                    .append(SQL_BETWEEN)
                    .append(minPrice)
                    .append(SQL_AND)
                    .append(maxPrice);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Bought Item SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Bought Item SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.BoughtItemDao
     */
    @Override
    public List<BoughtItem> getBoughtItemsByDate(Date date) {
        List<BoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(BOUGHT_DATE)
                    .append(" = ")
                    .append("'")
                    .append(new java.sql.Date(date.getTime()))
                    .append("'");

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Bought Item SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Bought Item SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.BoughtItemDao
     */
    @Override
    public List<BoughtItem> getBoughtItemsByBasketId(Long basketId) {
        List<BoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(SHARED_BASKET_ID)
                    .append(" = ")
                    .append(basketId);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Bought Item SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Bought Item SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return basketItemsList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.BoughtItemDao
     */
    @Override
    public List<BoughtItem> getBoughtItemsByProductId(Long productId) {
        List<BoughtItem> basketItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE)
                    .append(PRODUCT_ID)
                    .append(" = ")
                    .append(productId);

            rs = statement.executeQuery(sql.toString());
            basketItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Bought Item SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Bought Item SQL Naming Exception" + e.getMessage(), e);
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
    public Long save(BoughtItem entity) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT).append(BOUGHT_ITEM_TABLE).append("(").
                append(ITEM_PRICE).append(",").
                append(BOUGHT_DATE).append(",").
                append(SHARED_BASKET_ID).append(",").
                append(PRODUCT_ID)
                .append(SQL_VALUES).append("(?,?,?,?)");
        ResultSet generatedKeys = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long newBasketId = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pstmt.setBigDecimal(1, entity.getItemPrice());
            pstmt.setDate(2, new java.sql.Date(entity.getBoughtDate().getTime()));
            pstmt.setLong(3, entity.getSharedBasketId());
            pstmt.setLong(4, entity.getProduct().getId());
            pstmt.executeUpdate();
            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                newBasketId = generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("Save Bought Item SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Save Bought Item Naming Exception: " + e.getMessage(), e);
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
    public void batchSave(List<BoughtItem> entity) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = getConnection();
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(SQL_INSERT)
                    .append(BOUGHT_ITEM_TABLE).append(" (").append(ITEM_PRICE).append(",")
                    .append(BOUGHT_DATE).append(",").append(SHARED_BASKET_ID)
                    .append(",").append(PRODUCT_ID).append(") ")
                    .append(SQL_VALUES).append("(?,?,?,?)");


            pstmt = connection.prepareStatement(sqlBuilder.toString(), Statement.RETURN_GENERATED_KEYS);

            for (BoughtItem item : entity) {
                pstmt.setBigDecimal(1, item.getItemPrice());
                pstmt.setDate(2, new java.sql.Date(item.getBoughtDate().getTime()));
                pstmt.setLong(3, item.getSharedBasketId());
                pstmt.setLong(4, item.getProduct().getId());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            LOG.error("Batch Save Bought Items SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Batch Save Bought Items Naming Exception: " + e.getMessage(), e);
        } finally {
            closeResources(pstmt, connection);
        }
    }

    @Override
    public int update(BoughtItem entity) {
        return 0;
    }

    @Override
    public void batchUpdate(List<BoughtItem> entities, Long[] id) {
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
    public BoughtItem getById(Long Id) {
        BoughtItem boughtItem = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(BOUGHT_ITEM_TABLE)
                    .append(SQL_WHERE).append(ITEM_ID).append(" = ").append(Id);

            rs = statement.executeQuery(sql.toString());
            if (rs.next()) {
                boughtItem = new BoughtItem();
                boughtItem.setId(rs.getLong(1));
                boughtItem.setItemPrice(rs.getBigDecimal(2));
                boughtItem.setBoughtDate(rs.getDate(3));
                boughtItem.setSharedBasketId(rs.getLong(4));
                boughtItem.setProduct(getProductById(rs.getLong(5)));
            }
        } catch (SQLException e) {
            LOG.error("Bought Item SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Bought Item SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return boughtItem;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public List<BoughtItem> getAll() {
        List<BoughtItem> boughtItemsList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(BOUGHT_ITEM_TABLE);

            rs = statement.executeQuery(sql.toString());
            boughtItemsList = initItems(rs);
        } catch (SQLException e) {
            LOG.error("Bought SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Bought SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return boughtItemsList;
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
    private List<BoughtItem> initItems(ResultSet rs) throws SQLException {
        List<BoughtItem> list = new ArrayList<BoughtItem>();
        while (rs.next()) {
            BoughtItem item = new BoughtItem();
            item.setId(rs.getLong(1));
            item.setItemPrice(rs.getBigDecimal(2));
            item.setBoughtDate(rs.getDate(3));
            item.setSharedBasketId(rs.getLong(4));
            item.setProduct(getProductById(rs.getLong(5)));
            list.add(item);
        }

        return list;
    }
}
