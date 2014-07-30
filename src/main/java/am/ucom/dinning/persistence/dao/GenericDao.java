package am.ucom.dinning.persistence.dao;

import java.util.List;


/**
 * Class description here.
 *
 * @author Hayk Hayryan
 * @version 1.0 8/31/11 6:09 PM
 */
public interface GenericDao<T> {

    String CATEGORY_TABLE_NAME = " category ";

    String PRODUCT_TABLE_NAME = " products ";

    String PRODUCT_CATEGORY_TABLE_NAME = " product_category ";

    String SQL_SELECT = " SELECT ";

    String SQL_FROM = " FROM ";

    String SQL_WHERE = " WHERE ";

    String SQL_BETWEEN = " BETWEEN ";

    String SQL_INSERT = " INSERT INTO ";

    String SQL_UPDATE = " UPDATE ";

    String SQL_DELETE = " DELETE FROM ";

    String SQL_VALUES = " VALUES ";

    String SQL_SET = " SET ";

    String SQL_AND = " AND ";

    String SQL_DISTINCT = " DISTINCT ";

    String SQL_LIKE = " LIKE ";

    String SQL_INNER_JOIN = " INNER JOIN ";

    String SQL_ON = " ON ";

    String SQL_OR = " OR ";

    String SQL_IN = " IN ";

    String SQL_LIMIT = " LIMIT ";

    String PRODUCT_CATEGORY_PRODUCT_ID = " prcat_pr_id ";

    String PRODUCT_CATEGORY_CATEGORY_ID = "prcat_cat_id ";

    String SQL_ORDER_BY = " ORDER BY ";

    String ORDER_DESC = " DESC ";

    /**
     * Insert entity to database
     *
     * @param entity
     * @return
     */
    Long save(T entity);

    /**
     * Insert a set of entities form to persistence storage.
     */
    void batchSave(List<T> entity);

    /**
     * update entity from persistence storage
     *
     * @param entity
     */
    int update(T entity);

    /**
     * Update a set of entities from the persistence storage
     *
     * @param entities
     * @param id
     */
    void batchUpdate(List<T> entities, Long[] id);

    /**
     * delete entity from persistent storage.
     *
     * @param id
     */
    int delete(Long id);

    /**
     * Delete a set of entities form the persistent storage
     *
     * @param id
     */
    void batchDelete(List<String> id);

    /**
     * Get object by selected Id
     *
     * @param Id
     * @return Object
     */
    T getById(Long Id);

    /**
     * Get everything from table
     *
     * @return List of objects
     */

    List<T> getAll();

    /**
     * get all names
     *
     * @return
     */
    List<String> getAllNames(Long id);

}
