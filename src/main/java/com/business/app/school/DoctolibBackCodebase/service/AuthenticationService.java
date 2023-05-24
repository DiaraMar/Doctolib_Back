package com.business.app.school.DoctolibBackCodebase.service;

import com.business.app.school.DoctolibBackCodebase.config.JwtService;
import com.business.app.school.DoctolibBackCodebase.controller.auth.DTO.AuthenticationRequest;
import com.business.app.school.DoctolibBackCodebase.controller.auth.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.auth.DTO.RegisterRequest;
import com.business.app.school.DoctolibBackCodebase.domain.Role;
import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import com.business.app.school.DoctolibBackCodebase.exception.AlreadyExistsException;
import com.business.app.school.DoctolibBackCodebase.exception.BadCredentialException;
import com.business.app.school.DoctolibBackCodebase.infra.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
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

        userRepository.save(user);

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

    public AuthenticationResponse authenticate(AuthenticationRequest authenticateRequest) throws BadCredentialException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getEmail(),
                        authenticateRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticateRequest.getEmail())
                .orElseThrow(); //todo : refactor.  generalize isAlreadyRegister method to be call also from here

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public boolean isAlreadyRegister(String email) throws AlreadyExistsException {
        if(!userRepository.findByEmail(email).isEmpty()){
            throw new AlreadyExistsException("User already exists in the database.");
        }
    return false;
    }
}

