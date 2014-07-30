package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.annotation.CollectionOption;
import am.ucom.dinning.persistence.annotation.LazyCollectionOption;
import am.ucom.dinning.persistence.dao.BoughtItemDao;
import am.ucom.dinning.persistence.dao.ProductDao;
import am.ucom.dinning.persistence.dao.SharedBasketDao;
import am.ucom.dinning.persistence.domain.BoughtItem;
import am.ucom.dinning.persistence.domain.ProductDomain;
import am.ucom.dinning.persistence.domain.SharedBasket;
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

public class SharedBasketDaoImpl implements SharedBasketDao {

    private static final Logger LOG = LoggerFactory.getLogger(SharedBasketDaoImpl.class);

    public SharedBasketDaoImpl() {
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.SharedBasketDao
     */
    @Override
    public List<SharedBasket> getBasketsByCashierId(Long cashierId) {
        List<SharedBasket> sharedBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(SHARED_BASKET_TABLE)
                    .append(SQL_WHERE).append(CASHIER_ID).append("= ").append(cashierId);

            rs = statement.executeQuery(sql.toString());
            sharedBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Shared Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return sharedBasketList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.SharedBasketDao
     */
    @Override
    public List<SharedBasket> getBasketByCashierIdAndDate(Long cashierId, Date date) {
        List<SharedBasket> sharedBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(SHARED_BASKET_TABLE)
                    .append(SQL_WHERE).append(CASHIER_ID).append(" = ").append(cashierId)
                    .append(SQL_AND).append(PAYMENT_DATE).append(" = ")
                    .append("'").append(new java.sql.Date(date.getTime())).append("'");

            rs = statement.executeQuery(sql.toString());
            sharedBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Shared Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return sharedBasketList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.SharedBasketDao
     */
    @Override
    public List<SharedBasket> getBasketsByDate(Date date) {
        List<SharedBasket> sharedBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(SHARED_BASKET_TABLE)
                    .append(SQL_WHERE).append(PAYMENT_DATE).append(" = ")
                    .append("'").append(new java.sql.Date(date.getTime())).append("'");

            rs = statement.executeQuery(sql.toString());
            sharedBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Shared Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return sharedBasketList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.SharedBasketDao
     */
    @Override
    public List<SharedBasket> getBasketsByDateRange(Date startDate, Date endDate) {
        List<SharedBasket> sharedBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(SHARED_BASKET_TABLE)
                    .append(SQL_WHERE).append(PAYMENT_DATE).append(SQL_BETWEEN)
                    .append("'").append(new java.sql.Date(startDate.getTime())).append("'")
                    .append(SQL_AND).append("'").append(new java.sql.Date(endDate.getTime())).append("'");

            rs = statement.executeQuery(sql.toString());
            sharedBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Shared Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return sharedBasketList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.SharedBasketDao
     */
    @Override
    public List<SharedBasket> getBasketsByTotalPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<SharedBasket> sharedBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(SHARED_BASKET_TABLE)
                    .append(SQL_WHERE).append(PAYMENT_TOTAL).append(SQL_BETWEEN)
                    .append(minPrice)
                    .append(SQL_AND).append(maxPrice);

            rs = statement.executeQuery(sql.toString());
            sharedBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Shared Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return sharedBasketList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.SharedBasketDao
     */
    @Override
    public List<SharedBasket> getBasketsByGenericParams(Map<String, String> searchParams) {
        List<SharedBasket> sharedBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(SHARED_BASKET_TABLE)
                    .append(SQL_WHERE);

            for (String key : searchParams.keySet()) {
                sql.append(key).append(" = ").append(searchParams.get(key)).append(SQL_AND);
            }

            rs = statement.executeQuery(sql.toString().substring(0, sql.toString().indexOf(SQL_AND)).trim());
            sharedBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Shared Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return sharedBasketList;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public Long save(SharedBasket entity) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT).append(SHARED_BASKET_TABLE).append("(").
                append(CASHIER_ID).append(",").
                append(PAYMENT_DATE).append(",").
                append(PAYMENT_TOTAL).append(") ")
                .append(SQL_VALUES).append("(?,?,?)");
        ResultSet generatedKeys = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long newBasketId = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, entity.getCashierId());
            pstmt.setDate(2, new java.sql.Date(entity.getPaymentDate().getTime()));
            pstmt.setBigDecimal(3, entity.getPaymentTotal());
            pstmt.executeUpdate();
            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                newBasketId = generatedKeys.getLong(1);
            }
            saveBasketBoughtItems(entity.getBoughtItems(), newBasketId);
        } catch (SQLException e) {
            LOG.error("Save Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Save Shared Basket Naming Exception: " + e.getMessage(), e);
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
    public void batchSave(List<SharedBasket> entity) {
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = getConnection();

            stmt = connection.createStatement();
            StringBuilder sqlBuilder = new StringBuilder();
            for (SharedBasket item : entity) {
                sqlBuilder.append(SQL_INSERT).append(SHARED_BASKET_TABLE)
                        .append(SQL_VALUES).append("(")
                        .append(item.getCashierId()).append(", ")
                        .append(item.getPaymentDate()).append(", ")
                        .append(item.getPaymentTotal()).append(", ");
                stmt.addBatch(sqlBuilder.toString());
            }
            int[] ids = stmt.executeBatch();
            for (int i = 0; i < ids.length; i++) {
                saveBasketBoughtItems(entity.get(i).getBoughtItems(), (long) ids[i]);
            }

        } catch (SQLException e) {
            LOG.error("Batch Save Bought Items SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Batch Save Bought Items Naming Exception: " + e.getMessage(), e);
        } finally {
            closeResources(stmt, connection);
        }
    }

    @Override
    public int update(SharedBasket entity) {
        return 0;
    }

    @Override
    public void batchUpdate(List<SharedBasket> entities, Long[] id) {
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public int delete(Long id) {
        Connection connection = null;
        int status = -1;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();

            StringBuilder querySelect = new StringBuilder();

            querySelect.append(SQL_DELETE).append(SHARED_BASKET_TABLE)
                    .append(SQL_WHERE).append(BASKET_ID).append("=?");
            preparedStatement = connection.prepareStatement(querySelect.toString());
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Delete Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Delete Shared Basket Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(preparedStatement, connection);
        }
        return status;
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
    public SharedBasket getById(Long Id) {
        SharedBasket sharedBasket = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(SHARED_BASKET_TABLE)
                    .append(SQL_WHERE).append(BASKET_ID).append(" = ").append(Id);

            rs = statement.executeQuery(sql.toString());
            if (rs.next()) {
                sharedBasket = new SharedBasket();
                sharedBasket.setId(rs.getLong(1));
                sharedBasket.setCashierId(rs.getLong(2));
                sharedBasket.setPaymentDate(rs.getDate(3));
                sharedBasket.setPaymentTotal(rs.getBigDecimal(4));
                sharedBasket.setBoughtItems(fetchCollectionField(SharedBasket.class, sharedBasket.getId()));
            }
        } catch (SQLException e) {
            LOG.error("Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Shared Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return sharedBasket;
    }

    /**
     * (non-Javadoc)
     *
     * @see am.ucom.dinning.persistence.dao.GenericDao
     */
    @Override
    public List<SharedBasket> getAll() {
        List<SharedBasket> sharedBasketList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(SHARED_BASKET_TABLE);

            rs = statement.executeQuery(sql.toString());
            sharedBasketList = initResultSet(rs);
        } catch (SQLException e) {
            LOG.error("Shared Basket SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Shared Basket SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return sharedBasketList;
    }

    @Override
    public List<String> getAllNames(Long id) {
        return null;
    }

    public static ProductDomain getBoughtProductDomain(final Long productId) {
        ProductDao productDao = new ProductDaoImpl();

        return productDao.getById(productId);
    }

    /**
     * Initialize basket's bought items
     *
     * @param rs - ResultSet: results values retrieving from database
     * @return List<EmployeeBoughtItem> - initialized EmployeeBoughtItem objects instances
     * @throws SQLException - throws if result set not initialized
     */
    private static List<BoughtItem> initBoughtItems(ResultSet rs) throws SQLException {
        List<BoughtItem> boughtItemsList = new ArrayList<BoughtItem>();
        while (rs.next()) {
            BoughtItem item = new BoughtItem();
            item.setId(rs.getLong(1));
            item.setItemPrice(rs.getBigDecimal(2));
            item.setBoughtDate(rs.getDate(3));
            item.setSharedBasketId(rs.getLong(4));
            item.setProduct(getBoughtProductDomain(rs.getLong(5)));
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
    public static List<BoughtItem> fetchCollectionField(Class<SharedBasket> clazz, final Long fetchId) {
        Field fields[] = clazz.getFields();
        List<BoughtItem> list = null;
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
                                .append(BOUGHT_TABLE)
                                .append(" WHERE ").append(BASKET_ID).append("= ").append(fetchId);

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

    /**
     * Initialization of EmployeeBasket object instances list
     *
     * @param rs - ResultSet: results values retrieving from database
     * @return List<EmployeeBasket> - initialized EmployeeBasket objects instances
     * @throws SQLException - throws if result set not initialized
     */
    private List<SharedBasket> initResultSet(ResultSet rs) throws SQLException {
        List<SharedBasket> sharedBasketList = new ArrayList<SharedBasket>();
        while (rs.next()) {
            SharedBasket basket = new SharedBasket();
            basket.setId(rs.getLong(1));
            basket.setCashierId(rs.getLong(2));
            basket.setPaymentDate(rs.getDate(3));
            basket.setPaymentTotal(rs.getBigDecimal(4));
            basket.setBoughtItems(fetchCollectionField(SharedBasket.class, basket.getId()));
            sharedBasketList.add(basket);
        }

        return sharedBasketList;
    }

    private void saveBasketBoughtItems(List<BoughtItem> boughtItemList, Long newBasketId) {
        BoughtItemDao dao = new BoughtItemDaoImpl();
        for (BoughtItem item : boughtItemList) {
            item.setSharedBasketId(newBasketId);
        }

        dao.batchSave(boughtItemList);
    }
}
