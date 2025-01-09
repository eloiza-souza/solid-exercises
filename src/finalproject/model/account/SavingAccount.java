package finalproject.model.account;

public class SavingAccount extends BaseAccount {

    @Override
    public AccountType defineType() {
        return AccountType.SAVING;
    }

    @Override
    public double defineRateInterest() {
        return 0.01;
    }

}
