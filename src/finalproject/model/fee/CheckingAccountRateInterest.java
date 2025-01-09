package finalproject.model.fee;

public class CheckingAccountFeeCalculator implements FeeCalculator {
    @Override
    public double calculateFee(double amount) {
        return amount * 0.01;
    }
}

