package finalproject.model;

public class SavingAccount extends AccountBase {

    @Override
    public AccountType setType() {
        return AccountType.SAVING;
    }

    @Override
    public double interestRate() {
        return 0.01;
    }
}
