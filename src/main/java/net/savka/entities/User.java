package net.savka.entities;

/**
 * Created with IntelliJ IDEA.
 * User: savka
 * Date: 12/2/13
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */

public class User {
    private Role role;
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private java.sql.Date birthDate;

    public void setId(Long id) {
        this.id = id;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Long getId() {
        return id;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Role getRole() {
        return role;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setLogin(String s) {
        login = s;
    }

    public String getPassword() {
        return password;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setPassword(String s) {
        password = s;
    }

    public String getFirstName() {
        return firstName;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setFirstName(String s) {
        firstName = s;
    }

    public String getLastName() {
        return lastName;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setLastName(String s) {
        lastName = s;
    }

    public java.sql.Date getBirthDate() {
        return birthDate;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setBirthDate(java.sql.Date date) {
        birthDate = date;
    }

    public String getEmail() {
        return email;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setEmail(String s) {
        email = s;
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
