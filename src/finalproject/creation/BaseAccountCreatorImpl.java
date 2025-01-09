package finalproject.creation;

import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.account.CheckingAccount;
import finalproject.model.account.SavingAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BaseAccountCreatorImpl implements BaseAccountCreator {
    private final Map<AccountType, Supplier<Account>> accountSuppliers = new HashMap<>();

    public BaseAccountCreatorImpl() {
        accountSuppliers.put(AccountType.SAVING, SavingAccount::new);
        accountSuppliers.put(AccountType.CHECKING, CheckingAccount::new);
    }

    @Override
    public Account createAccount(AccountType accountType) {
        Supplier<Account> supplier = accountSuppliers.get(accountType);
        if (supplier != null) {
            return supplier.get();
        }
        throw new IllegalArgumentException("Tipo de conta inválido: " + accountType);
    }
}
