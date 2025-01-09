package finalproject.service;

import finalproject.model.account.Account;
import finalproject.model.notification.Notification;

public class AccountService {
    private final Account account;
    private final Notification notification;

    public AccountService(Account account, Notification notification) {
        this.account = account;
        this.notification = notification;
    }

    public String getAccountNumber() {
        return account.getAccountNumber();
    }

    public void deposit(double amount) {
        validateAmount(amount);
        account.deposit(amount);
        notification.sendNotification("Depósito efetuado no valor: " + amount);
    }

    public void withdraw(double amount) {
        validateAmount(amount);
        if (amount > account.getBalance()) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        account.withdraw(amount);
        notification.sendNotification("Saque realizado no valor: " + amount);
    }

    public void transfer(AccountService targetAccountService, double amount) {
        if (targetAccountService == null) {
            throw new IllegalArgumentException("Conta de destino inválida.");
        }
        validateAmount(amount);
        account.withdraw(amount);
        targetAccountService.account.deposit(amount);
        targetAccountService.notification.sendNotification("Transferência recebida da conta " + getAccountNumber() + " no valor de " + amount);
        notification.sendNotification("Transferência enviada para a conta " + targetAccountService.getAccountNumber() + " no valor de " + amount);
    }

    public void applyRateInterest() {
        account.applyInterest();
        notification.sendNotification("Rendimento aplicado com sucesso.");
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }

    public double getBalance() {
        return account.getBalance();
    }

}
