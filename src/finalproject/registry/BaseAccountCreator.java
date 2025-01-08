package finalproject.registry;

import finalproject.model.account.Account;
import finalproject.model.account.AccountType;

public interface BaseAccountRegistry {
    Account createAccount(AccountType type);
}
