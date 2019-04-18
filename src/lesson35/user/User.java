package lesson35.user;

public class User {
    private long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;

    private boolean isLogin = false;

    public User(long id, String userName, String password, String country, UserType userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.userType = userType;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setLogin(boolean login) {
        this.isLogin = login;
    }

    @Override
    public String toString() {
        return id + ", "
                + userName + ", "
                + password + ", "
                + country + ", "
                + userType + ", ";
    }
}
