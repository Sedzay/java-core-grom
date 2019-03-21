package lesson27.hw2;

import java.util.ArrayList;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        User user1 = new User(101, "Vasiya", "sesId");

        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(user1);

        UserRepository userRepository = new UserRepository(userArrayList);

        System.out.println(Arrays.deepToString(userRepository.getUsers().toArray()));

        User user2 = new User(102, "Vasiya2", "sesId");

        userRepository.save(user2);

        System.out.println(Arrays.deepToString(userRepository.getUsers().toArray()));

        //такой же ид
        User user3 = new User(102, "Grisha", "sesId2");
        System.out.println(userRepository.save(user3));
        System.out.println(Arrays.deepToString(userRepository.getUsers().toArray()));

        //обновить второго на третьего
        userRepository.update(user3);
        System.out.println(Arrays.deepToString(userRepository.getUsers().toArray()));

        //нечего удалять
        userRepository.delete(103);

        //удалить первый
        userRepository.delete(101);
        System.out.println(Arrays.deepToString(userRepository.getUsers().toArray()));
    }
}
