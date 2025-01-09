package finalproject.controller;

import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.client.Client;
import finalproject.model.notification.Notification;
import finalproject.service.AccountService;
import finalproject.service.ClientService;

import java.util.Optional;

public class BankManager {
    ClientService bankManager;
    AccountService accountService;

    public BankManager(ClientService clients, AccountService accountService) {
        this.bankManager = clients;
        this.accountService = accountService;
    }

    public void addClient(String cpf, String name, String email, String cellPhoneNumber, Notification notification) {
        bankManager.addClient(cpf, name, email, cellPhoneNumber, notification);
    }

    public void addAccountToClient(String cpf, AccountType type) {
        bankManager.addAccountToClient(cpf, type);
    }

    public void getBalance(String cpf, String accountNumber ){
        Optional<Account> account = findAccountClient(cpf, accountNumber);
        if (account.isPresent()) {
            double balance = accountService.getBalance(account.get());
            sendNotificationToClient(cpf,"Seu saldo em conta é de R$ " + balance);
        }
        else
            notFoundAccount(accountNumber);
    }

    public void deposit(String cpf, String accountNumber, double amount) {
        Optional<Account> account = findAccountClient(cpf, accountNumber);
        if (account.isPresent()) {
            accountService.deposit(account.get(), amount);
            sendNotificationToClient(cpf, "Depósito realizado. Valor R$ " + amount);
        } else
            notFoundAccount(accountNumber);
    }

    public void withdraw(String cpf, String accountNumber, double amount) {
        Optional<Account> account = findAccountClient(cpf, accountNumber);
        if (account.isPresent()) {
            accountService.withdraw(account.get(), amount);
            sendNotificationToClient(cpf, "Saque realizado. Valor R$ " + amount);
        }
        else
            notFoundAccount(accountNumber);
    }

    public void transfer(String sourceCpf, String sourceAccountNumber, String targetCpf, String targetAccountNumber, double amount) {
        Optional<Account> sourceAccount = findAccountClient(sourceCpf, sourceAccountNumber);
        Optional<Account> targetAccount = findAccountClient(targetCpf, targetAccountNumber);
        if (sourceAccount.isPresent() && targetAccount.isPresent()) {
            accountService.transfer(sourceAccount.get(), targetAccount.get(), amount);
            sendNotificationToClient(sourceCpf, "Transferência realizada para o destino: " + targetAccount.get().getAccountNumber()+ ". Valor R$ " + amount);
            sendNotificationToClient(targetCpf, "Transferência recebida da conta " + sourceAccount.get().getAccountNumber() + ". Valor R$ " + amount);
        }
        else
            throw new IllegalArgumentException("Verifique o número das contas");
    }

    public void applyRateInterest(String cpf, String accountNumber) {
        Optional<Account> account = findAccountClient(cpf, accountNumber);
        if (account.isPresent()) {
            accountService.applyRateInterest(account.get());
            sendNotificationToClient(cpf,"Sua conta recebeu rendimentos");
        }
        else
            notFoundAccount(accountNumber);
    }

    private Optional<Client> findClient(String cpf) {
        return bankManager.findClientByCpf(cpf);
    }

    private Optional<Account> findAccountClient(String cpf, String accountNumber) {
        Optional<Client> client = findClient(cpf);
        if (client.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado com CPF: " + cpf);
        }
        return bankManager.findAccountByNumber(client.get(), accountNumber);
    }

    private void notFoundAccount(String accountNumber) {
        throw new IllegalArgumentException("Conta número " + accountNumber + "não encontrada.");
    }

    private void sendNotificationToClient(String cpf, String message) {
        Optional<Client> client = findClient(cpf);
        if (client.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado com CPF: " + cpf);
        }
        client.get().getNotification().sendNotification(message);
    }
}
