package lesson35.user;

import lesson35.AbstractRepository;
import lesson35.exceptions.InternalServerException;

public class UserRepository extends AbstractRepository {
    private final String path = "D:\\DataBaseFiles\\UserDb.txt";

    public UserRepository() {
        super.setPath(path);
    }

    //регистрация юзера

    User registerUser(User user) throws Exception {
        user.setId(addId(mappingStringsToObjects(readFile())));
        addLine(user);
        System.out.println("New user " + user.getUserName() + " added successfully!");
        return user;
    }

    @Override
    public User stringToObject(String[] attributes) throws InternalServerException {
        return new User(Long.parseLong(attributes[0]),
                attributes[1],
                attributes[2],
                attributes[3],
                UserType.valueOf(attributes[4]));

    }
}
