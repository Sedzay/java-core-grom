package gromecode.lesson25.hw;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws Exception {
        GeneralDAO<Order> generalDAO = new GeneralDAO<>();
        Order order = new Order(1001);

        try {
            generalDAO.save(order);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Arrays.deepToString(generalDAO.getAll()));

        Order order1 = new Order(1001);

        try {
            generalDAO.save(order1);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(generalDAO.getAll()));

        GeneralDAO<User> userGeneralDAO = new GeneralDAO<>();
        User user1 = new User(101, "name1");
        User user2 = new User(102, "name2");
        User user3 = new User(103, "name3");
        User user4 = new User(104, "name4");
        User user5 = new User(105, "name5");
        User user6 = new User(106, "name6");
        User user7 = new User(107, "name7");
        User user8 = new User(108, "name8");
        User user9 = new User(109, "name9");
        User user10 = new User(110, "name10");
        User user11 = new User(111, "name11");

        try {
            userGeneralDAO.save(user1);
            userGeneralDAO.save(user2);
            userGeneralDAO.save(user3);
            userGeneralDAO.save(user4);
            userGeneralDAO.save(user5);
            userGeneralDAO.save(user6);
            userGeneralDAO.save(user7);
            userGeneralDAO.save(user8);
            userGeneralDAO.save(user9);
            userGeneralDAO.save(user10);
            userGeneralDAO.save(user11);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(userGeneralDAO.getAll()));

        GeneralDAO<Transaction> transactionGeneralDAO = new GeneralDAO<>();
        Transaction transaction = new Transaction(100001, 100);
        Transaction transaction2 = new Transaction(100002, 200);
        Transaction transaction3 = new Transaction(100001, 300);

        try {
            transactionGeneralDAO.save(transaction);
            transactionGeneralDAO.save(transaction2);
            transactionGeneralDAO.save(transaction3);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(transactionGeneralDAO.getAll()));
    }
}
