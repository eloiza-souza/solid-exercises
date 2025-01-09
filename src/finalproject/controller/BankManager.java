package finalproject.controller;

import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.client.Client;
import finalproject.service.ClientService;
import finalproject.util.CpfValidator;

import java.util.List;

public class BankController {
    List<ClientService> bank;

    public BankController(List<ClientService> bank) {
        this.bank = bank;
    }

    public void addClient(Client client) {
        bank.add(new ClientService(client));
    }

    public void addAccount(String cpf, Account account) {
        findClientByCpf(cpf).addAccount(account);
    }

    public void deposit(String cpf, String accountNumber, double amount) {
        findClientByCpf(cpf).findAccount()
    }

    private ClientService findClientByCpf(String cpf) {
        CpfValidator.validateCpf(cpf);
        return bank.stream()
                .filter(client -> client.getClientCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
    }

    private Account findAccountByType(Client client, AccountType type) {
        return bank.stream()
                .filter()
    }
}
