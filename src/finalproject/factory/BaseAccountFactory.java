package finalproject.factory;

import finalproject.model.account.Account;
import finalproject.model.account.AccountType;

public interface BaseAccountFactory {
    Account createAccount(AccountType type);
}
