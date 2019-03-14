package lesson20.task2;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception {
        Transaction transaction = new Transaction(10001, "Kiev",10,"someTransaction", TransactionType.INCOME,new Date());

        //тест сохранения
        Controller controller = new Controller();
        try {
            controller.save(transaction);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Arrays.toString(controller.transactionList()));

        //превышение разового лимита количества по транзакции
        Transaction transaction2 = new Transaction(10002, "Kiev",100,"someTransaction", TransactionType.INCOME,new Date());
        try {
            controller.save(transaction2);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(controller.transactionList()));

        //превышение лимита количества за день
        transaction2 = new Transaction(10002, "Kiev",40,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction3 = new Transaction(10003, "Kiev",40,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction4 = new Transaction(10004, "Kiev",40,"someTransaction", TransactionType.INCOME,new Date());
        try {
            controller.save(transaction2);
            controller.save(transaction3);
            controller.save(transaction4);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(controller.transactionList()));

        //транзакция с недопустимым городом
        Transaction transaction5 = new Transaction(10005, "Dnepr",40,"someTransaction", TransactionType.INCOME,new Date());
        Controller controller2 = new Controller();
        try {
            controller2.save(transaction5);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //сохранение транзакциис одинаковым ИД
        transaction5 = new Transaction(10005, "Odessa",5,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction6 = new Transaction(10005, "Odessa",5,"someTransaction", TransactionType.INCOME,new Date());
        try {
            controller2.save(transaction5);
            controller2.save(transaction6);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(controller2.transactionList()));

        //превышение количества транзакций за день
        transaction6 = new Transaction(10006, "Odessa",5,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction7 = new Transaction(10007, "Kiev",6,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction8 = new Transaction(10008, "Kiev",4,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction9 = new Transaction(10009, "Odessa",10,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction10 = new Transaction(10010, "Odessa",2,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction11 = new Transaction(10011, "Odessa",5,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction12 = new Transaction(10012, "Odessa",2,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction13 = new Transaction(10013, "Odessa",3,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction14 = new Transaction(10014, "Odessa",1,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction15 = new Transaction(10015, "Odessa",5,"someTransaction", TransactionType.INCOME,new Date());

        try {
            controller2.save(transaction6);
            controller2.save(transaction7);
            controller2.save(transaction8);
            controller2.save(transaction9);
            controller2.save(transaction10);
            controller2.save(transaction11);
            controller2.save(transaction12);
            controller2.save(transaction13);
            controller2.save(transaction14);
            controller2.save(transaction15);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(controller2.transactionList()));

        //отфильтровать транзакции по городу
        System.out.println(Arrays.toString(controller2.transactionList("Odessa")));

        //отфильтровать по сумме
        System.out.println(Arrays.toString(controller2.transactionList(5)));
    }
}
