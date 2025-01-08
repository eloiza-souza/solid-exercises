package finalproject.model;

import finalproject.util.NumberAccountGenerator;

public abstract class AccountBase implements Account {

    private AccountType type;
    private double balance;
    private final String accountNumber;

    public AccountBase() {
        this.type = setType();
        this.balance = 0;
        this.accountNumber = NumberAccountGenerator.getAccountNumber();
    }

    public abstract AccountType setType();

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public void withdrawal(double amount) {
          this.balance -= amount;
    }

    @Override
    public void transfer(AccountBase targetAccount, double amount) {
        this.withdrawal(amount);
        targetAccount.deposit(amount);
    }

    public abstract double interestRate();

    public void  applyInterest(){
        deposit(this.balance * interestRate());
    }


}