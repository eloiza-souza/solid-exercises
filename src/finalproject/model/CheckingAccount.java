package finalproject.model;

public class CheckingAccount extends AccountBase {

     @Override
    public AccountType setType() {
        return AccountType.CHECKING;
    }

    @Override
    public double interestRate() {
        return 0;
    }
}
