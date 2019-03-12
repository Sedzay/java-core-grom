package lesson20.task1;

import lesson20.task1.exception.BadRequestException;
import lesson20.task1.exception.InternalServerException;
import lesson20.task1.exception.UserNotFoundException;

public class UserRepository {
    private User[] users = new User[10];

    public User save(User user) throws Exception {
        if (users == null)
            throw new BadRequestException("Can't save null user");

        try {
            findById(user.getId());
            throw new BadRequestException("User with id: " + user.getId() + " already exist");
        } catch (UserNotFoundException e) {
            System.out.println("User with id: " + user.getId() + "not found. Will be saved");
        }

        int index = 0;
        for (User user1 : users) {
            if (user1 == null) {
                users[index] = user;
                return users[index];
            }
            index++;
        }
        throw new InternalServerException("Not enought space to save user with id: " + user.getId());

    }

    public User update(User user) throws Exception {
        if (users == null)
            throw new BadRequestException("Can't update null user");

        findById(user.getId());

        int index = 0;
        for (User user1 : users) {
            if (user1 != null && user1 == findById(user.getId())) {
                users[index] = user;
                return users[index];
            }
            index++;
        }

        throw new InternalServerException("Unexpected error");
    }

    public void delete(long id) throws Exception {
        findById(id);

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i] == findById(id)) {
                users[i] = null;
                break;
            }
        }
    }

    public User findById(long id) throws UserNotFoundException {
        for (User user : users) {
            if (user != null && user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException("user with id: " + id + "not found");
    }

    /*public UserRepository(User[] users) {
        this.users = users;
    }*/

    /*public User[] getUsers() {
        return users;
    }

    public String[] getUserNames() {
        int count = 0;
        if (users != null) {
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
        return null;
    }

    public long[] getUserIds() {
        int count = 0;
        if (users != null) {
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
        return null;
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
    }*/



    /*public User getUserBySessionId(String sessionId) throws UserNotFoundException {
        for (User user : users) {
            if (user != null && user.getSessionId() == sessionId) {
                return user;
            }
        }
        throw new UserNotFoundException("user with id: " + sessionId + "not found");
    }*/


}
