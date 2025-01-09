package finalproject.model.account;

public class CheckingAccount extends BaseAccount {

    @Override
    public AccountType defineType() {
        return AccountType.CHECKING;
    }

    @Override
    public double defineRateInterest() {
        return 0;
    }

}