package net.savka.dao.impl;

import net.savka.entities.Role;
import net.savka.entities.User;

import java.sql.SQLException;

//Заполняет базу данных готовыми пользователями. (Можно не использовать.)
public class main {
    public static void main(String[] args) throws SQLException {
        Role role = new Role((long) 1);
        role.setName("user");
        Role role2 = new Role((long) 2);
        role2.setName("admin");

        User user = new User();
        user.setLogin("Mike");
        user.setPassword("123");
        user.setRole(role);
        System.out.print(user.toString());

        User user2 = new User();
        user2.setLogin("Sara");
        user2.setPassword("123");
        user2.setRole(role2);
        System.out.print(user2.toString());

        User user3 = new User();
        user3.setLogin("Jon");
        user3.setPassword("123");
        user3.setRole(role2);
        System.out.print(user3.toString());

        JdbcRoleDao jdbscRoledao = new JdbcRoleDao();
        jdbscRoledao.create(role);
        jdbscRoledao.create(role2);

        JdbcUserDao jdbscUserdao = new JdbcUserDao();
        jdbscUserdao.create(user);
        jdbscUserdao.create(user2);
        jdbscUserdao.create(user3);


//        JdbcRoleDao jdbscRoledao = new JdbcRoleDao();
//        jdbscRoledao.create(user.getRole());
//          List<User> userList = new ArrayList<>();
//         userList=jdbscUserdao.findAll();
//        System.out.print(userList.toString());
//        User user3 = jdbscUserdao.findByLogin("notmylogin");
////         System.out.print("Юзер по логину"+ user3.toString());
//
//        Role role2 = new Role(Long.valueOf(4));
//        role2.setName("anon");
//
//        Role role3 = new Role(Long.valueOf(5));
//        role3.setName("unknow");
//
//
//        jdbscRoledao.create(role2);
//        jdbscRoledao.create(role3);
////        System.out.print("Роль по имени "+jdbscRoledao.findByName("unknow").toString());
//        jdbscRoledao.remove(role3);
//        //jdbscUserdao.remove(user);
//        // jdbscUserdao.remove(user4);
//


    }
}
