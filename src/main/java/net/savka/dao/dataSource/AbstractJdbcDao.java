package net.savka.dao.dataSource;

import net.savka.util.PropertyFileReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: com.mycompany.savka
 * Date: 12/2/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */

public class AbstractJdbcDao {
    private static c3p0DataSource conp = new c3p0DataSource();

    static {
        Properties prop = PropertyFileReader.getProperties();
        conp.setDriverClass(prop.getProperty("driverClass"));
        conp.setJdbcUrl(prop.getProperty("database"));
        conp.setUser(prop.getProperty("dbUser"));
        conp.setPassword(prop.getProperty("dbPassword"));
        try {
            conp.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = conp.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
