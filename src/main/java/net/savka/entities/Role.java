package net.savka.entities;

/**
 * Created with IntelliJ IDEA.
 * User: savka
 * Date: 12/2/13
 * Time: 3:38 PM
 * To change this template use File | Settings | File Templates.
 */

public class Role {
    private final Long id;
    private String name;

    public Role(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
