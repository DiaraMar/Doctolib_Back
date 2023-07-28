package com.business.app.school.DoctolibBackCodebase.service;

import com.business.app.school.DoctolibBackCodebase.config.JwtAuthenticationFilterService;
import com.business.app.school.DoctolibBackCodebase.domain.account.Account;
import com.business.app.school.DoctolibBackCodebase.domain.account.AccountInterface;
import com.business.app.school.DoctolibBackCodebase.infra.user.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountInterface {

    private final AccountRepository accountRepository;
    private final JwtAuthenticationFilterService jwtAuthenticationFilterService;


    @Override
    public Optional<Account> getAccount(String email) {
            return this.accountRepository.getAccount(email);
    }

    @Override
    public Optional<List<Account>> getAllAccounts(String email) {
        System.out.println("service " + email);

        return this.accountRepository.getAllAccount(email);
    }


    @Override
    public Account updateAccount(Account account) {
        //If token valid
        //If accountId from token = accountId

        return this.accountRepository.updateAccount(account);
    }


}
