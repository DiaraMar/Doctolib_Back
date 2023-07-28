package com.business.app.school.DoctolibBackCodebase.domain.account;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AccountInterface {
    Optional<Account> getAccount(String email);
    Optional <List<Account>> getAllAccounts(String email);

    Account updateAccount(Account account);

}
