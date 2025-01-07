package finalproject.model;

import finalproject.util.NumberAccountGenerator;

public abstract class Account {

    private double balance;
    private final String accountNumber;

    public Account() {
        this.balance = 0;
        this.accountNumber = NumberAccountGenerator.getAccountNumber();
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdrawal(double amount) {
          this.balance -= amount;
    }

    public void transfer(Account targetAccount, double amount) {
        this.withdrawal(amount);
        targetAccount.deposit(amount);
    }
    public abstract double interestRate();

    public void  applyInterest(){
        deposit(this.balance * interestRate());
    }


    private void checkPositiveValue(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor da operação deve ser maior que zero.");
        }
    }

    private void checkSufficientBalance(double amount) {
        if (amount > getBalance()) {
            throw new IllegalStateException("Saldo insuficiente.");
        }
    }
}