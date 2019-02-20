package lesson15.hw1userRepository;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {

        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public User save(User user) {
        if (checkUsersForNull(user))
            return null;

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i] == findUserById(user.getId()))
                break;
            if (users[i] == null) {
                users[i] = user;
                return users[i];
            }
        }
        return null;
    }

    public User update(User user) {
        if (checkUsersForNull(user))
            return null;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i] == findUserById(user.getId())) {
                users[i] = user;
                break;
            }
        }
        return null;
    }

    public void delete(long id) {
        if (checkUsersForNull(new User(0, null, null)))
            return;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getId() == id) {
                users[i] = null;
                break;
            }
        }
    }

    public User findUser(User user) {
        if (checkUsersForNull(user))
            return null;
        for (User someUser : users)
            if (someUser != null) {
                return someUser.equals(user) && someUser.hashCode() == user.hashCode() ? someUser : null;
            }
        return null;
    }

    private boolean checkUsersForNull(User user) {
        return users == null || user == null;
    }

    private User findUserById(long id) {
        if (checkUsersForNull(new User(0, null, null)))
            return null;
        User userById = null;
        for (User user : users) {
            if (user != null && user.getId() == id) {
                userById = user;
                break;
            }
        }
        return userById;
    }
}
