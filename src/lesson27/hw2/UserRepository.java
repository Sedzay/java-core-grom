package lesson27.hw2;

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
        User[] newUsers = new User[users.size()];
        users.toArray(newUsers);
        for (User us : users) {
            if (us != null && us.getId() == id) {
                userById = us;
                break;
            }
        }
        return userById;
    }

    public User save(User user) {
        if (user == null)
            return null;
        if(findBiId(user.getId()) !=null)
            return null;
        users.add(user);
        return user;
    }

    public User update(User user) {
        if (user == null)
            return null;
        int index;
        if(findBiId(user.getId()) == null) {
            return null;
        }else {
            index = users.indexOf(findBiId(user.getId()));
        }
        users.set(index, user);
        return user;
    }

    public void delete(long id) {
        users.remove(findBiId(id));
    }
}
