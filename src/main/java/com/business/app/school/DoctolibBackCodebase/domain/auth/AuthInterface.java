package com.business.app.school.DoctolibBackCodebase.domain.auth;

import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationRequest;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.RegisterRequest;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.ResetPasswordRequest;
import com.business.app.school.DoctolibBackCodebase.exception.AlreadyExistsException;
import com.business.app.school.DoctolibBackCodebase.exception.BadCredentialException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public interface AuthInterface {

    AuthenticationResponse register(RegisterRequest registerRequest)  throws AlreadyExistsException;
    AuthenticationResponse authenticate(AuthenticationRequest authenticateRequest) throws BadCredentialException;
    AuthenticationResponse resetPassword(ResetPasswordRequest resetPasswordRequest) throws BadCredentialException;
    boolean isAlreadyRegister(String email) throws AlreadyExistsException;
    boolean isTokenExpired(String token, UserDetails userDetails);



}
