package finalproject;

import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.client.Client;
import finalproject.model.notification.EmailNotification;
import finalproject.model.notification.Notification;
import finalproject.model.notification.SmsNotification;
import finalproject.service.ClientService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    @Test
    void testAddClient() {
        ClientService clientService = new ClientService();
        Notification notification = new EmailNotification();

        clientService.addClient("265.061.930-93", "John Doe", "email@example.com", "123456789", notification);

        Optional<Client> client = clientService.findClientByCpf("265.061.930-93");
        assertTrue(client.isPresent());
        assertEquals("John Doe", client.get().getName());
        assertEquals("email@example.com", client.get().getEmail());
    }

    @Test
    void testAddAccountToClient() {
        ClientService clientService = new ClientService();
        Notification notification = new SmsNotification();

        clientService.addClient("265.061.930-93", "John Doe", "email@example.com", "123456789", notification);
        clientService.addAccountToClient("265.061.930-93", AccountType.CHECKING);

        Optional<Client> client = clientService.findClientByCpf("265.061.930-93");
        assertTrue(client.isPresent());
        assertEquals(1, client.get().getAccounts().size());
        assertEquals(AccountType.CHECKING.toString().toUpperCase(), client.get().getAccounts().get(0).getAccountType().toUpperCase());
    }

    @Test
    void testFindClientByCpf() {
        ClientService clientService = new ClientService();
        Notification notification = new SmsNotification();

        clientService.addClient("265.061.930-93", "John Doe", "email@example.com", "123456789", notification);

        Optional<Client> client = clientService.findClientByCpf("265.061.930-93");
        assertTrue(client.isPresent());
        assertEquals("John Doe", client.get().getName());
    }

    @Test
    void testFindAccountByNumber() {
        ClientService clientService = new ClientService();
        Notification notification = new SmsNotification();

        clientService.addClient("265.061.930-93", "John Doe", "email@example.com", "123456789", notification);
        clientService.addAccountToClient("265.061.930-93", AccountType.CHECKING);

        Optional<Client> client = clientService.findClientByCpf("265.061.930-93");
        assertTrue(client.isPresent());

        Optional<Account> account = clientService.findAccountByNumber(client.get(), client.get().getAccounts().get(0).getAccountNumber());
        assertTrue(account.isPresent());
        assertEquals(AccountType.CHECKING.toString().toUpperCase(), account.get().getAccountType().toUpperCase());
    }

    @Test
    void testAddClientWithInvalidCpf() {
        ClientService clientService = new ClientService();
        Notification notification = new SmsNotification();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> clientService.addClient("invalid_cpf", "John Doe", "email@example.com", "123456789", notification));

        assertEquals("CPF deve conter exatamente 11 dígitos.", exception.getMessage());
    }

    @Test
    void testAddClientWithInvalidEmail() {
        ClientService clientService = new ClientService();
        Notification notification = new SmsNotification();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> clientService.addClient("265.061.930-93", "John Doe", "invalid_email", "123456789", notification));

        assertEquals("O e-mail deve conter exatamente um '@'.", exception.getMessage());
    }
}