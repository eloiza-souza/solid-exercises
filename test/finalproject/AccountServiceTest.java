package finalproject;

import finalproject.model.account.Account;
import finalproject.model.account.CheckingAccount;
import finalproject.model.account.SavingAccount;
import finalproject.service.AccountService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void testGetBalance() {
        Account account = new CheckingAccount();
        account.deposit(100.0);
        AccountService accountService = new AccountService();
        double balance = accountService.getBalance(account);
        assertEquals(100.0, balance);
    }

    @Test
    void testDeposit() {
        Account account = new CheckingAccount();
        account.deposit(100.0);
        AccountService accountService = new AccountService();
        accountService.deposit(account, 50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testDepositWithNegativeAmount() {
        Account account = new CheckingAccount();
        account.deposit(100.0);
        AccountService accountService = new AccountService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> accountService.deposit(account, -50.0));
        assertEquals("O valor deve ser maior que zero.", exception.getMessage());
    }

    @Test
    void testWithdraw() {
        Account account = new CheckingAccount();
        account.deposit(100.0);
        AccountService accountService = new AccountService();
        accountService.withdraw(account, 50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void testWithdrawWithInsufficientBalance() {
        Account account = new CheckingAccount();
        account.deposit(100.0);
        AccountService accountService = new AccountService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(account, 150.0));
        assertEquals("Saldo insuficiente.", exception.getMessage());
    }

    @Test
    void testTransfer() {
        Account sourceAccount = new CheckingAccount();
        sourceAccount.deposit(200.0);
        Account targetAccount = new SavingAccount();
        targetAccount.deposit(100.0);
        AccountService accountService = new AccountService();
        accountService.transfer(sourceAccount, targetAccount, 50.0);
        assertEquals(150.0, sourceAccount.getBalance());
        assertEquals(150.0, targetAccount.getBalance());
    }

    @Test
    void testTransferWithInvalidSourceAccount() {
        Account targetAccount = new SavingAccount();
        targetAccount.deposit(100.0);
        AccountService accountService = new AccountService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> accountService.transfer(null, targetAccount, 50.0));
        assertEquals("Conta inválida.", exception.getMessage());
    }

    @Test
    void testTransferWithInvalidTargetAccount() {
        Account sourceAccount = new CheckingAccount();
        sourceAccount.deposit(200.0);
        AccountService accountService = new AccountService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> accountService.transfer(sourceAccount, null, 50.0));
        assertEquals("Conta inválida.", exception.getMessage());
    }

    @Test
    void testApplyRateInterest() {
        Account account = new SavingAccount();
        account.deposit(100.0);
        AccountService accountService = new AccountService();
        accountService.applyRateInterest(account);
        assertEquals(101.0, account.getBalance());
    }
}
