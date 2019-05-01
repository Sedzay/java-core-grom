package lesson35.user;

import lesson35.exceptions.BadRequestException;

import java.util.ArrayList;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    User registerUser(User user) throws Exception {

        checkFieldsUser(user);
        checkReiterationUser(user);
        userRepository.registerUser(user);

        return user;
    }

    private void checkReiterationUser(User user) throws Exception {
        userRepository.setPath(userRepository.getPath());
        for (User userFromDb : (ArrayList<User>) userRepository.mappingStringsToObjects(userRepository.readFile())) {
            if (userFromDb.getUserName().equals(user.getUserName()))
                throw new BadRequestException("User with name " + user.getUserName() + " already exist. Method " + Thread.currentThread().getStackTrace()[2].getMethodName());
        }
    }

    private void checkFieldsUser(User user) throws BadRequestException {
        if (user.getUserName() == null)
            throw new BadRequestException("Field Name must be filled");

        if (user.getPassword() == null)
            throw new BadRequestException("Field Password must be filled");

        if (user.getCountry() == null)
            throw new BadRequestException("Field Country must be filled");

        if (user.getUserType() == null)
            throw new BadRequestException("Field UserType must be filled");
    }
}
