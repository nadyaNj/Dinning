package am.ucom.dinning.persistence.dao.impl;

import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.closeResources;
import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import am.ucom.dinning.persistence.dao.UserDao;
import am.ucom.dinning.persistence.domain.ProductSearchResult;
import am.ucom.dinning.persistence.domain.UserDomain;
import am.ucom.dinning.util.Constants;

/**
 * implements UserDao
 *
 * @author arthur
 */
public class UserDaoImpl implements UserDao {

    public static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#save(java.lang.Object)
      */
    @Override
    public Long save(UserDomain userDomain) {
        StringBuilder sql = new StringBuilder();

        sql.append(SQL_INSERT).append(USER_TABLE_NAME).append("(").
                append(USER_NAME).append(",").append(USER_PASSWORD).append(",").
                append(USER_ROLE_ID).append(",").
                append(USER_DEP_ID).append(",").append(USER_EMAIL).append(",").append(USER_POSITION).
                append(",").append(USER_CREATED_DATE).
                append(",").append(USER_CHANGED_DATE).append(",").append(USER_STATE_ID).append(",").append(USER_DISC_CODE).
                append(")").append(SQL_VALUES).append("(?,?,?,?,?,?,?,?,?,?)");
        ResultSet generatedKeys = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long newUserId = null;

        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, userDomain.getUsername());
            pstmt.setString(2, userDomain.getPassword());
            pstmt.setLong(3, userDomain.getRoleId());
            pstmt.setLong(4, userDomain.getUserDepId());
            pstmt.setString(5, userDomain.getUserEmail());
            pstmt.setString(6, userDomain.getUserPosition());
            pstmt.setTimestamp(7, userDomain.getCreatedAt());
            pstmt.setTimestamp(8, userDomain.getChangedAt());
            pstmt.setInt(9, userDomain.getStateId());
            pstmt.setString(10, userDomain.getDicountCode());


            pstmt.executeUpdate();

            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                newUserId = generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("Save user sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("Save user naming error: " + e.getMessage(), e);
        } finally {
            closeResources(pstmt, connection);
        }

        return newUserId;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#getUserByUsernamePassword(java.lang.String, java.lang.String)
      */
    @Override
    public UserDomain getUserByUsernamePassword(final String username, final String password) {
        StringBuilder sql = new StringBuilder();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserDomain domain = null;

        sql.append(SQL_SELECT).append("*").append(SQL_FROM).append(USER_TABLE_NAME)
                .append(SQL_WHERE).append(USER_NAME).append("=?").append(SQL_AND).append(USER_PASSWORD).append("=?");

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();
            domain = initResultSetDomain(rs);
        } catch (SQLException e) {
            LOG.error("SQL Error: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("SQL Naming Error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }

        return domain;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchSave(java.util.List)
      */
    @Override
    public void batchSave(List<UserDomain> entity) {

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#update(java.lang.Object, java.lang.Long)
      */
    @Override
    public int update(UserDomain userDomain) {
        StringBuilder sql = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long id = userDomain.getId();
        try {
            connection = getConnection();

            sql = new StringBuilder();

            sql.append(SQL_UPDATE).append(USER_TABLE_NAME).append(SQL_SET)
                    .append(USER_NAME).append("=").append("?").append(",")
                    .append(USER_ROLE_ID).append("=").append("?").append(",")
                    .append(USER_DEP_ID).append("=").append("?").append(",")
                    .append(USER_EMAIL).append("=").append("?").append(",")
                    .append(USER_POSITION).append("=").append("?").append(",")
                    .append(USER_CHANGED_DATE).append("=").append("?").append(",")
                    .append(USER_DISC_CODE).append("=").append("?")
                    .append(SQL_WHERE).append(USER_ID).append("=").append("?");

            String sqlString = sql.toString();
            pstmt = connection.prepareStatement(sqlString);

            pstmt.setString(1, userDomain.getUsername());
            pstmt.setLong(2, userDomain.getRoleId());
            pstmt.setLong(3, userDomain.getUserDepId());
            pstmt.setString(4, userDomain.getUserEmail());
            pstmt.setString(5, userDomain.getUserPosition());

            pstmt.setTimestamp(6, userDomain.getChangedAt());
            pstmt.setString(7, userDomain.getDicountCode());
            pstmt.setLong(8, id);

            pstmt.executeUpdate();


        } catch (SQLException e) {
            return 0;
        } catch (NamingException e) {
            return 0;
        } finally {
            closeResources(pstmt, connection);
        }
        return 1;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchUpdate(java.util.List, java.lang.Long[])
      */
    @Override
    public void batchUpdate(List<UserDomain> entities, Long[] id) {

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#delete(java.lang.Long)
      */
    @Override
    public int delete(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();

            StringBuilder querySelect = new StringBuilder();

            querySelect.append(SQL_DELETE).append(USER_TABLE_NAME)
                    .append(SQL_WHERE).append(USER_ID).append("=?");

            preparedStatement = connection.prepareStatement(querySelect.toString());
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeResources(preparedStatement, connection);
        }
        return 1;

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#batchDelete(java.util.List)
      */
    @Override
    public void batchDelete(List<String> id) {

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getById(java.lang.Long)
      */
    @Override
    public UserDomain getById(Long Id) {
        UserDomain userDomain = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).
                    append(USER_TABLE_NAME).append(SQL_WHERE).append(USER_ID).
                    append("= ").append(Id);
            String sqlString = querySelect.toString();
            rs = statement.executeQuery(sqlString);
            userDomain = new UserDomain();
            if (rs.next()) {
                userDomain = new UserDomain();
                userDomain.setId(rs.getLong(1));
                userDomain.setUsername(rs.getString(2));
                userDomain.setPassword(rs.getString(3));
                userDomain.setRoleId(rs.getLong(4));
                userDomain.setUserEmail(rs.getString(5));
                userDomain.setUserDepId(rs.getLong(6));
                userDomain.setUserPosition(rs.getString(7));
                userDomain.setStateId(rs.getInt(10));
                userDomain.setCreatedAt(rs.getTimestamp(8));
                userDomain.setChangedAt(rs.getTimestamp(9));
                userDomain.setDicountCode(rs.getString(10));

                // get company name by departmen id
                userDomain.setCompanyName(getCompanyNameByDepartmentId(userDomain.getUserDepId()));

                // get department name by department id
                userDomain.setDepartamentName(getDepartmentName(userDomain.getUserDepId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, statement, connection);
        }


        return userDomain;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAll()
      */
    @Override
    public List<UserDomain> getAll() {
        UserDomain userDomain = null;
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        List<UserDomain> userDomainList = new ArrayList<UserDomain>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).
                    append(USER_TABLE_NAME);

            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                userDomain = new UserDomain();
                userDomain.setId(rs.getLong(1));
                userDomain.setUsername(rs.getString(2));
                userDomain.setPassword(rs.getString(3));
                userDomain.setRoleId(rs.getLong(4));
                userDomain.setUserDepId(rs.getLong(5));
                userDomain.setUserEmail(rs.getString(6));
                userDomain.setUserPosition(rs.getString(7));
                userDomain.setStateId(rs.getInt(10));
                userDomain.setCreatedAt(rs.getTimestamp(8));
                userDomain.setChangedAt(rs.getTimestamp(9));
                userDomain.setDicountCode(rs.getString(10));
                userDomainList.add(userDomain);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, statement, connection);
        }

        return userDomainList;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#getIdByUsernamePassword()
      */
    @Override
    public List<UserDomain> getUserParameters(boolean bool) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append("*").append(SQL_FROM).
                append(USER_TABLE_NAME);
        if (!bool) {
            sql.append(SQL_WHERE).append(USER_STATE_ID).
                    append(" != ").append(Constants.USER_STATE_IN_ACTIVE);
        }
        List<UserDomain> listUsers = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            listUsers = new ArrayList<UserDomain>();
            connection = getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            UserDomain user = null;
            while (rs.next()) {
                user = new UserDomain();
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRoleId(rs.getLong(4));
                user.setUserEmail(rs.getString(5));
                user.setUserDepId(rs.getLong(6));
                user.setUserPosition(rs.getString(7));
                user.setStateId(rs.getInt(10));
                user.setDicountCode(rs.getString(11));
                listUsers.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, st, connection);
        }
        return listUsers;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAllNames()
      */
    @Override
    public List<String> getAllNames(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(USER_NAME).append(SQL_FROM).append(USER_TABLE_NAME);
        if (id != null) {
            sql.append(SQL_WHERE).append(USER_ID).append("!=").append(id);
        }
        List<String> userNames = null;
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            userNames = new ArrayList<String>();
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            while (rs.next()) {
                userNames.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, statement, connection);
        }
        return userNames;

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#setStatusId(am.ucom.dinning.persistence.domain.UserDomain)
      */
    public int setStatusId(UserDomain entity) {
        StringBuilder sql = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long id = entity.getId();
        try {
            connection = getConnection();

            sql = new StringBuilder();

            sql.append(SQL_UPDATE).append(USER_TABLE_NAME).append(SQL_SET)
                    .append(USER_STATE_ID).append("=").append("?").append(",")
                    .append(USER_CHANGED_DATE).append("=").append("?")
                    .append(SQL_WHERE).append(USER_ID).append("=").append("?");

            String sqlString = sql.toString();
            pstmt = connection.prepareStatement(sqlString);

            pstmt.setInt(1, entity.getStateId());
            pstmt.setTimestamp(2, entity.getChangedAt());
            pstmt.setLong(3, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } catch (NamingException e) {
            return 0;
        } finally {
            closeResources(pstmt, connection);
        }
        return 1;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#setPassword(am.ucom.dinning.persistence.domain.UserDomain)
      */
    public int setPassword(UserDomain entity) {
        StringBuilder sql = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long id = entity.getId();
        try {
            connection = getConnection();

            sql = new StringBuilder();

            sql.append(SQL_UPDATE).append(USER_TABLE_NAME).append(SQL_SET)
                    .append(USER_STATE_ID).append("=").append("?").append(",")
                    .append(USER_PASSWORD).append("=").append("?").append(",")
                    .append(USER_CHANGED_DATE).append("=").append("?")
                    .append(SQL_WHERE).append(USER_ID).append("=").append("?");

            String sqlString = sql.toString();
            pstmt = connection.prepareStatement(sqlString);

            pstmt.setInt(1, entity.getStateId());
            pstmt.setString(2, entity.getPassword());
            pstmt.setTimestamp(3, entity.getChangedAt());
            pstmt.setLong(4, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } catch (NamingException e) {
            return 0;
        } finally {
            closeResources(pstmt, connection);
        }
        return 1;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#updateUserPassword()
      */
    @Override
    public int updateUserPassword(String password, Long userId, int state) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_UPDATE).append(USER_TABLE_NAME).append(SQL_SET).
                append(USER_PASSWORD).append("=?,").append(USER_STATE_ID).
                append("=?").append(SQL_WHERE).append(USER_ID).append("=?");
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.setString(1, password);
            pstmt.setInt(2, state);
            pstmt.setLong(3, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } catch (NamingException e) {
            return 0;
        } finally {
            closeResources(pstmt, connection);
        }

        return 1;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#getUserParametersByName(java.lang.String)
      */
    @Override
    public UserDomain getUserParametersByName(String name) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append("*").append(SQL_FROM).
                append(USER_TABLE_NAME).append(SQL_WHERE).append(USER_NAME).
                append(" = ").append("'").append(name).append("'");
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;
        UserDomain user = null;
        try {
            connection = getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql.toString());
            user = new UserDomain();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRoleId(rs.getLong(4));
                user.setUserEmail(rs.getString(5));
                user.setUserDepId(rs.getLong(6));
                user.setUserPosition(rs.getString(7));
                user.setStateId(rs.getInt(10));
                user.setDicountCode(rs.getString(11));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, st, connection);
        }

        return user;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#getUsersByNumber(java.lang.Integer)
      */
    @Override
    public List<UserDomain> getUsersByNumber(Integer pageNumber) {
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        UserDomain user = null;

        List<UserDomain> usersList = new ArrayList<UserDomain>();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(SQL_SELECT).append(" * ").append(SQL_FROM).append(USER_TABLE_NAME)
                .append(SQL_WHERE).append(USER_ROLE_ID).append("=4")
                .append(SQL_ORDER_BY).append(USER_CREATED_DATE).append(ORDER_DESC)
                .append(SQL_LIMIT).append(pageNumber * 10).append(" , ").append(10);

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlBuilder.toString());
            while (rs.next()) {
                user = new UserDomain();
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRoleId(rs.getLong(4));
                user.setUserEmail(rs.getString(5));
                user.setUserDepId(rs.getLong(6));
                user.setUserPosition(rs.getString(7));
                user.setStateId(rs.getInt(10));
                user.setDicountCode(rs.getString(11));

                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, statement, connection);
        }

        return usersList;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#getUsersCount()
      */
    @Override
    public int getUsersCount() {
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        int count = 0;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append("COUNT").append("(*)").append(SQL_FROM).
                    append(USER_TABLE_NAME).append(SQL_WHERE).append(USER_ROLE_ID).append("=4");
            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, statement, connection);
        }
        return count;
    }

    /**
     * @return int - users count
     */
    private int getAllUsersCount() {
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        int count = 0;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append("COUNT").append("(*)").
                    append(SQL_FROM).append(USER_TABLE_NAME).append(SQL_WHERE).
                    append(USER_ID).append("!=").append(1);

            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, statement, connection);
        }
        return count;
    }

    /**
     * return List user domain by pageNumber
     *
     * @param pageNumber - Integer
     * @return List<UserDomain>
     */
    private List<UserDomain> getAllUsersByPageNumber(Integer pageNumber) {
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        UserDomain user = null;

        List<UserDomain> usersList = new ArrayList<UserDomain>();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(SQL_SELECT).append(" * ").append(SQL_FROM).
                append(USER_TABLE_NAME).append(SQL_WHERE).append(USER_ID).
                append("!=").append(1).append(SQL_ORDER_BY).
                append(USER_CREATED_DATE).append(ORDER_DESC).
                append(SQL_LIMIT).append(pageNumber * 10).append(" , ").append(10);

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlBuilder.toString());
            while (rs.next()) {
                user = new UserDomain();
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRoleId(rs.getLong(4));
                user.setUserEmail(rs.getString(5));
                user.setUserDepId(rs.getLong(6));
                user.setUserPosition(rs.getString(7));
                user.setStateId(rs.getInt(10));
                user.setDicountCode(rs.getString(11));
                user.setCompanyName(getCompanyNameByDepartmentId(user.getUserDepId()));
                user.setDepartamentName(getDepartmentName(user.getUserDepId()));
                usersList.add(user);
            }
        } catch (SQLException e) {
            LOG.error("get List user sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("get List user naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return usersList;
    }

    /**
     * return company name by department id
     *
     * @param id - Long department id
     * @return String - company name
     */
    private String getCompanyNameByDepartmentId(Long id) {
        String companyName = null;
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(COMPANY_TABLE_NAME).append(".").
                append(COMPANY_NAME).append(SQL_FROM).append(COMPANY_TABLE_NAME).
                append(SQL_INNER_JOIN).append(" department ").append(SQL_ON).
                append(COMPANY_TABLE_NAME).append(".").append("id=(").
                append(SQL_SELECT).append(" company_id ").append(SQL_FROM).
                append(" department ").append(SQL_WHERE).
                append(" department.id=").append(id).append(")");
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()) {
                companyName = rs.getString(1);
            }
        } catch (SQLException e) {
            LOG.error("get Company name sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("get Department name naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return companyName;
    }

    /**
     * return department name by department id
     *
     * @param id - Long department id
     * @return String - department name
     */
    private String getDepartmentName(Long id) {
        String departmenName = null;
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(" name ").append(SQL_FROM).
                append(" department ").append(SQL_WHERE).append(" id= ").append(id);
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()) {
                departmenName = rs.getString(1);
            }
        } catch (SQLException e) {
            LOG.error("get Department name sql error: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("get Department name naming error: " + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return departmenName;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#getProductSearchResult(java.lang.Integer)
      */
    @Override
    public ProductSearchResult<UserDomain> getProductSearchResult(Integer pageNumber) {
        ProductSearchResult<UserDomain> result = new ProductSearchResult<UserDomain>();
        result.setCount(getCount(getAllUsersCount()));
        if (result.getCount() == pageNumber) {
            result.setPageNumber(pageNumber);
        } else {
            result.setPageNumber(pageNumber + 1);
        }
        result.setDomainList(getAllUsersByPageNumber(result.getPageNumber() - 1));
        return result;
    }


    /**
     * @param count
     * @return Integer - count division 10
     */
    private Integer getCount(int count) {
        int divCount;
        if (count % 10 == 0) {
            divCount = count / 10;
        } else {
            divCount = count / 10 + 1;
        }
        return divCount;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.UserDao#validName(java.lang.String)
      */
    @Override
    public boolean validUserName(String name, Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(USER_ID).append(SQL_FROM).
                append(USER_TABLE_NAME).append(SQL_WHERE).append(USER_NAME).
                append("= '").append(name).append("'");
        if (id != null) {
            sql.append(SQL_AND).append(USER_ID).append("!=").append(id);
        }
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        boolean isUnique = true;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()) {
                isUnique = false;
            }
        } catch (SQLException e) {
            LOG.error("sql select user id naming error" + e.getMessage());
        } catch (NamingException e) {
            LOG.error("sql select user id sql error" + e.getMessage());
        } finally {
            closeResources(rs, statement, connection);
        }
        return isUnique;
    }

    private UserDomain initResultSetDomain(ResultSet rs) throws SQLException {
        UserDomain user = null;
        if (rs.next()) {
            user = new UserDomain();
            user.setId(rs.getLong(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setRoleId(rs.getLong(4));
            user.setUserEmail(rs.getString(5));
            user.setUserDepId(rs.getLong(6));
            user.setUserPosition(rs.getString(7));
            user.setStateId(rs.getInt(10));
            user.setDicountCode(rs.getString(11));

            // get company name by departmen id
            user.setCompanyName(getCompanyNameByDepartmentId(user.getUserDepId()));

            // get department name by department id
            user.setDepartamentName(getDepartmentName(user.getUserDepId()));
        }

        return user;
    }
    
    /*
     * (non-Javadoc)
     * @see am.ucom.dinning.persistence.dao.UserDao#getUserByCode(java.lang.String)
     */
	@Override
	public UserDomain getUserByCode(String code) {
		Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        UserDomain userDomain = null;
        
        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();
            
    		sql.append(SQL_SELECT).append("*").append(SQL_FROM).append(USER_TABLE_NAME).
    			append(SQL_WHERE).append(USER_DISC_CODE).append(" ='").append(code).
    			append("'");
            rs = statement.executeQuery(sql.toString());
            userDomain = initResultSetDomain(rs);
        } catch (SQLException e) {
            LOG.error("get user SQL Exception: " + e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("get user SQL Naming Exception" + e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
		return userDomain;
	}
}