package net.savka.dao.impl;

import net.savka.dao.UserDao;
import net.savka.dao.dataSource.AbstractJdbcDao;
import net.savka.entities.Role;
import net.savka.entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: savka
 * Date: 12/2/13
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
//класс для сохранения/чтения/изменения/ сущности  и т.д. сущности User в Базу данных
//примичание: смотри коментарии класс JdbcrRoleDao.
public class JdbcUserDao extends AbstractJdbcDao implements UserDao {
    private Log log = LogFactory.getLog(JdbcRoleDao.class);

    @Override
    public final void create(final User user) {
        final String sql = "INSERT INTO user(login, password, email, firstname, lastname, birthday, role_id) values(?,?,?,?,?,?,?); ";
        Connection connection = createConnection();
        PreparedStatement preparedStatementForUser = null;
        try {
            preparedStatementForUser = connection.prepareStatement(sql);
            //   preparedStatementForUser.setLong(1, user.getId());
            preparedStatementForUser.setString(1, user.getLogin());
            preparedStatementForUser.setString(2, user.getPassword());
            preparedStatementForUser.setString(3, user.getEmail());
            preparedStatementForUser.setString(4, user.getFirstName());
            preparedStatementForUser.setString(5, user.getLastName());
            preparedStatementForUser.setDate(6, user.getBirthDate());
            preparedStatementForUser.setLong(7, user.getRole().getId());
            preparedStatementForUser.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e);
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
    public final void update(final User user) {

        final String sql = "UPDATE  user SET login=?, password=?, email=?," +
                " firstname=?, lastname=?, birthday=?, role_id=? where id = ? ;";
        Connection connection = createConnection();
        PreparedStatement preparedStatementForUser = null;

        try {
            preparedStatementForUser = connection.prepareStatement(sql);
            //     preparedStatementForUser.setLong(1, user.getId());
            preparedStatementForUser.setString(1, user.getLogin());
            preparedStatementForUser.setString(2, user.getPassword());
            preparedStatementForUser.setString(3, user.getEmail());
            preparedStatementForUser.setString(4, user.getFirstName());
            preparedStatementForUser.setString(5, user.getLastName());
            preparedStatementForUser.setDate(6, user.getBirthDate());
            preparedStatementForUser.setLong(7, user.getRole().getId());
            preparedStatementForUser.setLong(8, user.getId());
            preparedStatementForUser.executeUpdate();
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
    public final void remove(final User user) {
        final String sql = "DELETE FROM user WHERE id=?;";
        Connection connection = createConnection();
        PreparedStatement preparedStatementForUser = null;
        try {
            preparedStatementForUser = connection.prepareStatement(sql);
            preparedStatementForUser.setLong(1, user.getId());
            preparedStatementForUser.executeUpdate();
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
    public final List<User> findAll() {
        final String sql = "SELECT * FROM user AS u LEFT JOIN role AS r ON(u.role_id=r.id)";
        Connection connection = createConnection();
        ResultSet resaultSet = null;
        Statement statement = null;
        List<User> userList = new ArrayList<User>();
        try {
            statement = connection.createStatement();
            resaultSet = statement.executeQuery(sql);
            //    ResultSet - результат запроса, возваращенный базой данных
            while (resaultSet.next()) {
                User user = new User();
                user.setId(resaultSet.getLong("id"));
                user.setLogin(resaultSet.getString("login"));
                user.setPassword(resaultSet.getString("password"));
                user.setEmail(resaultSet.getString("email"));
                user.setBirthDate(resaultSet.getDate("birthday"));
                user.setFirstName(resaultSet.getString("firstname"));
                user.setLastName(resaultSet.getString("lastname"));
                Role role = new Role(resaultSet.getLong("role_id"));
                role.setName(resaultSet.getString("name"));
                user.setRole(role);
                userList.add(user);
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

        return userList;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public final User findByLogin(final String login) {
        final String sql = "SELECT * FROM user AS u LEFT JOIN role AS r ON(u.role_id=r.id) WHERE login=?";
        Connection connection = createConnection();
        PreparedStatement preparedStatementForUser = null;
        User user = null;
        try {
            preparedStatementForUser = connection.prepareStatement(sql);
            preparedStatementForUser.setString(1, login);
            //    ResultSet - результат запроса, возваращенный базой данных
            ResultSet resaultSet = preparedStatementForUser.executeQuery();
            while (resaultSet.next()) {
                user = new User();
                user.setId(resaultSet.getLong("id"));
                user.setLogin(resaultSet.getString("login"));
                user.setPassword(resaultSet.getString("password"));
                user.setEmail(resaultSet.getString("email"));
                user.setBirthDate(resaultSet.getDate("birthday"));
                user.setFirstName(resaultSet.getString("firstname"));
                user.setLastName(resaultSet.getString("lastname"));
                Role role = new Role(resaultSet.getLong("role_id"));
                role.setName(resaultSet.getString("name"));
                user.setRole(role);
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
        //throw new
        return user;      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public final User findByEmail(final String email) {
        final String sql = "SELECT * FROM user AS u LEFT JOIN role AS r ON(u.role_id=r.id) WHERE email=?";
        Connection connection = createConnection();
        PreparedStatement preparedStatementForUser = null;
        User user = null;
        try {
            preparedStatementForUser = connection.prepareStatement(sql);
            preparedStatementForUser.setString(1, email);
            ResultSet resaultSet = preparedStatementForUser.executeQuery();
            while (resaultSet.next()) {
                user = new User();
                user.setId(resaultSet.getLong("id"));
                user.setLogin(resaultSet.getString("login"));
                user.setPassword(resaultSet.getString("password"));
                user.setEmail(resaultSet.getString("email"));
                user.setBirthDate(resaultSet.getDate("birthday"));
                user.setFirstName(resaultSet.getString("firstname"));
                user.setLastName(resaultSet.getString("lastname"));
                Role role = new Role(resaultSet.getLong("role_id"));
                role.setName(resaultSet.getString("name"));
                user.setRole(role);
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
        return user;
    }
}
