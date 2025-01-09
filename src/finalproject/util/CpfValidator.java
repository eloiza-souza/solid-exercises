package finalproject.util;

public class CpfValidator {

    public static void validateCpf(String cpf) {
        cpf = cleanCpfString(cpf);
        validateLength(cpf);
        validateAllDigitsNotEqual(cpf);
        int[] cpfDigits = convertToDigitArray(cpf);
        validateFirstCheckDigit(cpfDigits);
        validateSecondCheckDigit(cpfDigits);
    }


    public static String cleanCpfString(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        return cpf.replaceAll("[^\\d]", "");
    }

    private static void validateLength(String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos.");
        }
    }

    private static void validateAllDigitsNotEqual(String cpf) {
        char firstDigit = cpf.charAt(0);
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != firstDigit) {
                return;
            }
        }
        throw new IllegalArgumentException("CPF não pode conter todos os dígitos iguais.");
    }

    private static int[] convertToDigitArray(String cpf) {
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }
        return digits;
    }

    private static void validateFirstCheckDigit(int[] cpfDigits) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += cpfDigits[i] * (10 - i);
        }
        int firstCheckDigit = (sum * 10) % 11;
        if (firstCheckDigit == 10) {
            firstCheckDigit = 0;
        }
        if (firstCheckDigit != cpfDigits[9]) {
            throw new IllegalArgumentException("Primeiro dígito verificador do CPF é inválido.");
        }
    }

    private static void validateSecondCheckDigit(int[] cpfDigits) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += cpfDigits[i] * (11 - i);
        }
        int secondCheckDigit = (sum * 10) % 11;
        if (secondCheckDigit == 10) {
            secondCheckDigit = 0;
        }
        if (secondCheckDigit != cpfDigits[10]) {
            throw new IllegalArgumentException("Segundo dígito verificador do CPF é inválido.");
        }
    }

    public static String formatCpf(String cpf) {
        validateCpf(cpf);
        return String.format("%s.%s.%s-%s", cpf.substring(0, 3),
                cpf.substring(3, 6),
                cpf.substring(6, 9),
                cpf.substring(9));
    }
}
