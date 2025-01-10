package finalproject;


import finalproject.factory.BaseAccountFactoryImpl;
import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.account.BaseAccount;
import finalproject.model.account.CheckingAccount;
import finalproject.model.account.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseAccountFactoryImplTest {

    @Test
    void testCreateSavingAccount() {
        BaseAccountFactoryImpl factory = new BaseAccountFactoryImpl();
        Account account = factory.createAccount(AccountType.SAVING);

        assertNotNull(account);
        assertInstanceOf(SavingAccount.class, account, "A conta criada deve ser do tipo SavingAccount.");
    }

    @Test
    void testCreateCheckingAccount() {
        BaseAccountFactoryImpl factory = new BaseAccountFactoryImpl();
        Account account = factory.createAccount(AccountType.CHECKING);

        assertNotNull(account);
        assertInstanceOf(CheckingAccount.class, account, "A conta criada deve ser do tipo CheckingAccount.");
    }

    @Test
    void testCreateAccountWithInvalidType() {
        BaseAccountFactoryImpl factory = new BaseAccountFactoryImpl();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAccount(null); // Tipo inválido
        });

        assertEquals("Tipo de conta inválido: null", exception.getMessage());
    }

    @Test
    void testRegisterNewAccountType() {

        // Criar um novo tipo de conta fictício para teste
        class CustomAccount extends BaseAccount {

            @Override
            public AccountType defineType() {
                return AccountType.CHECKING;
            }

            @Override
            public double defineRateInterest() {
                return 0.5;
            }
        }

        AccountType customType = AccountType.valueOf("CHECKING");
        BaseAccountFactoryImpl.register(customType, CustomAccount::new);

        BaseAccountFactoryImpl factory = new BaseAccountFactoryImpl();
        Account account = factory.createAccount(customType);

        assertNotNull(account);
        assertInstanceOf(CustomAccount.class, account, "A conta criada deve ser do tipo CustomAccount.");
    }
}
