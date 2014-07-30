package am.ucom.dinning.persistence.factory.mysql;

import am.ucom.dinning.persistence.dao.impl.CompanyDaoImpl;
import am.ucom.dinning.persistence.factory.AbstractDaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * extends AbstractDaoFactory
 * have two methods getConnection and closeResources
 *
 * @author arthur
 */
public class MySqlDAOFactory extends AbstractDaoFactory {

    public static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);

    private static DataSource pool = null;

    private static final String CONTEXT_PROPERTY = "java:/comp/env";
    private static final String DATASOURCE_PROPERTY = "jdbc/mysql";

    /**
     * check if pool == null create new connection and return this
     * else return connection
     *
     * @return Connection
     * @throws SQLException
     * @throws NamingException
     */
    public static Connection getConnection() throws SQLException, NamingException {

        if (pool == null) {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(CONTEXT_PROPERTY);
            pool = (DataSource) envContext.lookup(DATASOURCE_PROPERTY);

            return pool.getConnection();

        } else {
            return pool.getConnection();
        }
    }

    /**
     * close all resources
     *
     * @param connection - Connection
     * @param stmt       - Statement
     * @param rs         - ResultSet
     * @throws SQLException
     */
    public static void closeResources(ResultSet rs, Statement stmt, Connection connection) {

        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("closer resources sql exception" + e.getMessage());
        }
    }

    /**
     * close all resources
     *
     * @param connection - Connection
     * @param pstmt      - PrepareStatement
     * @param rs         - ResultSet
     * @throws SQLException
     */
    public static void closeResources(ResultSet rs, PreparedStatement pstmt, Connection connection) {

        try {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("closer resources sql exception" + e.getMessage());
        }
    }

    /**
     * close all resources
     *
     * @param connection - Connection
     * @param pstmt      - PrepareStatement
     * @throws SQLException
     */
    public static void closeResources(PreparedStatement pstmt, Connection connection) {

        try {
            if (pstmt != null) {
                pstmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("closer resources sql exception" + e.getMessage());
        }
    }

    /**
     * close all resources
     *
     * @param connection - Connection
     * @param stmt       - PrepareStatement
     * @throws SQLException
     */
    public static void closeResources(Statement stmt, Connection connection) {

        try {
            if (stmt != null) {
                stmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("closer resources sql exception" + e.getMessage());
        }
    }
}
