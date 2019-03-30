package lesson30.task1;

public class Demo {
    public static void main(String[] args) throws Exception {

        Bank euBank = new USBank(1333, "Sweden", Currency.EUR, 200, 1500, 4, 56565656);
        User user1 = new User(1001, "Denis", 12200, 40, "GME", 1500, euBank);

        UkrainianBankSystem bankSystem = new UkrainianBankSystem();

        //withdraw
        bankSystem.withdraw(user1, 150);
        Thread.sleep(1500);
        bankSystem.withdraw(user1, 10);

        System.out.println(bankSystem.getTransactions());

        //fund
        Thread.sleep(1500);
        bankSystem.fund(user1, 10);

        System.out.println(bankSystem.getTransactions());

        //transfer
        Thread.sleep(1500);
        User user2 = new User(1002, "Anna", 400, 150, "TPI", 15000, euBank);
        bankSystem.transferMoney(user1, user2, 5);

        System.out.println(bankSystem.getTransactions());

        //salary
        Thread.sleep(1500);
        bankSystem.paySalary(user1);

        System.out.println(bankSystem.getTransactions());


    }
}
