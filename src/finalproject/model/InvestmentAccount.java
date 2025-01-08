package finalproject.model;

public class InvestmentAccount extends AccountBase {

    @Override
    public AccountType setType() {
        return AccountType.INVESTMENT;
    }

    @Override
    public double interestRate() {
        return 0.03;
    }
}
