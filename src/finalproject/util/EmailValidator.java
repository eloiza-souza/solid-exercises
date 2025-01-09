package finalproject.util;

public class EmailValidator {

    public static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("O e-mail não pode ser nulo ou vazio.");
        }

        email = email.trim();

        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');
        if (atIndex == -1 || atIndex != lastAtIndex) {
            throw new IllegalArgumentException("O e-mail deve conter exatamente um '@'.");
        }

        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex + 1);

        if (localPart.isEmpty() || domainPart.isEmpty()) {
            throw new IllegalArgumentException("O e-mail deve conter uma parte local e um domínio.");
        }

        int dotIndex = domainPart.indexOf('.');
        if (dotIndex == -1 || dotIndex == 0 || dotIndex == domainPart.length() - 1) {
            throw new IllegalArgumentException("O domínio deve conter pelo menos um ponto e não pode começar ou terminar com ele.");
        }

        if (!isValidLocalPart(localPart)) {
            throw new IllegalArgumentException("A parte local do e-mail contém caracteres inválidos.");
        }

        if (!isValidDomainPart(domainPart)) {
            throw new IllegalArgumentException("O domínio do e-mail contém caracteres inválidos.");
        }
    }

    private static boolean isValidLocalPart(String localPart) {
        for (char c : localPart.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '_' && c != '-' && c != '+') {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidDomainPart(String domainPart) {
        for (char c : domainPart.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '-') {
                return false;
            }
        }
        return true;
    }
}