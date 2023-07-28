package com.business.app.school.DoctolibBackCodebase.domain.account;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AccountInterface {
    Optional<Patient> getPatientAccount(String email);

    Patient updateAccount(Patient patient, String username);

}
