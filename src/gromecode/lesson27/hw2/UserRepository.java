package gromecode.lesson27.hw2;

import java.util.ArrayList;

public class UserRepository {
    ArrayList<User> users;

    public UserRepository(ArrayList<User> users) {

        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User findBiId(long id){

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

        if(findBiId(user.getId()) !=null)
            return null;
        users.add(user);
        return user;
    }

    public User update(User user) {

        int index = users.indexOf(findBiId(user.getId()));

        if (index >= 0) {
            users.set(index, user);
            return user;
        }
        return null;
    }

    public void delete(long id) {
        users.remove(findBiId(id));
    }
}
