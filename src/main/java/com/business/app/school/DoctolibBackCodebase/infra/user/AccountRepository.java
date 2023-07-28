package com.business.app.school.DoctolibBackCodebase.infra.user;

import com.business.app.school.DoctolibBackCodebase.domain.account.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AccountRepository {

    private final PatientJPARepository patientJPARepository;
    private final UserRepository userRepository;


    public Optional<Patient> getAccount(String email) {
       return this.patientJPARepository.findById(1L);
    }

    public Optional<Patient> getPatientAccountBy(String email) {

        System.out.println("repo patient email " + email);

        if( !this.userRepository.findByEmail(email).isEmpty() && this.userRepository.findByEmail(email).get().getId() !=null){
            System.out.println("try get by ID " + this.userRepository.findByEmail(email).get().getId());

            return this.patientJPARepository.findPatientAccountBy(this.userRepository.findByEmail(email).get().getId());
        }
            return null;
    }
    public Patient save(Patient patient) {
        return this.patientJPARepository.save(patient);
    }

    public Patient updateAccount(Patient patient) {
        return this.patientJPARepository.save(patient);
    }
}
