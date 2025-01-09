package finalproject.main;


import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.notification.Email;
import finalproject.model.notification.Notification;
import finalproject.creation.BaseAccountCreatorImpl;

public class Main {
    public static void main(String[] args) {

        Notification emailNotification = new Email();
        BaseAccountCreatorImpl checkingEmail = new BaseAccountCreatorImpl();
        Account account1 = checkingEmail.createAccount(AccountType.CHECKING);
        account1.deposit(100);

    }
}