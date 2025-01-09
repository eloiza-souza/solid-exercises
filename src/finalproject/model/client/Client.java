package finalproject.model.client;

import finalproject.model.account.Account;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String cpf;
    private String name;
    private String email;
    private String cellPhoneNumber;
    private List<Account> listAccount;

    public Client(String cpf, String name, String email, String cellPhoneNumber) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.cellPhoneNumber = cellPhoneNumber;
        this.listAccount = new ArrayList<>();
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
}
