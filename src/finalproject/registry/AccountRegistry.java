package finalproject.registry;

import finalproject.model.Account;
import finalproject.model.AccountType;

public interface AccountRegistry {
    Account createAccount(AccountType type);
}
