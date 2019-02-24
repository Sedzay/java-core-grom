package lesson15.libraryManadger;

import java.util.Objects;

public abstract class User {
    private long id;
    private String name;
    private String password;
    private boolean authorization = false;

    public User(long id) {
        this.id = id;
    }

    /*public User(String name, String password) {
        this.name = name;
        this.password = password;
    }*/

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +  ", ";
    }

    public void login(String name, String password) {
        if (!authorization && this.name == name && this.password == password)
            authorization = true;
    }

    public void logOut() {
        authorization = false;
    }

    public boolean checkLogin() {
        if(!isAuthorization()) {
            System.out.println("you must log in");
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
