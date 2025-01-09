package finalproject.model.client;

import finalproject.model.account.Account;
import finalproject.model.notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String cpf;
    private String name;
    private String email;
    private String cellPhoneNumber;
    private Notification notification;
    private final List<Account> accounts;

    public Client(String cpf, String name, String email, String cellPhoneNumber, Notification notification) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.cellPhoneNumber = cellPhoneNumber;
        this.accounts = new ArrayList<>();
        this.notification = notification;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}