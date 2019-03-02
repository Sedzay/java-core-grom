package lesson15.libraryManadger;

import java.util.Objects;

public class User {
    private long id;
    private String name;
    private String password;
    private UserType userType;
    private String email;
    private String address;
    private String city;
    private int contact;

    private boolean authorization = false;

    public User(long id, String name, String password, UserType userType, String email, String address, String city, int contact) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userType = userType;
        this.email = email;
        this.address = address;
        this.city = city;
        this.contact = contact;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getContact() {
        return contact;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", contact=" + contact +
                ", authorization=" + authorization +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name) &&
                userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userType);
    }
}
