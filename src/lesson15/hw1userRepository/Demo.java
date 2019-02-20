package lesson15.hw1userRepository;

import java.util.Arrays;

public class Demo {
    private static User[]users = null;

    public static void main(String[] args) {
        User user = new User(1001,"Jack","session1");
        User user1 = new User(1002,"Ann","session2");
        User user2 = new User(1003,"Sam","session3");
        User user3 = new User(1004,"Den","session4");

        //test save
        System.out.println("TEST SAVE METHOD************");
        System.out.println();
        //если список юзеров пустой
        UserRepository userRepository = new UserRepository(users);
        System.out.println("userRepository is null: ");
        System.out.println(userRepository.save(user));
        System.out.println("--------------------------------------");

        //если юзер null
        users = new User[10];
        userRepository = new UserRepository(users);
        System.out.println("user is null: ");
        System.out.println(userRepository.save(null));
        System.out.println("--------------------------------------");

        //в массиве есть пустое место, передаем юзера
        System.out.println("add two users: ");
        userRepository.save(user);
        userRepository.save(user1);
        System.out.println(Arrays.toString(userRepository.getUsers()));
        System.out.println("--------------------------------------");

        //Записать существующего юзера
        System.out.println("add existing user");
        System.out.println(userRepository.save(user));
        System.out.println(Arrays.toString(userRepository.getUsers()));
        System.out.println("--------------------------------------");

        //test update
        System.out.println("TEST UPDATE METHOD************");
        System.out.println();

        //передать юзера для изменения с таким же ид
        System.out.println("user is in array: ");
        System.out.println(userRepository.update(new User(1002,"Klava","session155")));
        System.out.println(Arrays.toString(userRepository.getUsers()));
        System.out.println("--------------------------------------");

        //передать несуществующего юзера
        System.out.println("user is not in array: ");
        System.out.println(userRepository.update(new User(9999,"Sweta","session200")));
        System.out.println(Arrays.toString(userRepository.getUsers()));
        System.out.println("--------------------------------------");

        //test delete
        System.out.println("TEST DELETE METHOD************");
        System.out.println();

        //передать ид не существующего юзера
        userRepository.delete(9999);
        System.out.println("id is not: ");
        System.out.println(Arrays.toString(userRepository.getUsers()));
        System.out.println("--------------------------------------");

        //передать ид существующего юзера
        userRepository.delete(1002);
        System.out.println("id is it: ");
        System.out.println(Arrays.toString(userRepository.getUsers()));
        System.out.println("--------------------------------------");

        //test findUser
        System.out.println("TEST findUser METHOD************");
        System.out.println();

        //найти юзера по всем параметра
        System.out.println("find user: ");
        System.out.println(userRepository.findUser(user));
        System.out.println("--------------------------------------");

        //найти другого юзера, но стакими же данными
        System.out.println("find other user: ");
        System.out.println(userRepository.findUser(user3));
        System.out.println(user.hashCode());
        System.out.println(user3.hashCode());
        System.out.println("--------------------------------------");

    }


}
