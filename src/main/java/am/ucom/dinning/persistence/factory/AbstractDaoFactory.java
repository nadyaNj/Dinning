package am.ucom.dinning.persistence.factory;

import am.ucom.dinning.persistence.factory.mysql.MySqlDAOFactory;
import am.ucom.dinning.util.Constants;

/**
 * abstract class have one method,
 * the method return DAOFactory
 * @author arthur
 *
 */
public abstract class AbstractDaoFactory {
	
	/**
	 * check whichFactory and return connect database
	 * @param whichFactory - int
	 * @return AbstractDaoFactory
	 */
    public static AbstractDaoFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case Constants.TYPE_MYSQL: {
                return new MySqlDAOFactory();
            }
            default: {
                return null;
            }
        }
    }
}
