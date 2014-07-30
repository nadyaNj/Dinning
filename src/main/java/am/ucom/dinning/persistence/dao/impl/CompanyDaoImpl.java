package am.ucom.dinning.persistence.dao.impl;

import am.ucom.dinning.persistence.dao.CompanyDao;
import am.ucom.dinning.persistence.domain.CompanyDomain;
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
 * Company Dao  Implementation class
 *
 * @author arthur
 */
public class CompanyDaoImpl implements CompanyDao {

    public static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#save(java.lang.Object)
      */
    @Override
    public Long save(CompanyDomain entity) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT).append(COMPANY_TABLE_NAME).append("(").
                append(COMPANY_NAME).append(")").
                append(SQL_VALUES).append("(?)");

        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.setString(1, entity.getCompanyName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("CompanyDomain instance SQL error happened in time save: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("CompanyDomain instance naming error happend in time save", e.getMessage(), e);
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
    public void batchSave(List<CompanyDomain> entity) {
        // TODO Auto-generated method stub

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#update(java.lang.Object)
      */
    @Override
    public int update(CompanyDomain entity) {
        StringBuilder sql = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        Long id = entity.getId();
        try {
            connection = getConnection();

            sql = new StringBuilder();

            sql.append(SQL_UPDATE).append(COMPANY_TABLE_NAME).append(SQL_SET)
                    .append(COMPANY_NAME).append("=").append("?")
                    .append(SQL_WHERE).append(COMPANY_NAME_ID).append("=").append("?");

            String sqlString = sql.toString();
            pstmt = connection.prepareStatement(sqlString);

            pstmt.setString(1, entity.getCompanyName());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("CompanyDomain instance SQL error happened in time update: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("CompanyDomain instance naming error happend in time update", e.getMessage(), e);
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
    public void batchUpdate(List<CompanyDomain> entities, Long[] id) {
        // TODO Auto-generated method stub

    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#delete(java.lang.Long)
      */
    @Override
    public int delete(Long id) {
        if (!companyNameIsUse(id)) {
            return 0;
        }
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_DELETE).append(COMPANY_TABLE_NAME).
                append(SQL_WHERE).append(COMPANY_NAME_ID).append("=").append(id);
        Connection connection = null;
        Statement st = null;
        try {
            connection = getConnection();
            st = connection.createStatement();
            st.executeUpdate(sql.toString());
        } catch (SQLException e) {
            LOG.error("CompanyDomain instance SQL error happened in time delete: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("CompanyDomain instance naming error happened in time delete: ", e.getMessage(), e);
        } finally {
            closeResources(st, connection);
        }
        return 1;
    }

    /**
     * This method for check company name is in use or no.
     *
     * @param id - Long
     * @return deleteBool - boolean
     */
    private boolean companyNameIsUse(Long id) {
        boolean deleteBool = true;
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(COMPANY_NAME_FOREGIN_ID).
                append(SQL_FROM).append(DEPARTAMENT_TABLE_NAME).
                append(SQL_WHERE).append(COMPANY_NAME_FOREGIN_ID).
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
            LOG.error("CompanyDomain instance SQL error happened in time when checked company is in use or no: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("CompanyDomain instance naming error happened in time when checked company is in use or no: ", e.getMessage(), e);
        } finally {
            closeResources(rs, st, connection);
        }
        return deleteBool;
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
    public CompanyDomain getById(Long Id) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAll()
      */
    @Override
    public List<CompanyDomain> getAll() {
        CompanyDomain domain = null;
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;

        List<CompanyDomain> domainList = new ArrayList<CompanyDomain>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append(" * ").append(SQL_FROM).
                    append(COMPANY_TABLE_NAME).append(" ORDER BY ").append(COMPANY_NAME).append(" ASC");

            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                domain = new CompanyDomain();

                domain.setId(rs.getLong(1));
                domain.setCompanyName(rs.getString(2));
                domain.setDepartmentsId(getDepartments(domain.getId()));
                domainList.add(domain);
            }
        } catch (SQLException e) {
            LOG.error("CompanyDomain instance SQL error happened in time when geting all companies: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("CompanyDomain instance naming error happened in time when geting all companies: ", e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return domainList;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.GenericDao#getAllNames(java.lang.Long)
      */
    @Override
    public List<String> getAllNames(Long id) {
        return null;
    }

    /**
     * Get departments parameters by id
     *
     * @param id - Long
     * @return company id list - List<DepartamentDomain>
     */
    private List<DepartamentDomain> getDepartments(Long id) {
        List<DepartamentDomain> compIdList = new ArrayList<DepartamentDomain>();
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        DepartamentDomain departamentDomain = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            StringBuilder querySelect = new StringBuilder();
            querySelect.append(SQL_SELECT).append(" * ").append(SQL_FROM).
                    append(DEPARTAMENT_TABLE_NAME).append(SQL_WHERE).append(COMPANY_NAME_FOREGIN_ID).append("=").append(id);

            rs = statement.executeQuery(querySelect.toString());
            while (rs.next()) {
                departamentDomain = new DepartamentDomain();
                departamentDomain.setId(rs.getLong(1));
                departamentDomain.setDepartamentName(rs.getString(2));
                departamentDomain.setCompanyId(rs.getLong(3));

                compIdList.add(departamentDomain);
            }
        } catch (SQLException e) {
            LOG.error("CompanyDomain instance SQL error happened in time when geting all departments by company_id: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("CompanyDomain instance naming error happened in time when geting all departments by company_id: ", e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return compIdList;
    }

    /*
      * (non-Javadoc)
      * @see am.ucom.dinning.persistence.dao.CompanyDao#isNameUnique(java.lang.Long)
      */
    @Override
    public String isNameUnique(CompanyDomain company) {
        StringBuilder sql = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String companyName = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            sql = new StringBuilder();
            sql.append(SQL_SELECT).append(COMPANY_NAME).append(SQL_FROM).append(COMPANY_TABLE_NAME).
                    append(SQL_WHERE);
            if (company.getId() != null) {
                sql.append(COMPANY_NAME_ID).append("!=").append(company.getId()).append(SQL_AND);
            }
            sql.append(COMPANY_NAME).append("=").append("'").append(company.getCompanyName()).append("'");
            rs = statement.executeQuery(sql.toString());
            while (rs.next()) {
                companyName = rs.getString(1);
            }
        } catch (SQLException e) {
            LOG.error("CompanyDomain instance SQL error happened in time when checked Company name unique: ", e.getMessage(), e);
        } catch (NamingException e) {
            LOG.error("CompanyDomain instance naming error happened in time when checked Company name unique: ", e.getMessage(), e);
        } finally {
            closeResources(rs, statement, connection);
        }
        return companyName;
    }
}