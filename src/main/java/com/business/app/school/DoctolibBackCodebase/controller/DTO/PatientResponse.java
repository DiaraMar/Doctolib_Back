package com.business.app.school.DoctolibBackCodebase.controller.DTO;

import com.business.app.school.DoctolibBackCodebase.domain.account.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

    private int securityNumber;

    public PatientResponse(Patient patient){
        this.securityNumber = patient.getSecurityNumber();
    }

    public PatientResponse(Optional<Patient> patient){
        this.securityNumber = patient.get().getSecurityNumber();
    }


}
