package finalproject.main;

import finalproject.controller.BankManager;
import finalproject.model.account.AccountType;
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
        novoBanco.addClient("45520203075", "Maria", "maria@email.com", "3298897777", smsNotification);

        novoBanco.addAccountToClient("73512293093", AccountType.SAVING);
        novoBanco.addAccountToClient("45520203075", AccountType.CHECKING);

        novoBanco.deposit("73512293093", "10001-3", 100.00);
        novoBanco.deposit("45520203075", "10002-1", 100.00);
        novoBanco.withdraw("73512293093", "10001-3", 10.00);
        novoBanco.transfer("73512293093", "10001-3", "45520203075", "10002-1", 20.00);
        novoBanco.getBalance("73512293093", "10001-3");
        novoBanco.applyRateInterest("73512293093", "10001-3");
        novoBanco.getBalance("73512293093", "10001-3");
    }
}