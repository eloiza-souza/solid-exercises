package finalproject.model;

public interface Account {
    String getAccountNumber();

    double getBalance();

    void deposit(double amount);

    void withdrawal(double amount);

    void transfer(AccountBase targetAccount, double amount);
}
