package lesson22.hw;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception {
        Transaction transaction = new Transaction(10001, "Kiev",10,"someTransaction", TransactionType.INCOME,new Date());

        //тест сохранения

//        try {
//            Transaction returnTransaction = Controller.save(transaction);
//            System.out.println(returnTransaction);
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }


        System.out.println(Arrays.toString(Controller.transactionList()));

        //превышение разового лимита количества по транзакции
        Transaction transaction2 = new Transaction(10002, "Kiev",100,"someTransaction", TransactionType.INCOME,new Date());
        try {
            Controller.save(transaction2);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(Controller.transactionList()));

        //превышение лимита количества за день
        transaction2 = new Transaction(10002, "Kiev",40,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction3 = new Transaction(10003, "Kiev",40,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction4 = new Transaction(10004, "Kiev",40,"someTransaction", TransactionType.INCOME,new Date());
        try {
 //           Controller.save(transaction2);
            //Controller.save(transaction3);
            //Controller.save(transaction4);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(Controller.transactionList()));

        //транзакция с недопустимым городом

        Transaction transaction5 = new Transaction(10005, "Dnepr",40,"someTransaction", TransactionType.INCOME,new Date());
        try {
            Controller.save(transaction5);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(Controller.transactionList()));

        //сохранение транзакциис одинаковым ИД
        transaction5 = new Transaction(10005, "Odessa",5,"someTransaction", TransactionType.INCOME,new Date());
        Transaction transaction6 = new Transaction(10005, "Odessa",5,"someTransaction", TransactionType.INCOME,new Date());
        try {
            Controller.save(transaction5);
            Controller.save(transaction6);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(Controller.transactionList()));

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
            Controller.save(transaction6);
            Controller.save(transaction7);
            Controller.save(transaction8);
            Controller.save(transaction9);
            Controller.save(transaction10);
            Controller.save(transaction11);
            Controller.save(transaction12);
            Controller.save(transaction13);
            Controller.save(transaction14);
            Controller.save(transaction15);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(Controller.transactionList()));

        //отфильтровать транзакции по городу
        System.out.println(Arrays.toString(Controller.transactionList("Odessa")));

        //отфильтровать по сумме
        System.out.println(Arrays.toString(Controller.transactionList(5)));
    }
}
