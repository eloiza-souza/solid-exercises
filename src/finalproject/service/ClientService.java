package finalproject.service;

import finalproject.factory.BaseAccountFactoryImpl;
import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.client.Client;
import finalproject.model.notification.Notification;
import finalproject.util.CpfValidator;
import finalproject.util.EmailValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private final List<Client> clients;

    public ClientService() {
        this.clients = new ArrayList<>();
    }

    public void addClient(String cpf, String name, String email, String cellPhoneNumber, Notification notification) {
        validateCpf(cpf);
        validateEmail(email);
        Client client = new Client(cpf, name, email, cellPhoneNumber, notification);
        clients.add(client);
        System.out.println("Cliente "+ client.getName() + " adicionado com sucesso!");
    }

    private void validateEmail(String email) {
        EmailValidator.validateEmail(email);
    }

    private void validateCpf(String cpf) {
        CpfValidator.validateCpf(cpf);
    }

    public void addAccountToClient(String cpf, AccountType accountType) {
        Client client = findClientByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        Account account = new BaseAccountFactoryImpl().createAccount(accountType);
        client.addAccount(account);
        System.out.println("Conta criada. Número: " + account.getAccountNumber());
    }

    public Optional<Client> findClientByCpf(String cpf) {
        return clients.stream()
                .filter(client -> client.getCpf().equals(cpf))
                .findFirst();
    }

    public Optional<Account> findAccountByNumber(Client client, String accountNumber) {
        return client.getAccounts().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

}

