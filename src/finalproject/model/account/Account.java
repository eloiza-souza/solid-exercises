package finalproject.model.account;

public interface Account {
    String getAccountNumber();

    double getBalance();

    void deposit(double amount);

    void withdraw(double amount);

    void transfer(BaseAccount targetAccount, double amount);

    void applyInterest();
}
