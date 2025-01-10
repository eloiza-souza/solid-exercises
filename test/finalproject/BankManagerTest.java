package finalproject;


import finalproject.controller.BankManager;
import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.client.Client;
import finalproject.model.notification.EmailNotification;
import finalproject.model.notification.Notification;
import finalproject.service.AccountService;
import finalproject.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankManagerTest {

    private BankManager bankManager;
    private ClientService clientService;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService();
        accountService = new AccountService();
        bankManager = new BankManager(clientService, accountService);
    }

    @Test
    void testAddClient() {
        Notification notification = new EmailNotification();
        bankManager.addClient("875.392.840-72", "John Doe", "email@example.com", "123456789", notification);

        Optional<Client> client = clientService.findClientByCpf("875.392.840-72");
        assertTrue(client.isPresent());
        assertEquals("John Doe", client.get().getName());
    }

    @Test
    void testAddAccountToClient() {
        Notification notification = new EmailNotification();
        bankManager.addClient("875.392.840-72", "John Doe", "email@example.com", "123456789", notification);

        bankManager.addAccountToClient("875.392.840-72", AccountType.CHECKING);

        Optional<Client> client = clientService.findClientByCpf("875.392.840-72");
        assertTrue(client.isPresent());
        assertEquals(1, client.get().getAccounts().size());
        assertEquals(AccountType.CHECKING.toString().toUpperCase(), client.get().getAccounts().get(0).getAccountType().toUpperCase());
    }

    @Test
    void testGetBalance() {
        Notification notification = new EmailNotification();
        bankManager.addClient("875.392.840-72", "John Doe", "email@example.com", "123456789", notification);
        bankManager.addAccountToClient("875.392.840-72", AccountType.CHECKING);

        Optional<Client> client = clientService.findClientByCpf("875.392.840-72");
        assertTrue(client.isPresent());

        Account account = client.get().getAccounts().get(0);
        account.deposit(1000);

        bankManager.getBalance("875.392.840-72", account.getAccountNumber());
    }

    @Test
    void testDeposit() {
        Notification notification = new EmailNotification();
        bankManager.addClient("875.392.840-72", "John Doe", "email@example.com", "123456789", notification);
        bankManager.addAccountToClient("875.392.840-72", AccountType.CHECKING);

        Optional<Client> client = clientService.findClientByCpf("875.392.840-72");
        assertTrue(client.isPresent());

        Account account = client.get().getAccounts().get(0);
        bankManager.deposit("875.392.840-72", account.getAccountNumber(), 500);

        assertEquals(500, account.getBalance());
    }

    @Test
    void testWithdraw() {
        Notification notification = new EmailNotification();
        bankManager.addClient("875.392.840-72", "John Doe", "email@example.com", "123456789", notification);
        bankManager.addAccountToClient("875.392.840-72", AccountType.CHECKING);

        Optional<Client> client = clientService.findClientByCpf("875.392.840-72");
        assertTrue(client.isPresent());

        Account account = client.get().getAccounts().get(0);
        account.deposit(1000);

        bankManager.withdraw("875.392.840-72", account.getAccountNumber(), 500);

        assertEquals(500, account.getBalance());
    }

    @Test
    void testTransfer() {
        Notification notification1 = new EmailNotification();
        Notification notification2 = new EmailNotification();

        bankManager.addClient("875.392.840-72", "John Doe", "email1@example.com", "123456789", notification1);
        bankManager.addClient("267.717.340-99", "Jane Doe", "email2@example.com", "987654321", notification2);

        bankManager.addAccountToClient("875.392.840-72", AccountType.CHECKING);
        bankManager.addAccountToClient("267.717.340-99", AccountType.SAVING);

        Optional<Client> sourceClient = clientService.findClientByCpf("875.392.840-72");
        Optional<Client> targetClient = clientService.findClientByCpf("267.717.340-99");

        assertTrue(sourceClient.isPresent());
        assertTrue(targetClient.isPresent());

        Account sourceAccount = sourceClient.get().getAccounts().get(0);
        Account targetAccount = targetClient.get().getAccounts().get(0);

        sourceAccount.deposit(1000);

        bankManager.transfer("875.392.840-72", sourceAccount.getAccountNumber(), "267.717.340-99", targetAccount.getAccountNumber(), 500);

        assertEquals(500, sourceAccount.getBalance());
        assertEquals(500, targetAccount.getBalance());
    }

    @Test
    void testApplyRateInterest() {
        Notification notification = new EmailNotification();
        bankManager.addClient("267.717.340-99", "John Doe", "email@example.com", "123456789", notification);
        bankManager.addAccountToClient("267.717.340-99", AccountType.SAVING);

        Optional<Client> client = clientService.findClientByCpf("267.717.340-99");
        assertTrue(client.isPresent());

        Account account = client.get().getAccounts().get(0);
        account.deposit(1000);

        bankManager.applyRateInterest("267.717.340-99", account.getAccountNumber());

        // Supondo que a taxa de juros seja aplicada corretamente
        assertTrue(account.getBalance() > 1000);
    }
}
