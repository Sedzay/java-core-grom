package gromecode.lesson22.repository;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {

        User user1 = new User(1001, "Daniil", "32434234sdsfuuuua");
        UserRepository.save(user1);

        System.out.println(Arrays.deepToString(UserRepository.getUsers()));

        User user2 = new User(1002, "Andrey", "ewrewrwer4343wef");
        UserRepository.save(user2);

        System.out.println(Arrays.deepToString(UserRepository.getUsers()));

        User user3 = new User(1001, "Test", "32434234sdsfuuuua");
        UserRepository.update(user3);

        System.out.println(Arrays.deepToString(UserRepository.getUsers()));

        System.out.println(Arrays.deepToString(UserRepository.getUsers()));

        UserRepository.save(user1);

        System.out.println(Arrays.deepToString(UserRepository.getUsers()));
        user1 = new User(1003,"Sacha", "bjhb");

        UserRepository.save(user1);
        System.out.println(Arrays.deepToString(UserRepository.getUsers()));

        UserRepository.delete(1001);
        System.out.println(Arrays.deepToString(UserRepository.getUsers()));
    }
}
