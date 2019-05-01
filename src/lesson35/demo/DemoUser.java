package lesson35.demo;

import lesson35.user.User;
import lesson35.user.UserController;
import lesson35.user.UserType;

public class DemoUser {
    public static void main(String[] args) throws Exception {

        //User user = new User(0, "Kolya", "pas4", "USA", UserType.USER);
        User user = new User(0, "Stepan", "pas4", "USA", UserType.USER);
        UserController userController = new UserController();

        //добавление юзера
//        User user2 = new User(0, "Ivan", "pas5", "EUROPA", UserType.USER);
//        userController.registerUser(user2);

        //тетс логина
//        userController.login("Stepan", "pas4");


    }
}
