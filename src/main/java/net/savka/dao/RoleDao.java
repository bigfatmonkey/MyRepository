package net.savka.dao;

import net.savka.entities.Role;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: savka
 * Date: 12/2/13
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */

public interface RoleDao {
    public void create(Role role) throws SQLException;

    public void update(Role role);

    public void remove(Role role);

    public Role findByName(String name);
}
