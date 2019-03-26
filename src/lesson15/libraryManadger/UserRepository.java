package lesson15.libraryManadger;

import java.util.Arrays;

public class UserRepository {
    private User[] users = new User[10];

    public UserRepository() {

        users[0] = new User(1, "admin", "admin123", UserType.ADMIN, null, null, null, 0);

    }

    public User[] getUsers() {
        return users;
    }

    public void autorisation(UserType userType, String name, String password) {
        if (userType == UserType.VISITOR)
            return;
        int index = 0;
        logOut();
        for (User user : users) {

            if (user != null && userType == user.getUserType() && user.getName() == name && user.getPassword() == password) {
                users[index].setAuthorisation(true);
                System.out.println("Welcome " + name);
                return;
            }
            index++;
        }
        System.out.println("Incorrect login or password");
        return;
    }

    public void logOut() {

        for (User user : users) {
            if (user != null && user.isAuthorisation() == true) {
                user.setAuthorisation(false);
                return;
            }
        }
    }

    public User addLibrarian(String name, String password, String email, String address, String city, int contact) {
        if (checkTypeOfAutorisedUser(UserType.ADMIN)) {
            User user = new User(0, name, password, UserType.LIBRARIAN, email, address, city, contact);
            return addUser(user);
        }
        return null;
    }

    public void showLibrarians() {
        if (checkTypeOfAutorisedUser(UserType.ADMIN)) {
            for (User user : users) {
                if (user != null && user.getUserType() == UserType.LIBRARIAN)
                    System.out.println(user.toString());
            }
        }
    }

    public void deleteLibrarian(long id) {
        if (checkTypeOfAutorisedUser(UserType.ADMIN)) {
            int index = 0;
            for (User user : users) {
                if (user != null && user.getUserType() == UserType.LIBRARIAN && user.getId() == id) {
                    users[index] = null;
                    System.out.println("Record with id " + id + " deleted successfully!");
                    return;
                }
                index++;
            }
            System.out.println("Librarian with id " + id + " not found");
        }
    }

    public User addVisitor(String name, int contact) {
        if (checkTypeOfAutorisedUser(UserType.ADMIN)) {
            User user = new User(0, name, null, UserType.VISITOR, null, null, null, contact);
            return addUser(user);
        }
        return null;
    }

    public boolean checkTypeOfAutorisedUser(UserType userType) {
        for (User user : users) {
            if (user != null && user.getUserType() == userType && user.isAuthorisation() == true) {
                return true;
            }
        }
        System.out.println("Log in how " + userType);
        return false;
    }

    private User addUser(User user) {
        int index = 0;
        for (User user1 : users) {
            if (user1 != null && user1.equals(user)) {
                System.out.println("user " + user.getUserType() + " with name " + user.getName() + " already exist");
                return null;
            }
            index++;
        }
        index = 0;
        for (User user1 : users) {
            if (user1 == null) {
                System.out.println("user " + user.getUserType() + " with name " + user.getName() + " add successfully");
                user.setId(users[index - 1].getId() + 1);
                return users[index] = user;
            }
            index++;
        }
        return null;
    }

}
