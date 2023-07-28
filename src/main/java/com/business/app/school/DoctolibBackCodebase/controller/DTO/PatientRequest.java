package com.business.app.school.DoctolibBackCodebase.controller.DTO;

import com.business.app.school.DoctolibBackCodebase.domain.account.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    private int securityNumber;

    public Patient toPatient(){
        Patient patient = Patient
                .builder()
                .securityNumber(this.securityNumber)
                .build();
        return patient;
    }

}
