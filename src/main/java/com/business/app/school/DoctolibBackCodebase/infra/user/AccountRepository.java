package com.business.app.school.DoctolibBackCodebase.infra.user;

import com.business.app.school.DoctolibBackCodebase.domain.account.Account;
import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import com.business.app.school.DoctolibBackCodebase.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AccountRepository {

    private final AccountJPARepository accountJPARepository;
    private final UserRepository userRepository;


    public Optional<Account> getAccount(String email) {
       return this.accountJPARepository.findById(1L);
    }

    public Optional<List<Account>> getAllAccount(String email) {


        if( !this.userRepository.findByEmail(email).isEmpty() && this.userRepository.findByEmail(email).get().getId() !=null){
            System.out.println("repo user");

            return this.accountJPARepository.findAllAccountBy(this.userRepository.findByEmail(email).get().getId());
        }
            return null;
    }
    public Account save(Account account) {
        return this.accountJPARepository.save(account);
    }

    public Account updateAccount(Account account) {
        return this.accountJPARepository.save(account);
    }
}
