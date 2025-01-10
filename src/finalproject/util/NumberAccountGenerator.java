package finalproject.util;

public class NumberAccountGenerator {
    private static int generateAccountNumber = 10000;

    public static String createAccountNumber() {
        return ++generateAccountNumber + "-" + generateCheckDigit();
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
}
