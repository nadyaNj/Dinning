package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.dao.DepartamentDao;
import am.ucom.dinning.persistence.domain.DepartamentDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.closeResources;
import static am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory.getConnection;

/**
 * Departament Dao Implementation class
 *
 * @author arthur
 */
public class DepartamentDaoImpl implements DepartamentDao {

    public static final Logger LOG = LoggerFactory.getLogger(DepartamentDaoImpl.class);

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#save(java.lang.Object)
      */
    @Override
    public Long save(DepartamentDomain entity) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT).append(DEPARTAMENT_TABLE_NAME).append("(").
                append(DEPARTAMENT_NAME).append(",").append(COMPANY_NAME_FOREGIN_ID).append(")").
                append(SQL_VALUES).append("(?,?)");

        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.setString(1, entity.getDepartamentName());
            pstmt.setLong(2, entity.getCompanyId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("DepartmentDomain instance SQL error happened in time save: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("DepartmentDomain instance naming error happend in time save", e.getMessage(), e);
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
    public void batchSave(List<DepartamentDomain> entity) {
        // TODO Auto-generated method stub

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#update(java.lang.Object)
      */
    @Override
    public int update(DepartamentDomain entity) {
        StringBuilder sql = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long id = entity.getId();
        try {
            connection = getConnection();

            sql = new StringBuilder();

            sql.append(SQL_UPDATE).append(DEPARTAMENT_TABLE_NAME).append(SQL_SET)
                    .append(DEPARTAMENT_NAME).append("=").append("?").append(",").append(COMPANY_NAME_FOREGIN_ID).append("=")
                    .append("?").append(SQL_WHERE).append(DEPARTAMENT_NAME_ID).append("=").append("?");

            String sqlString = sql.toString();
            pstmt = connection.prepareStatement(sqlString);

            pstmt.setString(1, entity.getDepartamentName());
            pstmt.setLong(2, entity.getCompanyId());
            pstmt.setLong(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("DepartmentDomain instance SQL error happened in time update: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("DepartmentDomain instance naming error happend in time update", e.getMessage(), e);
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
    public void batchUpdate(List<DepartamentDomain> entities, Long[] id) {
        // TODO Auto-generated method stub

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#delete(java.lang.Long)
      */
    @Override
    public int delete(Long id) {
        if (!departmentIsUse(id)) {
            return 0;
        }
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_DELETE).append(DEPARTAMENT_TABLE_NAME).
                append(SQL_WHERE).append(DEPARTAMENT_NAME_ID).append("=").append(id);
        Connection connection = null;
        Statement st = null;
        try {
            connection = getConnection();
            st = connection.createStatement();
            st.executeUpdate(sql.toString());
        } catch (SQLException e) {
            LOG.error("DepartmentDomain instance SQL error happened in time delete: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("DepartmentDomain instance naming error happend in time delete", e.getMessage(), e);
        } finally {
            closeResources(st, connection);
        }
        return 1;
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
      * @see am.ucom.dinning.persistence.dao.GenericDao#getById(java.lang.Long)
      */
    @Override
    public DepartamentDomain getById(Long Id) {
        Statement statement = null;
        DepartamentDomain departamentDomain = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();

            querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).append(DEPARTAMENT_TABLE_NAME)
                    .append(SQL_WHERE).append(DEPARTAMENT_NAME_ID).append("= ").append(Id);

            String sqlString = querySelect.toString();
            rs = statement.executeQuery(sqlString);

            if (rs.next()) {
                departamentDomain = new DepartamentDomain();
                departamentDomain.setId(rs.getLong(1));
                departamentDomain.setDepartamentName(rs.getString(2));
                departamentDomain.setCompanyId(rs.getLong(3));
                departamentDomain.setCreatedAt(rs.getTimestamp(3));
                departamentDomain.setChangedAt(rs.getTimestamp(4));
            }
        } catch (SQLException e) {
            LOG.error("DepartmentDomain instance SQL error happened in time when get department by id: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("DepartmentDomain instance naming error happend in time when get department by id", e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return departamentDomain;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAll()
      */
    @Override
    public List<DepartamentDomain> getAll() {
        return null;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAllNames(java.lang.Long)
      */
    @Override
    public List<String> getAllNames(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.DepartamentDao#getAllDepartaments(java.lang.Long)
      */
    @Override
    public List<DepartamentDomain> getAllDepartaments(Long id) {
        StringBuilder querySelect = new StringBuilder();
        querySelect.append(SQL_SELECT).append("*").append(SQL_FROM).
                append(DEPARTAMENT_TABLE_NAME);
        if (id != null) {
            querySelect.append(SQL_WHERE).append(DEPARTAMENT_NAME_ID).
                    append("!=").append(id);
        }
        querySelect.append(" ORDER BY ").append(DEPARTAMENT_NAME).append("ASC");
        return getDepartaments(querySelect.toString());
    }

    /**
     * Get departments by sql command
     *
     * @param sql - String
     * @return domainList - List<DepartamentDomain>
     */
    private List<DepartamentDomain> getDepartaments(String sql) {
        DepartamentDomain departamentDomain = null;
        Statement statement = null;
        List<DepartamentDomain> domainList = new ArrayList<DepartamentDomain>();
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                departamentDomain = new DepartamentDomain();
                departamentDomain.setId(rs.getLong(1));
                departamentDomain.setDepartamentName(rs.getString(2));
                departamentDomain.setCompanyId(rs.getLong(3));
                domainList.add(departamentDomain);
            }

        } catch (SQLException e) {
            LOG.error("DepartmentDomain instance SQL error happened in time when get all departments : ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("DepartmentDomain instance naming error happend in time when get all departments", e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return domainList;
    }

    /**
     * This method for check department in use or no.
     *
     * @param id - Long
     * @return deleteBool - boolean
     */
    private boolean departmentIsUse(Long id) {
        boolean deleteBool = true;
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(DEPARTAMENT_FOREGIN_ID).
                append(SQL_FROM).append(USERS_TABLE_NAME).
                append(SQL_WHERE).append(DEPARTAMENT_FOREGIN_ID).
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
            LOG.error("DepartmentDomain instance SQL error happened in time when checked department is use or no : ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("DepartmentDomain instance naming error happend in time when checked department is use or no:", e.getMessage(), e);
        } finally {
            closeResources(st, connection);
        }
        return deleteBool;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.DepartamentDao#getDepartamentsByCompany(java.lang.Long)
      */
    @Override
    public List<DepartamentDomain> getDepartamentsByCompany(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append("*").append(SQL_FROM).
                append(DEPARTAMENT_TABLE_NAME).append(SQL_WHERE).
                append(COMPANY_NAME_FOREGIN_ID).append("=").append(id);
        return getDepartaments(sql.toString());
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.DepartamentDao#getDepartamentsByDepartmentId(java.lang.Long)
      */
    @Override
    public List<DepartamentDomain> getDepartamentsByDepartmentId(Long id) {
        return getDepartamentsByCompany(getCompanyIdByDepartamentId(id));
    }

    /**
     * This method for get by company id department id.
     *
     * @param depId -Long
     * @return company id - Long
     */
    private Long getCompanyIdByDepartamentId(Long depId) {
        StringBuilder sql = new StringBuilder();
        Long companyId = null;
        sql.append(SQL_SELECT).append(COMPANY_NAME_FOREGIN_ID).append(SQL_FROM).
                append(DEPARTAMENT_TABLE_NAME).append(SQL_WHERE).
                append(DEPARTAMENT_NAME_ID).append("=").append(depId);
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()) {
                companyId = rs.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("DepartmentDomain instance SQL error happened in time when get company by department id : ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("DepartmentDomain instance naming error happend in time when get company by department id:", e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }

        return companyId;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.DepartamentDao#isDepartmentUnique(am.ucom.dinning.persistence.domain.DepartamentDomain)
      */
    @Override
    public String isDepartmentUnique(DepartamentDomain department) {
        StringBuilder sql = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String depName = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            sql = new StringBuilder();
            sql.append(SQL_SELECT).append(DEPARTAMENT_NAME).append(SQL_FROM).append(DEPARTAMENT_TABLE_NAME).
                    append(SQL_WHERE);
            if (department.getId() != null) {
                sql.append(DEPARTAMENT_NAME_ID).append("!=").append(department.getId()).append(SQL_AND);
            }
            sql.append(DEPARTAMENT_NAME).append("=").append("'").append(department.getDepartamentName()).append("'").
                    append(SQL_AND).append(COMPANY_NAME_FOREGIN_ID).append("=").append("'").
                    append(department.getCompanyId()).append("'");
            rs = statement.executeQuery(sql.toString());
            while (rs.next()) {
                depName = rs.getString(1);
            }
        } catch (SQLException e) {
            LOG.error("DepartmentDomain instance SQL error happened in time when checked is department unique or no : ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("DepartmentDomain instance naming error happend in time checked is department unique or no:", e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return depName;
    }
}