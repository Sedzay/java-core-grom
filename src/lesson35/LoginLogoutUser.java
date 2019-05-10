package lesson35;

import lesson35.exceptions.BadRequestException;
import lesson35.user.User;
import lesson35.user.UserRepository;

import java.util.ArrayList;

public class LoginLogoutUser {

    private UserRepository userRepository = new UserRepository();
    private User loginUser = null;

    public User getLoginUser() {
        return loginUser;
    }

    public void login(String userName, String password) throws Exception {
        ArrayList<User> users = userRepository.getList();

        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                loginUser = user;
                System.out.println("Welcome, " + userName + "!");
                return;
            }
        }
        throw new BadRequestException("Incorrect login or password. Method: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void logout() {
        loginUser = null;
    }

}
