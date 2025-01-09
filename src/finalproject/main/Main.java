package finalproject.main;

import finalproject.controller.BankManager;
import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.account.CheckingAccount;
import finalproject.model.account.SavingAccount;
import finalproject.model.client.Client;
import finalproject.model.notification.EmailNotification;
import finalproject.model.notification.Notification;
import finalproject.model.notification.SmsNotification;
import finalproject.service.AccountService;
import finalproject.service.ClientService;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService();

        BankManager novoBanco = new BankManager(clientService, accountService);

        Notification emailNotification = new EmailNotification();
        Notification smsNotification = new SmsNotification();

        novoBanco.addClient("73512293093", "Luiz Carlos", "luizcarlos@email.com", "3299997777", emailNotification);
        novoBanco.addClient("455.202.030-75", "Maria", "maria@email.com", "3298897777", smsNotification);

        novoBanco.addAccountToClient("73512293093", AccountType.SAVING);



        //fazer deposito
        novoBanco.deposit("73512293093", "10001-3", 100.00);

    }
}