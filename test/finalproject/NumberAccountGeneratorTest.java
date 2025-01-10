package finalproject;

import finalproject.util.NumberAccountGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberAccountGeneratorTest {

    @BeforeEach
    void resetAccountNumber() {
        // Reseta o número da conta para 10000 antes de cada teste
        try {
            var field = NumberAccountGenerator.class.getDeclaredField("generateAccountNumber");
            field.setAccessible(true);
            field.setInt(null, 10000);
        } catch (Exception e) {
            fail("Não foi possível resetar o número da conta.");
        }
    }

    @Test
    void createAccountNumber_ShouldGenerateValidAccountNumber() {
        // Cenário positivo: Verifica se o número da conta é gerado corretamente
        String accountNumber1 = NumberAccountGenerator.createAccountNumber();
        String accountNumber2 = NumberAccountGenerator.createAccountNumber();

        assertNotNull(accountNumber1, "O número da conta não deve ser nulo.");
        assertNotNull(accountNumber2, "O número da conta não deve ser nulo.");
        assertNotEquals(accountNumber1, accountNumber2, "Os números de conta gerados devem ser únicos.");

        // Verifica o formato do número da conta
        assertTrue(accountNumber1.matches("\\d{5}-\\d"), "O número da conta deve estar no formato 'XXXXX-X'.");
        assertTrue(accountNumber2.matches("\\d{5}-\\d"), "O número da conta deve estar no formato 'XXXXX-X'.");
    }

    @Test
    void createAccountNumber_ShouldIncrementAccountNumber() {
        // Cenário positivo: Verifica se o número da conta é incrementado corretamente
        String accountNumber1 = NumberAccountGenerator.createAccountNumber();
        String accountNumber2 = NumberAccountGenerator.createAccountNumber();

        String[] parts1 = accountNumber1.split("-");
        String[] parts2 = accountNumber2.split("-");

        int baseNumber1 = Integer.parseInt(parts1[0]);
        int baseNumber2 = Integer.parseInt(parts2[0]);

        assertEquals(baseNumber1 + 1, baseNumber2, "O número da conta deve ser incrementado em 1.");
    }

    @Test
    void createAccountNumber_ShouldGenerateValidCheckDigit() {
        // Cenário positivo: Verifica se o dígito verificador é gerado corretamente
        String accountNumber = NumberAccountGenerator.createAccountNumber();
        String[] parts = accountNumber.split("-");

        int baseNumber = Integer.parseInt(parts[0]);
        int checkDigit = Integer.parseInt(parts[1]);

        // Recalcula o dígito verificador para verificar a validade
        int recalculatedCheckDigit = calculateCheckDigit(baseNumber);

        assertEquals(recalculatedCheckDigit, checkDigit, "O dígito verificador gerado deve ser válido.");
    }

    // Método auxiliar para recalcular o dígito verificador
    private int calculateCheckDigit(int accountNumber) {
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
