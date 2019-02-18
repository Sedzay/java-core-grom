package lesson12;

public class Main {
    public static void main(String[] args) {

        Bank usBank = new USBank(1222,"USA", Currency.USD,100,1400,4,444343434);
        Bank euBank = new EUBank(1333,"Sweden", Currency.EUR,200,1500,4,56565656);
        Bank chBank = new ChinaBank(1444,"Honkong", Currency.USD,1000,300,3,10000000);

        User user1 = new User(1001,"Denis", 12200,40,"GME", 1500, usBank);
        User user2 = new User(1002,"Anna", 400,150,"TPI", 15000, usBank);
        User user3 = new User(1003,"Nikolay", 100000,63,"Intel", 50000, euBank);
        User user4 = new User(1004,"Olga", 1000000,12,"ATB", 3500, euBank);
        User user5 = new User(1005,"John", 122,48,"CrossChannel", 5000, chBank);
        User user6 = new User(1006,"Kris", 10,1,"ORRO", 100, chBank);

        BankSystem bankSystem = new UkrainianBankSystem();

        /*bankSystem.withdraw(user1,450);
        bankSystem.fund(user1,20);
        bankSystem.transferMoney(user1,user2,200);
        bankSystem.paySalary(user1);*/
        //System.out.println(user1.getBalance() + " " + user1.toString());


        /*bankSystem.withdraw(user2,150);
        bankSystem.fund(user2,200);
        bankSystem.transferMoney(user2,user3,200);
        bankSystem.paySalary(user2);
        System.out.println(user2.getBalance() + " " + user2.toString());*/

        //bankSystem.withdraw(user3,1500);
        //bankSystem.fund(user3,25000);
        //bankSystem.transferMoney(user3,user1,1000);
        bankSystem.paySalary(user3);
        System.out.println(user3.getBalance() + " " + user3.toString());

        /*bankSystem.withdraw(user4,300);
        bankSystem.fund(user4,300);
        bankSystem.transferMoney(user4,user5,300);
        bankSystem.paySalary(user4);*/
        //System.out.println(user4.getBalance() + " " + user4.toString());

        /*bankSystem.withdraw(user5,12);
        bankSystem.fund(user5,120);
        bankSystem.transferMoney(user5,user6,500);
        bankSystem.paySalary(user5);
        System.out.println(user5.getBalance() + " " + user5.toString());*/

        /*bankSystem.withdraw(user6,20);
        bankSystem.fund(user6,100);
        bankSystem.transferMoney(user6,user1,200);
        bankSystem.paySalary(user6);
        System.out.println(user6.getBalance() + " " + user6.toString());*/

    }
}
