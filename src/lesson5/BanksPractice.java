package lesson5;

import java.util.Arrays;

public class BanksPractice {
    public static void main(String[] args) {
        String[] names = {"Jack", "Ann", "Denis", "Andrey", "Nikolay", "Irina", "John"};
        int[] balances = {100, 500, 8432, -99, 12000, -54, 0};

        System.out.println(Arrays.toString(findClientsByBalance(names, balances, -100)));
        System.out.println(Arrays.toString(findClientsWithNegativeBalance(names, balances)));

        depositMoney(names, balances, "Ann", 2000);
        System.out.println(Arrays.toString(balances));
        System.out.println(withdraw(names,balances,"Jack", 20));
        System.out.println(withdraw(names,balances,"Irina", 20));
    }

    static String[] findClientsByBalance(String[] cliences, int[] balances, int n) {
        //1.находим кол-во клиентов
        //2.создаем массив нужной длинны
        //3.записываем данные в массив

        //1
        int count = 0;
        for (int balance : balances) {
            if (balance >= n)
                count++;
        }
        //2
        String[] results = new String[count];
        //3
        int index = 0;
        int resIndex = 0;
        for (int balance : balances) {
            if (balance >= n) {
                results[resIndex] = cliences[index];
                resIndex++;
            }
            index++;
        }
        return results;

    }

    static String[] findClientsWithNegativeBalance(String[] cliences, int[] balances) {
        //1.находим кол-во клиентов
        //2.создаем массив нужной длинны
        //3.записываем данные в массив

        //1
        int count = 0;
        for (int balance : balances) {
            if (balance < 0)
                count++;
        }
        //2
        String[] results = new String[count];
        //3
        int index = 0;
        int resIndex = 0;
        for (int balance : balances) {
            if (balance < 0) {
                results[resIndex] = cliences[index];
                resIndex++;
            }
            index++;
        }
        return results;
    }

    static void depositMoney(String[] clients, int[] balances, String client, int money) {
        //int clientIndex = findClientIndexByName(clients, client);
        //int moneyToDeposit = calculeteDepositAmountAfterCommission(money);
        balances[findClientIndexByName(clients, client)] += calculeteDepositAmountAfterCommission(money);
    }

    static int findClientIndexByName(String[] clients, String client) {
        int clientIndex = 0;
        for (String cl : clients) {
            if (cl == client) {
                break;
            }
            clientIndex++;
        }
        return clientIndex;
    }

    static int calculeteDepositAmountAfterCommission(int money) {
        return money <= 100 ? (int) (money - money * 0.02) : (int) (money - money * 0.01);
    }

    static int withdraw(String[] cliences, int[] balances, String client, int amount) {
        int clientIndex = 0;
        for (String el : cliences) {
            if (el == client) {
                break;
            }
            clientIndex++;
        }
        return (balances[clientIndex] - amount) >= 0 ? balances[clientIndex] - amount : -1;
    }
}
