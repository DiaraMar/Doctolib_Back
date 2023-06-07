package com.business.app.school.DoctolibBackCodebase.service;

import com.business.app.school.DoctolibBackCodebase.config.JwtService;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationRequest;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.RegisterRequest;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.ResetPasswordRequest;
import com.business.app.school.DoctolibBackCodebase.domain.Role;
import com.business.app.school.DoctolibBackCodebase.domain.auth.AuthInterface;
import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import com.business.app.school.DoctolibBackCodebase.exception.AlreadyExistsException;
import com.business.app.school.DoctolibBackCodebase.exception.BadCredentialException;
import com.business.app.school.DoctolibBackCodebase.infra.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthInterface {

    private final UserJPARepository userJPARepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) throws AlreadyExistsException {

    if (isAlreadyRegister(registerRequest.getEmail())== false){
        var user = User
                .builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();


        userJPARepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();

    }

    return AuthenticationResponse
            .builder()
            .token(null)
            .build();

    }
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticateRequest) throws BadCredentialException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getEmail(),
                        authenticateRequest.getPassword()
                )
        );

        var user = userJPARepository.findByEmail(authenticateRequest.getEmail())
                .orElseThrow(); //todo : refactor.  generalize isAlreadyRegister method to be call also from here

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
    @Override
    public AuthenticationResponse resetPassword(ResetPasswordRequest resetPasswordRequest) throws BadCredentialException {
        System.out.println("resetpassword method");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        resetPasswordRequest.getEmail(),
                        resetPasswordRequest.getOldPassword()
                )
        );

        var user = userJPARepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(); //todo : refactor.  generalize isAlreadyRegister method to be call also from here

        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        user.setFirstname("update firstname");

        System.out.println("user before update"+ user);
        userJPARepository.save(user);
        user = userJPARepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow();
        System.out.println(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public boolean isAlreadyRegister(String email) throws AlreadyExistsException {
        if(!userJPARepository.findByEmail(email).isEmpty()){
            throw new AlreadyExistsException("User already exists in the database.");
        }
    return false;
    }




}

