package lesson9;

import lesson9.homeWork.User;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public User findById(long id) {
        User userById = null;
        for (User us : users) {
            if (us != null && us.getId() == id) {
                userById = us;
                break;
            }
        }
        return userById;
    }

    public User save(User user) {

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i] == findById(user.getId())) {
                break;
            }
            if (users[i] == null) {
                users[i] = user;
                //System.out.println(users[i].getName());
                return users[i];
            }
        }
        return null;
    }

    public User update(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i] == findById(user.getId())) {
                users[i] = user;
                return users[i];
            }
        }
        return null;
    }

    public void delete(long id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i] == findById(id)) {
                users[i] = null;
            }
        }
    }
}
