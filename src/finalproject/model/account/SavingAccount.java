package finalproject.model.account;

import finalproject.model.fee.RateInterest;
import finalproject.model.fee.SavingsAccountRateInterest;

public class SavingAccount extends BaseAccount {

    @Override
    public AccountType defineType() {
        return AccountType.SAVING;
    }

    @Override
    public RateInterest defineRateInterest() {
        return new SavingsAccountRateInterest();
    }

}
