package finalproject;


import finalproject.util.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    // Testes para cenários positivos
    @Test
    void validateEmail_ValidEmail_ShouldNotThrowException() {
        // Cenário positivo: e-mails válidos
        assertDoesNotThrow(() -> EmailValidator.validateEmail("user@example.com"));
        assertDoesNotThrow(() -> EmailValidator.validateEmail("user.name+tag+sorting@example.com"));
        assertDoesNotThrow(() -> EmailValidator.validateEmail("user_name@example.co.uk"));
        assertDoesNotThrow(() -> EmailValidator.validateEmail("user-name@example.org"));
    }

    // Testes para cenários negativos
    @Test
    void validateEmail_NullEmail_ShouldThrowException() {
        // Cenário negativo: e-mail nulo
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail(null));
        assertEquals("O e-mail não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    void validateEmail_EmptyEmail_ShouldThrowException() {
        // Cenário negativo: e-mail vazio
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail(""));
        assertEquals("O e-mail não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    void validateEmail_EmailWithoutAtSymbol_ShouldThrowException() {
        // Cenário negativo: e-mail sem '@'
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail("userexample.com"));
        assertEquals("O e-mail deve conter exatamente um '@'.", exception.getMessage());
    }

    @Test
    void validateEmail_EmailWithMultipleAtSymbols_ShouldThrowException() {
        // Cenário negativo: e-mail com múltiplos '@'
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail("user@@example.com"));
        assertEquals("O e-mail deve conter exatamente um '@'.", exception.getMessage());
    }

    @Test
    void validateEmail_EmailWithoutLocalPart_ShouldThrowException() {
        // Cenário negativo: e-mail sem parte local
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail("@example.com"));
        assertEquals("O e-mail deve conter uma parte local e um domínio.", exception.getMessage());
    }

    @Test
    void validateEmail_EmailWithoutDomainPart_ShouldThrowException() {
        // Cenário negativo: e-mail sem domínio
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail("user@"));
        assertEquals("O e-mail deve conter uma parte local e um domínio.", exception.getMessage());
    }

    @Test
    void validateEmail_DomainWithoutDot_ShouldThrowException() {
        // Cenário negativo: domínio sem ponto
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail("user@examplecom"));
        assertEquals("O domínio deve conter pelo menos um ponto e não pode começar ou terminar com ele.", exception.getMessage());
    }

    @Test
    void validateEmail_DomainStartingWithDot_ShouldThrowException() {
        // Cenário negativo: domínio começando com ponto
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail("user@.example.com"));
        assertEquals("O domínio deve conter pelo menos um ponto e não pode começar ou terminar com ele.", exception.getMessage());
    }

    @Test
    void validateEmail_DomainEndingWithDot_ShouldThrowException() {
        // Cenário negativo: domínio terminando com ponto
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail("user@example.com."));
        assertEquals("O domínio deve conter pelo menos um ponto e não pode começar ou terminar com ele.", exception.getMessage());
    }

    @Test
    void validateEmail_LocalPartWithInvalidCharacters_ShouldThrowException() {
        // Cenário negativo: parte local com caracteres inválidos
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail("user!@example.com"));
        assertEquals("A parte local do e-mail contém caracteres inválidos.", exception.getMessage());
    }

    @Test
    void validateEmail_DomainWithInvalidCharacters_ShouldThrowException() {
        // Cenário negativo: domínio com caracteres inválidos
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail("user@example!.com"));
        assertEquals("O domínio do e-mail contém caracteres inválidos.", exception.getMessage());
    }
}

