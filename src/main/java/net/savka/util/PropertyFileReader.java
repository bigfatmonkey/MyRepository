package net.savka.util;

import net.savka.dao.impl.JdbcRoleDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: savka
 * Date: 12/4/13
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyFileReader {
    private static Log log = LogFactory.getLog(JdbcRoleDao.class);

    public Log getLogger() {
        return log;
    }

    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            prop.load(PropertyFileReader.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            log.error(e);
        }
        return prop;
    }
}
