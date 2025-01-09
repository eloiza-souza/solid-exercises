package finalproject.service;

import finalproject.model.account.Account;

public class AccountService {

    public double getBalance(Account account) {
        return account.getBalance();
    }

    public void deposit(Account account, double amount) {
        validatePositiveAmount(amount);
        account.deposit(amount);
    }

    public void withdraw(Account account, double amount) {
        validatePositiveAmount(amount);
        validateBalance(account, amount);
        account.withdraw(amount);
    }

    public void transfer(Account sourceAccount, Account targetAccount, double amount) {
        validateAccount(sourceAccount);
        validateAccount(targetAccount);
        validatePositiveAmount(amount);
        sourceAccount.withdraw(amount);
        targetAccount.deposit(amount);
    }

    public void applyRateInterest(Account account) {
        account.applyInterest();
    }

    private void validatePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }

    private void validateAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Conta inválida.");
        }
    }

    private void validateBalance(Account account, double amount) {
        if (amount > account.getBalance()) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }
}
