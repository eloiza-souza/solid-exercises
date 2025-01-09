package finalproject.creation;

import finalproject.model.account.Account;
import finalproject.model.account.AccountType;

public interface BaseAccountCreator {
    Account createAccount(AccountType type);
}
