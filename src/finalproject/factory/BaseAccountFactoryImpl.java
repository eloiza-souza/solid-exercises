package finalproject.factory;

import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.account.CheckingAccount;
import finalproject.model.account.SavingAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BaseAccountFactoryImpl implements BaseAccountFactory {

    private static final Map<AccountType, Supplier<Account>> register = new HashMap<>();

    static {
        register(AccountType.SAVING, SavingAccount::new);
        register(AccountType.CHECKING, CheckingAccount::new);
    }

    public static void register(AccountType type, Supplier<Account> supplier) {
        register.put(type, supplier);
    }

    @Override
    public Account createAccount(AccountType accountType) {
        Supplier<Account> supplier = register.get(accountType);
        if (supplier != null) {
            return supplier.get();
        }
        throw new IllegalArgumentException("Tipo de conta inválido: " + accountType);
    }

}
