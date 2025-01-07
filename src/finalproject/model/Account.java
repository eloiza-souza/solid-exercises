package finalproject.model;

public abstract class Account {

    private static int generateAccountNumber = 10000;

    private double balance;
    private static String accountNumber;

    public Account() {
        this.balance = 0;
        accountNumber = ++generateAccountNumber + "-" + generateCheckDigit();
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        checkPositiveValue(amount);
        this.balance += amount;
    }

    public void withdrawal(double amount) {
        checkPositiveValue(amount);
        checkSufficientBalance(amount);
        this.balance -= amount;
    }

    public void transfer(Account targetAccount, double amount) {
        checkPositiveValue(amount);
        checkSufficientBalance(amount);
        this.withdrawal(amount);
        targetAccount.deposit(amount);
    }

    private static int generateCheckDigit() {
        int accountNumber = generateAccountNumber;
        int sum = 0;
        int weight = 2;

        while (accountNumber > 0) {
            int digit = accountNumber % 10;
            sum += digit * weight;
            accountNumber /= 10;
            weight++;

            if (weight > 9) {
                weight = 2;
            }
        }

        int remainder = sum % 11;

        if (remainder == 0 || remainder == 1) {
            return 0;
        } else {
            return 11 - remainder;
        }
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