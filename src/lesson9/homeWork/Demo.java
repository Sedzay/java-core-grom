package lesson9.homeWork;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        User [] users = new User[10];
        UserRepository userRepository = new UserRepository(users);
        //userRepository.save(new User(3,"Anna","125ID"));
        //userRepository.save(new User(1,"Vasya","firstSess"));

        User user = new User(1001, "Ann", "1w21212");
        userRepository.save(user);

       // userRepository.save(user);

        System.out.println(Arrays.toString(userRepository.getUsers()));

        //сохранение юзера
        //сохранение того же юзера
        //когда нет места в массиве
        //когда сохраняем null

        int n = 15;
        while (n>0) {
            User user1 = new User(n,"Ann", "1w21212");

            System.out.println(userRepository.save(user1));
            n--;
        }

        userRepository.save(null);
        System.out.println(Arrays.toString(userRepository.getUsers()));

        //test update
        user = new User(1001,"Ann","testered");
        userRepository.update(user);
        System.out.println(Arrays.toString(userRepository.getUsers()));

        //обновление юзера
        //когда нет юзера
        //когда обновляем null

        user = new User(9999,"Ann","testered");
        System.out.println(userRepository.update(user));
        System.out.println(Arrays.toString(userRepository.getUsers()));

        System.out.println(userRepository.update(null));
        System.out.println(Arrays.toString(userRepository.getUsers()));

        //test getUserName & test getUserIds
        //если нет списка юзеров
        UserRepository userRepository1 = new UserRepository(null);
        System.out.println(Arrays.toString(userRepository1.getUserNames()));
        System.out.println(Arrays.toString(userRepository1.getUserIds()));
        //если юзера нет
        User[]users1 = new User[8];
        UserRepository userRepository2 = new UserRepository(users1);
        userRepository2.save(new User(1111,"Vasya","testered"));
        System.out.println(Arrays.toString(userRepository2.getUserNames()));
        System.out.println(Arrays.toString(userRepository2.getUserIds()));


        //test delete
        //если нет списка юзеров
        //если нет нужного ID
        userRepository1.delete(12);
    }
}
