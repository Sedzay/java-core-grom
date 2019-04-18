package lesson35.user;

public class UserController {

    private UserService userService = new UserService();

    public User registerUser(User user) throws Exception{

        userService.registerUser(user);

        return null;
    }
}
