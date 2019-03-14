package lesson20.task2;

import lesson20.task2.exception.BadRequestException;
import lesson20.task2.exception.InternalServerException;
import lesson20.task2.exception.LimitExceeded;

import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {
    private Transaction[] transactions = new Transaction[10];
    private Utils utils = new Utils();

    public Transaction save(Transaction transaction) throws Exception {
        if (transaction == null)
            throw new BadRequestException("Transaction is null");
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

    private void validate(Transaction transaction) throws Exception {
        if (transaction.getAmount() > utils.getLimitSimpleTransactionAmount())
            throw new LimitExceeded("Transaction limit exceed " + transaction.getId() + ". Can't be saved");

        long sum = 0;
        int count = 0;
        for (Transaction tr : getTransactionsPerDay(transaction.getDateCreated())) {
            sum += transaction.getAmount();
            count++;
        }
        if (sum > utils.getLimitTransactionsPerDayAmount())
            throw new LimitExceeded("Transaction limit  amount exceed " + transaction.getId() + ". Can't be saved");

        if (count > utils.getLimitTransactionsPerDayCount())
            throw new LimitExceeded("Transaction limit per day count exceed " + transaction.getId() + ". Can't be saved");

        boolean checkCity = false;
        for (String city : utils.getCities()) {
            if (city.equals(transaction.getCity())) {
                checkCity = true;
                break;
            }
        }
        if (!checkCity)
            throw new BadRequestException("City is not allowed for transaction with id: " + transaction.getId());

        /*for (Transaction tr : transactions) {
            if (tr != null && tr.getId() == transaction.getId())
                throw new BadRequestException("Transaction with id: " + transaction.getId() + " already exist");
        }*/

        for (Transaction tr : transactions) {
            if (tr == null)
                return;
        }
        throw new InternalServerException("Transaction with id: " + transaction.getId() + " not added. Size over");
    }


    public Transaction[] transactionList() {
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


    public Transaction[] transactionList(String city) {
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


    public Transaction[] transactionList(int amount) {
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


    private Transaction[] getTransactionsPerDay(Date dateOfCurTransaction) {
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
}
