package finalproject.model.fee;

public class SavingsAccountFeeCalculator implements FeeCalculator {
    @Override
    public double calculateFee(double amount) {
        return 0.0; // Sem taxa
    }
}
