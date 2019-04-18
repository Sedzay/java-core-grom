    package gromecode.lesson22.repository;

public class UserRepository {
    private static User[] users = new User[10];

    public static User[] getUsers() {
        return users;
    }

    public static User findById(long id) {
        User userById = null;
        for (User us : users) {
            if (us != null && us.getId() == id) {
                userById = us;
                break;
            }
        }
        return userById;
    }

    public static User save(User user) {
        if (user == null)
            return null;

        if(findById(user.getId()) !=null)
            return null;

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

    public static User update(User user) {
        if (user == null)
            return null;

        if(findById(user.getId()) ==null)
            return null;

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i] == findById(user.getId())) {
                users[i] = user;
                return users[i];
            }
        }
        return null;
    }

    public static void delete(long id) {

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i] == findById(id)) {
                users[i] = null;
            }
        }
    }
}
