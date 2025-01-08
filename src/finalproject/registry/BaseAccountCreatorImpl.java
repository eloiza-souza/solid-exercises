package finalproject.registry;

import finalproject.model.account.Account;
import finalproject.model.account.AccountType;
import finalproject.model.account.CheckingAccount;
import finalproject.model.account.SavingAccount;
import finalproject.model.fee.FeeCalculator;
import finalproject.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BaseAccountRegistryImpl implements BaseAccountRegistry {
    private final Map<AccountType, Supplier<Account>> accountSuppliers = new HashMap<>();

    public BaseAccountRegistryImpl(FeeCalculator feeCalculator, Notification notification) {
        accountSuppliers.put(AccountType.SAVING, SavingAccount::new(feeCalculator,notification));
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
