package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.dao.MenuDao;
import am.ucom.dinning.persistence.dao.ProductDao;
import am.ucom.dinning.persistence.domain.ProductDomain;
import am.ucom.dinning.persistence.domain.ProductSearchResult;
import am.ucom.dinning.persistence.domain.ProductTypeDomain;
import am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory;
import am.ucom.dinning.web.model.ProductSearchCriteriaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.closeResources;
import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.getConnection;

/**
 * Product DAO Implementation class
 *
 * @author nadya
 */
public class ProductDaoImpl implements ProductDao {

    public static final Logger LOG = LoggerFactory.getLogger(ProductDaoImpl.class);

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#save(java.lang.Object)
      */
    @Override
    public Long save(ProductDomain productDomain) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT).append(PRODUCT_TABLE_NAME).append("(").
                append(PRODUCT_NAME).append(",").append(PRODUCT_PRICE).append(",").
                append(PRODUCT_PICTURE_URL).append(",").
                append(PRODUCT_DESCRIPTION).append(",").append(PRODUCT_CODE).
                append(",").append(PRODUCT_MASURMENT_ID).append(",").
                append(PRODUCT_HIDE).append(",").append(PRODUCT_CREATED_DATE).
                append(",").append(PRODUCT_CHANGED_DATE).append(",").
                append(PRODUCT_DISC_PRICE).append(")").append(SQL_VALUES).
                append("(?,?,?,?,?,?,?,?,?,?)");
        ResultSet generatedKeys = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long newProductId = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, productDomain.getName());
            pstmt.setInt(2, productDomain.getPrice());
            pstmt.setString(3, productDomain.getImgUrl());
            pstmt.setString(4, productDomain.getDescription());
            pstmt.setString(5, productDomain.getCode());
            pstmt.setLong(6, productDomain.getMeasurementId());
            pstmt.setInt(7, 0);
            pstmt.setTimestamp(8, productDomain.getCreatedAt());
            pstmt.setTimestamp(9, productDomain.getChangedAt());
            pstmt.setInt(10, productDomain.getDiscountPrice());
            pstmt.executeUpdate();
            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                newProductId = generatedKeys.getLong(1);
            }
            insertProductCategoryIds(productDomain.getProductTypeList(), newProductId);
        } catch (SQLException e) {
            LOG.error("save product sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("save product naming exception" + e.getMessage());
        } finally {
            closeResources(generatedKeys, pstmt, connection);
        }
        return newProductId;
    }

    /* (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchSave(java.util.List)
      */
    @Override
    public void batchSave(List<ProductDomain> entity) {
    }

    /* (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#update(java.lang.Object)
      */
    @Override
    public int update(ProductDomain entity) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_UPDATE).append(PRODUCT_TABLE_NAME).append(SQL_SET)
                .append(PRODUCT_NAME).append("=").append("?").append(",")
                .append(PRODUCT_PRICE).append("=").append("?").append(",")
                .append(PRODUCT_PICTURE_URL).append("=").append("?").append(",")
                .append(PRODUCT_DESCRIPTION).append("=").append("?").append(",")
                .append(PRODUCT_CODE).append("=").append("?").append(",")
                .append(PRODUCT_HIDE).append("=").append("?").append(",")
                .append(PRODUCT_MASURMENT_ID).append("=").append("?").append(",")
                .append(PRODUCT_CHANGED_DATE).append("=").append("?")
                .append(SQL_WHERE).append(PRODUCT_ID).append("=").append("?");
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long id = entity.getId();
        try {
            connection = MySqlDAOFactory.getConnection();
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.setString(1, entity.getName());
            pstmt.setInt(2, entity.getPrice());
            pstmt.setString(3, entity.getImgUrl());
            pstmt.setString(4, entity.getDescription());
            pstmt.setString(5, entity.getCode());
            pstmt.setBoolean(6, entity.isHidden());
            pstmt.setLong(7, entity.getMeasurementId());
            pstmt.setTimestamp(8, entity.getChangedAt());
            pstmt.setLong(9, id);
            pstmt.executeUpdate();
            deletePrCatIds(id);
            insertProductCategoryIds(entity.getProductTypeList(), id);
        } catch (SQLException e) {
            LOG.error("update product sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("update product sql exception" + e.getMessage());
        } finally {
            closeResources(pstmt, connection);
        }
        return 1;
    }

    /* (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchUpdate(java.util.List)
      */
    @Override
    public void batchUpdate(List<ProductDomain> entities, Long[] id) {

    }

    /* (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#delete(java.lang.Object)
      */
    @Override
    public int delete(Long id) {
        if (isUseProduct(id)) {
            return -1;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();

            StringBuilder querySelect = new StringBuilder();

            querySelect.append(SQL_DELETE).append(PRODUCT_TABLE_NAME)
                    .append(SQL_WHERE).append(PRODUCT_ID).append("=?");
            preparedStatement = connection.prepareStatement(querySelect.toString());
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("delete product sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("delete product naming exception" + e.getMessage());
        } finally {
            closeResources(preparedStatement, connection);
        }
        return id.intValue();
    }

    /* (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchDelete(java.util.List)
      */
    @Override
    public void batchDelete(List<String> id) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String param = initStringByListString(id) + ")";
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_DELETE).append(PRODUCT_TABLE_NAME)
                    .append(SQL_WHERE).append(PRODUCT_ID).append(SQL_IN)
                    .append(param);
            statement.executeUpdate(querySelect.toString());
        } catch (SQLException e) {
            LOG.error("batcDelete product sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("batchDelete product naming exception" + e.getMessage());
        } finally {
            closeResources(statement, connection);
        }
    }

    /*
      *  (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ItemDao#ItemfindItemByID(java.lang.Long)
      */
    @Override
    public ProductDomain getById(Long Id) {
        ProductDomain productDomain = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();

            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).
                    append(PRODUCT_TABLE_NAME).append(SQL_WHERE).append(PRODUCT_ID).
                    append("= ").append(Id);
            String sqlString = querySelect.toString();
            rs = statement.executeQuery(sqlString);
            productDomain = initProductDomainByResultSet(rs);
        } catch (SQLException e) {
            LOG.error("get product by id product naming exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("get product by id product naming exception" + e.getMessage());
        } finally {
            closeResources(rs, statement, connection);
        }

        return productDomain;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductDao#hideProduct(java.lang.Long)
      */
    @Override
    public void hideProduct(Long id, Integer hideFlag) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_UPDATE).append(PRODUCT_TABLE_NAME).append(SQL_SET).
                append(PRODUCT_HIDE).append("=?").append(SQL_WHERE).
                append(PRODUCT_ID).append("=?");
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.setInt(1, hideFlag);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("hide product sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("hide product naming exception" + e.getMessage());
        } finally {
            closeResources(pstmt, connection);
        }

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductDao#searchProduct(am.ucom.dinning.web.model.ProductSearchCriteriaBean, java.lang.Long)
      */
    @Override
    public ProductSearchResult<ProductDomain> searchProduct(ProductSearchCriteriaBean searchBean, Integer pageId) {
        ProductSearchResult<ProductDomain> prodSearchResult = new ProductSearchResult<ProductDomain>();
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(" * ").append(SQL_FROM).
                append(PRODUCT_TABLE_NAME).append(SQL_WHERE);
        try {
            connection = getConnection();
            st = connection.createStatement();
            // search product by type
            searchByType(searchBean.getTypeIdes(), sql);
            // search products by same names
            searchByName(searchBean.getName(), sql);
            // search products by same codes
            searchByCode(searchBean.getCode(), sql);
            // search products by same description
            searchByDescription(searchBean.getDescription(), sql);
            // search products by price
            searchByPriceMin(searchBean.getPriceMin(), sql);
            // search products by price
            searchByPriceMax(searchBean.getPriceMax(), sql);
            // searched hidden products
            searchHidden(searchBean.isHidden(), sql);
            // search filter
            searchFilter(searchBean.getSearchFilterIds(), sql);
            String sqlCommand = sql.toString().trim();
            // delete latest and
            if (sqlCommand.charAt(sqlCommand.length() - 1) == 'D') {
                sqlCommand = sqlCommand.substring(0, sqlCommand.length() - 3);
            } else {
                sqlCommand = sqlCommand.substring(0, sqlCommand.length() - 5);
            }
            Integer count = getCountByCriteria(sql);
            int countPage = pageId * 10;
            if (countPage >= count && countPage != 0) {
                pageId = pageId - 1;
            }
            prodSearchResult.setCount(count);
            prodSearchResult.setPageNumber(pageId);
            sqlCommand = sqlCommand + getByPaging(pageId);
            rs = st.executeQuery(sqlCommand);
            prodSearchResult.setDomainList(initListProductDomainByResultSet(rs));
        } catch (SQLException e) {
            LOG.error("search product sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("search product naming exception" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return prodSearchResult;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductDao#getProducts(java.lang.Integer)
      */
    @Override
    public ProductSearchResult<ProductDomain> getProducts(Integer pageNumber) {
        ProductSearchResult<ProductDomain> prodResult = new ProductSearchResult<ProductDomain>();
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        try {
            connection = MySqlDAOFactory.getConnection();
            st = connection.createStatement();
            Integer count = getProductCount();
            int countPage = pageNumber * 10;
            if (countPage >= count && countPage != 0) {
                pageNumber = pageNumber - 1;
            }
            sql.append(SQL_SELECT).append(" * ").append(SQL_FROM).
                    append(PRODUCT_TABLE_NAME).append(SQL_WHERE).
                    append("!").append(PRODUCT_HIDE).append(getByPaging(pageNumber));
            prodResult.setCount(count);
            prodResult.setPageNumber(pageNumber);
            rs = st.executeQuery(sql.toString());
            prodResult.setDomainList(initListProductDomainByResultSet(rs));
        } catch (SQLException e) {
            LOG.error("getProducts sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("getProducts count naming exception" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return prodResult;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductDao#isNameAndCodeUnique(java.lang.String, java.lang.String, java.lang.Long)
      */
    @Override
    public boolean isNameAndCodeUnique(String name, String code, Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(PRODUCT_ID).append(SQL_FROM).
                append(PRODUCT_TABLE_NAME).append(SQL_WHERE).append("(").
                append(PRODUCT_NAME).append("= '").append(name).append(" '").
                append(SQL_OR).append(PRODUCT_CODE).append("= '").
                append(code).append("')");
        if (id != null) {
            sql.append(SQL_AND).append(PRODUCT_ID).append("!=").append(id);
        }
        boolean isUnique = true;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            if (rs.next()) {
                isUnique = false;
            }
        } catch (SQLException e) {
            LOG.error("check name and code unique sql error in ProductDaoImpl" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("check name and code unique naming error in ProductDaoImpl" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return isUnique;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductDao#getImgUrlById(java.lang.Long)
      */
    @Override
    public String getImgUrlById(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(PRODUCT_PICTURE_URL).append(SQL_FROM).
                append(PRODUCT_TABLE_NAME).append(SQL_WHERE).append(PRODUCT_ID).
                append("=").append(id);
        String imgUrl = null;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            if (rs.next()) {
                imgUrl = rs.getString(1);
            }
        } catch (SQLException e) {
            LOG.error("get imgUrl by id sql error in ProductDaoImpl" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("get imgUrl by id naming error in ProductDaoImpl" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return imgUrl;
    }


    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductDao#isUsedProduct(java.lang.String)
      */
    public List<String> usedProductsIdList(List<String> ids) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_DISTINCT).append(MenuDao.MENU_PRODUCT_ID).
                append(SQL_FROM).append(" menu_items ").append(SQL_WHERE).
                append("product_id").append(SQL_IN).
                append(initStringByListString(ids)).append(")");
        List<String> noDeleteProductIds = new ArrayList<String>();
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            while (rs.next()) {
                noDeleteProductIds.add(rs.getString(1));
            }
        } catch (SQLException e) {
            LOG.error("sql error in ProductDaoImpl method isUsedProduct" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("naming error in ProductDaoImpl method isUsedProduct" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return noDeleteProductIds;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductDao#searchByType(java.lang.Long)
      */
    @Override
    public List<ProductDomain> searchByType(String typeId) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(" * ").append(SQL_FROM).
                append(PRODUCT_TABLE_NAME).append(SQL_INNER_JOIN).
                append(PRODUCT_CATEGORY_TABLE_NAME).append(SQL_ON).
                append(PRODUCT_TABLE_NAME).append(".").append(PRODUCT_ID).
                append("=").append(PRODUCT_CATEGORY_TABLE_NAME).append(".").
                append(PRODUCT_CATEGORY_PRODUCT_ID).append(SQL_AND).
                append(PRODUCT_CATEGORY_TABLE_NAME).append(".").
                append(PRODUCT_CATEGORY_CATEGORY_ID).append("=").append(typeId);
        List<ProductDomain> productList = null;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            productList = initListProductDomainByResultSet(rs);
        } catch (SQLException e) {
            LOG.error("sql error in ProductDaoImpl method searchByType" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("naming error in ProductDaoImpl method searchByType" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return productList;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductDao#searchByCode(java.lang.String)
      */
    @Override
    public ProductDomain searchByCode(String code) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(" * ").append(SQL_FROM).append(PRODUCT_TABLE_NAME).
                append(SQL_WHERE).append(PRODUCT_CODE).append("=").append(code);
        ProductDomain productDomain = null;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            productDomain = initProductDomainByResultSet(rs);
        } catch (SQLException e) {
            LOG.error("sql error in ProductDaoImpl method searchByCode" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("naming error in ProductDaoImpl method searchByCode" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return productDomain;
    }


    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAll()
      */
    @Override
    public List<ProductDomain> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.ProductDao#getAllProductNames()
      */
    @Override
    public List<String> getAllNames(Long id) {
        return null;
    }

    @Override
    public List<ProductDomain> getCurrentDateMenuAndBoughtProductList() {
        List<ProductDomain> productDomainList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();

            sql.append(SQL_SELECT).append("*")
                    .append(SQL_FROM)
                    .append(PRODUCT_TABLE_NAME)
                    .append(SQL_WHERE).append(PRODUCT_ID).append(SQL_IN)
                    .append("(").append(SQL_SELECT).append("product_id")
                    .append(SQL_FROM).append("menu_items").append(SQL_WHERE)
                    .append("menu_id").append(SQL_IN).append("(")
                    .append(SQL_SELECT).append("id").append(SQL_FROM).append("menu")
                    .append(SQL_WHERE).append("menu_actual_date = CURDATE()))")
                    .append(SQL_OR).append(PRODUCT_ID).append(SQL_IN)
                    .append("(").append(SQL_SELECT).append("product_id").append(SQL_FROM)
                    .append("employee_bought_items").append(SQL_WHERE).append("bought_date = CURDATE())")
                    .append(SQL_OR).append(PRODUCT_ID).append(SQL_IN).append("(").append(SQL_SELECT)
                    .append("product_id").append(SQL_FROM).append("bought_items").append(SQL_WHERE).append("bought_date = CURDATE())");

            rs = statement.executeQuery(sql.toString());
            productDomainList = initListProductDomainByResultSet(rs);
        } catch (SQLException e) {
            LOG.error("sql error in ProductDaoImpl method searchByCode" + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("naming error in ProductDaoImpl method searchByCode" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return productDomainList;
    }

    /**
     * This method for search without use id's
     *
     * @param filter
     * @param sql
     */
    private void searchFilter(String filter, StringBuilder sql) {
        if (filter != null && !filter.equals("")) {
            String idList = filter.substring(0, filter.length() - 1);
            sql.append(PRODUCT_ID).append(" NOT IN ").append("(").append(idList).append(")").append(SQL_AND);
        }
    }

    /**
     * get by page limit
     *
     * @param pageId
     */
    private String getByPaging(Integer pageId) {
        StringBuilder sql = new StringBuilder();
        Integer limitMin = pageId * 10;
        Integer limitMax = 10;
        sql.append(SQL_ORDER_BY).append(PRODUCT_CHANGED_DATE).append(ORDER_DESC).
                append(SQL_LIMIT).append(limitMin).append(" , ").append(limitMax);
        return sql.toString().trim();
    }

    /**
     * method searched by name
     *
     * @param name
     * @param sql
     */
    private void searchByName(String name, StringBuilder sql) {
        if (name != null && !name.equals("")) {
            sql.append(PRODUCT_NAME).append(SQL_LIKE).append("'%").
                    append(name).append("%'").append(SQL_AND);
        }
    }

    /**
     * method searched by code
     *
     * @param code
     * @param sql
     */
    private void searchByCode(String code, StringBuilder sql) {
        if (code != null && !code.equals("")) {
            sql.append(PRODUCT_CODE).append(SQL_LIKE).append("'%").
                    append(code).append("%'").append(SQL_AND);
        }
    }

    /**
     * method searched by description
     *
     * @param description
     * @param sql
     */
    private void searchByDescription(String description, StringBuilder sql) {
        if (description != null && !description.equals("")) {
            sql.append(PRODUCT_DESCRIPTION).append(SQL_LIKE).
                    append("'%").append(description).append("%'").append(SQL_AND);
        }
    }

    /**
     * method searched by priceMin
     *
     * @param priceMin
     * @param sql
     */
    private void searchByPriceMin(String priceMin, StringBuilder sql) {
        if (priceMin != null && !priceMin.equals("")) {
            sql.append(PRODUCT_PRICE).append(">=").append(priceMin).append(SQL_AND);
        }
    }

    /**
     * method searched by priceMax
     *
     * @param priceMax
     * @param sql
     */
    private void searchByPriceMax(String priceMax, StringBuilder sql) {
        if (priceMax != null && !priceMax.equals("")) {
            sql.append(PRODUCT_PRICE).append("<=").append(priceMax).append(SQL_AND);
        }
    }

    /**
     * method searched hide product
     *
     * @param hide
     * @param sql
     */
    private void searchHidden(boolean hide, StringBuilder sql) {
        if (hide) {
            sql.append(PRODUCT_HIDE).append(SQL_AND);
        } else {
            sql.append("!").append(PRODUCT_HIDE).append(SQL_AND);
        }
    }

    /**
     * method searched by types
     *
     * @param typesId
     * @param sql     - StringBuilder
     */
    private void searchByType(String[] typesId, StringBuilder sql) {
        if (typesId != null) {
            sql.append(PRODUCT_ID).append(SQL_IN).append("(");
            for (int i = 0; i < typesId.length; i++) {
                sql.append(SQL_SELECT).append(SQL_DISTINCT).
                        append(PRODUCT_CATEGORY_PRODUCT_ID).append(SQL_FROM).
                        append(PRODUCT_CATEGORY_TABLE_NAME).append(SQL_WHERE).
                        append(PRODUCT_CATEGORY_CATEGORY_ID).append("=").
                        append(typesId[i]);
                if (i != 0) {
                    sql.append(")");
                }
                if (i != typesId.length - 1) {
                    sql.append(SQL_OR).append(PRODUCT_CATEGORY_PRODUCT_ID).
                            append(SQL_IN).append("(");
                }
            }
            sql.append(")");
            sql.append(SQL_AND);
        }
    }

    /**
     * productTypeListById for returned productTypeList
     *
     * @return List<ProductTypeDomain>
     */
    private List<ProductTypeDomain> productTypeListById(Long id) {
        List<ProductTypeDomain> typeList = new ArrayList<ProductTypeDomain>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append(PRODUCT_CATEGORY_CATEGORY_ID).
                    append(SQL_FROM).append(PRODUCT_CATEGORY_TABLE_NAME).append(SQL_WHERE).
                    append(PRODUCT_CATEGORY_PRODUCT_ID).append("= ").append(id);
            String sqlString = querySelect.toString();
            rs = statement.executeQuery(sqlString);
            ProductTypeDaoImpl productTypeDaoImpl;
            while (rs.next()) {
                productTypeDaoImpl = new ProductTypeDaoImpl();
                typeList.add(productTypeDaoImpl.getById(rs.getLong(1)));
            }
        } catch (SQLException e) {
            LOG.error("get product type list by product id sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("get product type list by product id naming exception" + e.getMessage());
        } finally {
            closeResources(rs, statement, connection);
        }

        return typeList;
    }

    /**
     * @param typeList
     * @param pstmt
     * @param productId
     */
    private int insertProductCategoryIds(List<ProductTypeDomain> typeList, Long productId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT).append(PRODUCT_CATEGORY_TABLE_NAME).
                append("(").append(PRODUCT_CATEGORY_PRODUCT_ID).append(",").
                append(PRODUCT_CATEGORY_CATEGORY_ID).append(")").
                append(SQL_VALUES).append("(?,?)");
        try {
            connection = MySqlDAOFactory.getConnection();
            pstmt = connection.prepareStatement(sql.toString());
            for (ProductTypeDomain typeListIds : typeList) {
                pstmt.setLong(1, productId);
                pstmt.setLong(2, typeListIds.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error("sql exception in class ProductDaoImpl insertProductCategoryIds" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("naming exception in class ProductDaoImpl insertProductCategoryIds" + e.getMessage());
        } finally {
            closeResources(pstmt, connection);
        }
        return 1;
    }


    /**
     * @param ids - List string
     * @return String
     */
    private String initStringByListString(List<String> ids) {
        StringBuilder str = new StringBuilder();
        str.append("(");
        for (String id : ids) {
            str.append(id).append(",");
        }
        return str.substring(0, str.length() - 1);
    }


    /**
     * deletes product category id
     *
     * @param id - Long
     */
    private void deletePrCatIds(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_DELETE).append(PRODUCT_CATEGORY_TABLE_NAME).
                append(SQL_WHERE).append(PRODUCT_CATEGORY_PRODUCT_ID).
                append("=").append(id);
        Statement st = null;
        Connection connection = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            st = connection.createStatement();
            st.executeUpdate(sql.toString());
        } catch (SQLException e) {
            LOG.error("delete product category ids sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("delete product category ids naming exception" + e.getMessage());
        } finally {
            closeResources(st, connection);
        }
    }

    /**
     * @param id - Long
     * @return boolean - is use product return true else return false
     */
    private boolean isUseProduct(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append("product_id").append(SQL_FROM).
                append(" menu_items ").append(SQL_WHERE).append("product_id=").
                append(id);
        boolean isUse = false;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            if (rs.next()) {
                isUse = true;
            }
        } catch (SQLException e) {
            LOG.error("sql error in ProductDaoImpl method isUsedProduct" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("naming error in ProductDaoImpl method isUsedProduct" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return isUse;
    }

    /**
     * @param sqlSearch - StringBuilder search criteria
     * @return Integer - count products by criteria
     */
    private Integer getCountByCriteria(StringBuilder sqlSearch) {
        Integer count = null;
        StringBuilder sqlCount = new StringBuilder();
        sqlCount.append(SQL_SELECT).append("COUNT(*)").append(SQL_FROM).append("(").
                append(sqlSearch).append("1").append(")").append("as y");
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sqlCount.toString());
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("getCountByCriteria product sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("getCountByCriteria product naming exception" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return count;
    }

    /**
     * @return Integer - all product counts
     */
    private Integer getProductCount() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append("COUNT( * )").append(SQL_FROM).
                append(PRODUCT_TABLE_NAME);
        Integer count = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("getProductCount sql exception" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("getProductCount naming exception" + e.getMessage());
        } finally {
            closeResources(rs, st, connection);
        }
        return count;
    }

    /**
     * @param rs - ResultSet:  list products
     * @return List<ProductDomain>
     * @throws SQLException
     */
    private List<ProductDomain> initListProductDomainByResultSet(ResultSet rs) throws SQLException {
        List<ProductDomain> domainList = new ArrayList<ProductDomain>();
        while (rs.next()) {
            ProductDomain productDomain = new ProductDomain();
            productDomain.setId(rs.getLong(1));
            productDomain.setName(rs.getString(2));
            productDomain.setPrice(rs.getInt(3));
            productDomain.setImgUrl(rs.getString(4));
            productDomain.setDescription(rs.getString(5));
            productDomain.setCode(rs.getString(6));
            productDomain.setMeasurementId(rs.getLong(7));
            productDomain.setHidden(rs.getBoolean(8));
            productDomain.setCreatedAt(rs.getTimestamp(9));
            productDomain.setChangedAt(rs.getTimestamp(10));
            productDomain.setDiscountPrice(rs.getInt(11));
            List<ProductTypeDomain> productTypeList = productTypeListById(productDomain.getId());
            productDomain.setProductTypeList(productTypeList);
            domainList.add(productDomain);
        }
        return domainList;
    }

    /**
     * @param rs - ResultSet
     * @return ProductDomain - initilize by rs
     * @throws SQLException
     */
    private ProductDomain initProductDomainByResultSet(ResultSet rs) throws SQLException {
        ProductDomain productDomain = null;
        if (rs.next()) {
            productDomain = new ProductDomain();
            productDomain.setId(rs.getLong(1));
            productDomain.setName(rs.getString(2));
            productDomain.setPrice(rs.getInt(3));
            productDomain.setImgUrl(rs.getString(4));
            productDomain.setDescription(rs.getString(5));
            productDomain.setCode(rs.getString(6));
            productDomain.setMeasurementId(rs.getLong(7));
            productDomain.setHidden(rs.getBoolean(8));
            productDomain.setCreatedAt(rs.getTimestamp(9));
            productDomain.setChangedAt(rs.getTimestamp(10));
            productDomain.setDiscountPrice(rs.getInt(11));
            productDomain.setProductTypeList(productTypeListById(productDomain.getId()));
        }
        return productDomain;
    }

}
