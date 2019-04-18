package gromecode.lesson13;

public class UserRepository {
    private User[] users = new User[10];

    /*public UserRepository(User[] users) {
        this.users = users;
    }*/

    public User[] getUsers() {
        return users;
    }

    public String[] getUserNames() {
        int count = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                count++;
            }
        }
        String[] names = new String[count];
        for (int j = 0, k = 0; j < users.length; j++) {
            if (users[j] != null) {
                names[k] = users[j].getName();
                k++;
            }
        }
        return names;
    }

    public long[] getUserIds() {
        int count = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                count++;
            }
        }
        long[] ids = new long[count];
        for (int j = 0, k = 0; j < users.length; j++) {
            if (users[j] != null) {
                ids[k] = users[j].getId();
                k++;
            }
        }
        return ids;
    }

    public String getUserNameById(long id) {
        String userName = null;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getId() == id) {
                userName = users[i].getName();
            }
        }
        return userName;
    }

    public User getUserByName(String name) {
        User userByName = null;
        for (User us : users) {
            if (us != null && us.getName() == name) {
                userByName = us;
                break;
            }
        }
        return userByName;
    }

    private User findById(long id) {
        User userById = null;
        for (User us : users) {
            if (us != null && us.getId() == id) {
                userById = us;
                break;
            }
        }
        return userById;
    }

    public User getUserBySessionId(String sessionId) {
        User userBySessionId = null;
        for (User us : users) {
            if (us != null && us.getSessionId() == sessionId) {
                userBySessionId = us;
                break;
            }
        }
        return userBySessionId;
    }

    public User save(User user) {

        for (int i = 0; i < users.length; i++) {
            if (user == null)
                return null;

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
        if (user == null)
            return null;
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
