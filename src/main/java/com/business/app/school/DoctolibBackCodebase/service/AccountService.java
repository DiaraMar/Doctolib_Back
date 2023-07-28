package com.business.app.school.DoctolibBackCodebase.service;

import com.business.app.school.DoctolibBackCodebase.config.JwtAuthenticationFilterService;
import com.business.app.school.DoctolibBackCodebase.domain.account.Patient;
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


    @Override
    public Optional<Patient> getPatientAccount(String email) {
        return this.accountRepository.getPatientAccountBy(email);
    }


    @Override
    public Patient updateAccount(Patient updatedPatient, String username) {

        Patient savedPatient = this.accountRepository.getAccount(username).get();
        //
        if( savedPatient !=null){
            updatedPatient.setId(savedPatient.getId());
            updatedPatient.setUser(savedPatient.getUser());
            return this.accountRepository.updateAccount(updatedPatient);

        }

        return null;
        //If accountId from token = accountId

    }


}
