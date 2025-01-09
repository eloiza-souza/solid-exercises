package finalproject.model.account;

import finalproject.model.fee.CheckingAccountRateInterest;
import finalproject.model.fee.RateInterest;

public class CheckingAccount extends BaseAccount {

    @Override
    public AccountType defineType() {
        return AccountType.CHECKING;
    }

    @Override
    public RateInterest defineRateInterest() {
        return new CheckingAccountRateInterest();
    }

}