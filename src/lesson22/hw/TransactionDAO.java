package lesson22.hw;

import lesson22.hw.exception.BadRequestException;
import lesson22.hw.exception.InternalServerException;
import lesson22.hw.exception.LimitExceeded;

import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {
    private static Transaction[] transactions = new Transaction[10];

    public static Transaction save(Transaction transaction) throws Exception {
        //сумма транзакций больше лимита +
        //сумма транзакций за день больше дневного лимита +
        //количество транзакций за день больше указанного лимита +
        //если город оплаты (совершения транзакции) не разрешен +
        //если не хватило места +

        validate(transaction);

        int i = 0;
        for (Transaction tr : transactions) {
            if (tr == null) {
                transactions[i] = transaction;
                break;
            }
            i++;
        }

        return transactions[i];
    }

    private static void validate(Transaction transaction) throws Exception {
        if (transaction.getAmount() > Utils.getLimitSimpleTransactionAmount())
            throw new LimitExceeded("Transaction limit exceed " + transaction.getId() + ". Can't be saved");

        long sum = 0;
        int count = 0;
        for (Transaction tr : getTransactionsPerDay(transaction.getDateCreated())) {
            sum += transaction.getAmount();
            count++;
        }
        if (sum > Utils.getLimitTransactionsPerDayAmount())
            throw new LimitExceeded("Transaction limit  amount exceed " + transaction.getId() + ". Can't be saved");

        if (count >= Utils.getLimitTransactionsPerDayCount())
            throw new LimitExceeded("Transaction limit per day count exceed " + transaction.getId() + ". Can't be saved");

        checkCity(Utils.getCities(), transaction);

        for (Transaction tr : transactions) {
            if (tr != null && tr.equals(transaction))
                throw new BadRequestException("Transaction with id: " + transaction.getId() + " already exist");
        }

        for (Transaction tr : transactions) {
            if (tr == null)
                return;
        }
        throw new InternalServerException("Transaction with id: " + transaction.getId() + " not added. Size over");
    }


    public static Transaction[] transactionList() {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null)
                count++;
        }

        Transaction[] transactionsPerCity = new Transaction[count];
        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null) {
                transactionsPerCity[index] = transaction;
                index++;
            }
        }
        return transactionsPerCity;
    }


    public static Transaction[] transactionList(String city) {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getCity().equals(city))
                count++;
        }

        Transaction[] transactionsPerCity = new Transaction[count];
        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getCity().equals(city)) {
                transactionsPerCity[index] = transaction;
                index++;
            }
        }
        return transactionsPerCity;
    }


    public static Transaction[] transactionList(int amount) {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getAmount() == amount)
                count++;
        }

        Transaction[] transactionsPerAmount = new Transaction[count];
        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getAmount() == amount) {
                transactionsPerAmount[index] = transaction;
                index++;
            }

        }
        return transactionsPerAmount;
    }


    private static Transaction[] getTransactionsPerDay(Date dateOfCurTransaction) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfCurTransaction);

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null) {
                calendar.setTime(transaction.getDateCreated());
                int trMonth = calendar.get(Calendar.MONTH);
                int trDay = calendar.get(Calendar.DAY_OF_MONTH);

                if (month == trMonth && day == trDay)
                    count++;
            }
        }

        Transaction[] result = new Transaction[count];
        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null) {
                calendar.setTime(transaction.getDateCreated());
                int trMonth = calendar.get(Calendar.MONTH);
                int trDay = calendar.get(Calendar.DAY_OF_MONTH);

                if (month == trMonth && day == trDay) {
                    result[index] = transaction;
                    index++;
                }
            }
        }
        return result;
    }

    private static void checkCity(String[] cities, Transaction transaction) throws BadRequestException {
        for (String city : cities) {
            if (city.equals(transaction.getCity())) {
                return;
            }
        }
        throw new BadRequestException("City is not allowed for transaction with id: " + transaction.getId());
    }
}
