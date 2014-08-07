package net.savka.dao;

import net.savka.entities.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: savka
 * Date: 12/2/13
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */

public interface UserDao {
    public void create(User user);

    public void update(User user);

    public void remove(User user);

    public List<User> findAll();

    public User findByLogin(String login);

    public User findByEmail(String email);
}
