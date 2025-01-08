package finalproject.registry;

import finalproject.model.Account;
import finalproject.model.AccountType;
import finalproject.model.CheckingAccount;
import finalproject.model.InvestmentAccount;
import finalproject.model.SavingAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AccountRegistryImpl implements AccountRegistry {
    private final Map<AccountType, Supplier<Account>> accountSuppliers = new HashMap<>();

    public AccountRegistryImpl() {
        accountSuppliers.put(AccountType.SAVING, SavingAccount::new);
        accountSuppliers.put(AccountType.CHECKING, CheckingAccount::new);
        accountSuppliers.put(AccountType.INVESTMENT, InvestmentAccount::new);
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
