package finalproject.model.account;

import finalproject.model.fee.RateInterest;
import finalproject.util.NumberAccountGenerator;

public abstract class BaseAccount implements Account {

    private AccountType type;
    private double balance;
    private final String accountNumber;
    private RateInterest rateInterest;

    public BaseAccount() {
        this.type = defineType();
        this.balance = 0;
        this.accountNumber = NumberAccountGenerator.createAccountNumber();
        this.rateInterest = defineRateInterest();
    }

    public abstract AccountType defineType();

    public abstract RateInterest defineRateInterest();

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
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    @Override
    public void transfer(BaseAccount targetAccount, double amount) {
        this.withdraw(amount);
        targetAccount.deposit(amount);
    }

    public void applyInterest() {
        double rateInterest = this.rateInterest.rateInterest();
        if (rateInterest > 0) {
            deposit(this.balance * rateInterest);
        }
    }
}