package lesson35.user;

public class UserController {

    private UserService userService = new UserService();

    public User registerUser(User user) throws Exception {
        return userService.registerUser(user);
    }

}
