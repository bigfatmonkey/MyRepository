package net.savka.dao.impl;

import net.savka.dao.RoleDao;
import net.savka.dao.dataSource.AbstractJdbcDao;
import net.savka.entities.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: savka
 * Date: 12/2/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
//класс для сохранения/чтения/изменения/ и т.д. сущности Role в Базу данных
public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao {
    private Log log = LogFactory.getLog(JdbcRoleDao.class);

    //метод для записи роли в базу данных
    @Override
    public final void create(final Role role) throws SQLException {
        final String sqlForRole = "INSERT INTO role(id,name) values(?,?)"; //подготавливаем sql
        Connection connection = createConnection();      //создаем соединение
        // что такое PreparedStatement можно почитать в документации (она здесь есть)
        PreparedStatement preparedStatementForRole = null;

        try {
            preparedStatementForRole = connection.prepareStatement(sqlForRole); //запысываем sql
            //устанавливаем для него недостающие значения вместо знаков "?"
            preparedStatementForRole.setLong(1, role.getId());
            preparedStatementForRole.setString(2, role.getName());
            //выполняем запрос
            preparedStatementForRole.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                try {
                    //закрываем соединение
                    connection.close();
                } catch (SQLException ex) {
                    log.warn(ex);
                }
            }
        }
    }

    //остальные методы работают аналогично
    @Override
    public final void update(final Role role) {
        final String sql = "UPDATE  role SET name=? where id = ? ;";
        Connection connection = createConnection();
        PreparedStatement preparedStatementForRole = null;

        try {
            preparedStatementForRole = connection.prepareStatement(sql);
            preparedStatementForRole.setLong(2, role.getId());
            preparedStatementForRole.setString(1, role.getName());
            preparedStatementForRole.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    log.warn(ex);
                }
            }
        }
    }

    @Override
    public final void remove(final Role role) {
        final String sql = "DELETE FROM role WHERE id=?;";
        Connection connection = createConnection();
        PreparedStatement preparedStatementForRole = null;

        try {
            preparedStatementForRole = connection.prepareStatement(sql);
            preparedStatementForRole.setLong(1, role.getId());

            preparedStatementForRole.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    log.warn(ex);
                }
            }
        }
    }

    @Override
    public final Role findByName(final String name) {
        final String sql = "SELECT * FROM role WHERE name=?";
        Connection connection = createConnection();
        PreparedStatement preparedStatementForRole = null;
        Role role = null;
        try {
            preparedStatementForRole = connection.prepareStatement(sql);
            preparedStatementForRole.setString(1, name);
            //    ResultSet - результат запроса, возваращенный базой данных
            ResultSet resaultSet = preparedStatementForRole.executeQuery();
            // присваиваем роли вычитанные значения.
            while (resaultSet.next()) {
                role = new Role(resaultSet.getLong("id"));
                role.setName(resaultSet.getString("name"));
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    log.warn(ex);
                }
            }
        }
        return role;
    }
}
