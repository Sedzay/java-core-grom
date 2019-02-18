package lesson12;

public class UkrainianBankSystem implements BankSystem {

    @Override
    public void withdraw(User user, int amount) {
        //проверить можно ли снять - проверитьлимит, проверить достаточно ли денег
        //снять

        if (!checkWithdraw(user, amount))
            return;
        withdrawMoney(user, amount);
    }

    @Override
    public void fund(User user, int amount) {
        //проверить можно ли пополнить счет
        //пополнить
        if (!checkFund(user, amount))
            return;
        creditMoney(user, amount);
    }

    @Override
    public void transferMoney(User fromUser, User toUser, int amount) {
        //снимаем деньги с fromUser
        //пополняем toUser

        if (checkWithdraw(fromUser, amount) && checkFund(toUser, amount) && checkCurency(fromUser,toUser)) {
            withdrawMoney(fromUser, amount);
            creditMoney(toUser, amount);
        }
    }

    @Override
    public void paySalary(User user) {
        fund(user, user.getSalary());
    }

    private boolean checkWithdraw(User user, int amount) {
        return checkWithdrawLimits(user, amount, user.getBank().getLimitOfWithdrawal())
                && checkWithdrawLimits(user, amount, user.getBalance());
    }

    private boolean checkWithdrawLimits(User user, int amount, double limit) {
        if (amount + user.getBank().getCommission(amount) > limit) {
            printWithdrawalErrorMsg(amount, user);
            return false;
        }
        return true;
    }

    private void printWithdrawalErrorMsg(int amount, User user) {
        System.err.println("Can't withdraw money " + amount + " from user " + user.toString());
    }

    private boolean checkFund(User user, int amount) {
        if (amount > user.getBank().getLimitOfFunding()) {
            printFundErrorMsg(amount, user);
            return false;
        }
        return true;
    }

    private void printFundErrorMsg(int amount, User user) {
        System.err.println("Can't funding money" + amount + " from user " + user.toString());
    }

    private void withdrawMoney(User user, int amount) {
        user.setBalance(user.getBalance() - amount - amount * user.getBank().getCommission(amount));
    }

    private void creditMoney(User user, int amount) {
        user.setBalance(user.getBalance() + amount);
    }

    private boolean checkCurency(User user1, User user2) {
        if (user1.getBank().getCurrency() == user2.getBank().getCurrency()) {
            return true;
        }
        printErrorCheckCurrency(user1, user2);
        return false;
    }

    private void printErrorCheckCurrency(User user1, User user2) {
        System.err.println("Can't transferring money: user " + user1.getId() + " and user " + user2.getId() + " have different curency");
    }
}
