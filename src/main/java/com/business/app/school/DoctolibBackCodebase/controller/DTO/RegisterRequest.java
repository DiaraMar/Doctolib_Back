package com.business.app.school.DoctolibBackCodebase.controller.DTO;

import com.business.app.school.DoctolibBackCodebase.domain.account.Patient;
import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private Patient patient;




}
