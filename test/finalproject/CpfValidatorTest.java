package finalproject;

import finalproject.util.CpfValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CpfValidatorTest {

    @Test
    void validateCpf_ValidCpf_ShouldNotThrowException() {
        // Cenário positivo: CPF válido
        String validCpf = "123.456.789-09"; // Exemplo fictício
        assertDoesNotThrow(() -> CpfValidator.validateCpf(validCpf));
    }

    @Test
    void validateCpf_InvalidCpfLength_ShouldThrowException() {
        // Cenário negativo: CPF com tamanho inválido
        String invalidCpf = "123.456";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CpfValidator.validateCpf(invalidCpf));
        assertEquals("CPF deve conter exatamente 11 dígitos.", exception.getMessage());
    }

    @Test
    void validateCpf_AllDigitsEqual_ShouldThrowException() {
        // Cenário negativo: CPF com todos os dígitos iguais
        String invalidCpf = "111.111.111-11";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CpfValidator.validateCpf(invalidCpf));
        assertEquals("CPF não pode conter todos os dígitos iguais.", exception.getMessage());
    }

    @Test
    void validateCpf_InvalidFirstCheckDigit_ShouldThrowException() {
        // Cenário negativo: Primeiro dígito verificador inválido
        String invalidCpf = "518.914.010-70";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CpfValidator.validateCpf(invalidCpf));
        assertEquals("Primeiro dígito verificador do CPF é inválido.", exception.getMessage());
    }

    @Test
    void validateCpf_InvalidSecondCheckDigit_ShouldThrowException() {
        // Cenário negativo: Segundo dígito verificador inválido
        String invalidCpf = "518.914.010-58";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CpfValidator.validateCpf(invalidCpf));
        assertEquals("Segundo dígito verificador do CPF é inválido.", exception.getMessage());
    }

    @Test
    void cleanCpfString_ValidCpfWithFormatting_ShouldReturnCleanedCpf() {
        // Cenário positivo: CPF formatado
        String formattedCpf = "123.456.789-09";
        String expectedCleanedCpf = "12345678909";
        assertEquals(expectedCleanedCpf, CpfValidator.cleanCpfString(formattedCpf));
    }

    @Test
    void cleanCpfString_NullCpf_ShouldThrowException() {
        // Cenário negativo: CPF nulo
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CpfValidator.cleanCpfString(null));
        assertEquals("CPF não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    void cleanCpfString_EmptyCpf_ShouldThrowException() {
        // Cenário negativo: CPF vazio
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CpfValidator.cleanCpfString(""));
        assertEquals("CPF não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    void formatCpf_ValidCpf_ShouldReturnFormattedCpf() {
        // Cenário positivo: CPF válido
        String validCpf = "88718023061"; // Exemplo fictício
        String expectedFormattedCpf = "887.180.230-61";
        assertEquals(expectedFormattedCpf, CpfValidator.formatCpf(validCpf));
    }

    @Test
    void formatCpf_InvalidCpf_ShouldThrowException() {
        // Cenário negativo: CPF inválido
        String invalidCpfFirstDigit = "887.180.230-51"; // Primeiro dígito verificador inválido
        String invalidCpfSecondDigit = "887.180.230-62"; // Segundo dígito verificador inválido

        // Verifica o primeiro dígito verificador
        Exception exceptionFirstDigit = assertThrows(IllegalArgumentException.class, () -> CpfValidator.formatCpf(invalidCpfFirstDigit));
        assertEquals("Primeiro dígito verificador do CPF é inválido.", exceptionFirstDigit.getMessage());

        // Verifica o segundo dígito verificador
        Exception exceptionSecondDigit = assertThrows(IllegalArgumentException.class, () -> CpfValidator.formatCpf(invalidCpfSecondDigit));
        assertEquals("Segundo dígito verificador do CPF é inválido.", exceptionSecondDigit.getMessage());
    }
}
