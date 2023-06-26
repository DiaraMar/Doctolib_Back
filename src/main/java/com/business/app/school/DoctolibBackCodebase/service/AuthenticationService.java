package com.business.app.school.DoctolibBackCodebase.service;

import com.business.app.school.DoctolibBackCodebase.config.JwtService;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationRequest;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.RegisterRequest;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.ResetPasswordRequest;
import com.business.app.school.DoctolibBackCodebase.controller.auth.AuthenticationController;
import com.business.app.school.DoctolibBackCodebase.domain.Role;
import com.business.app.school.DoctolibBackCodebase.domain.auth.AuthInterface;
import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import com.business.app.school.DoctolibBackCodebase.exception.AlreadyExistsException;
import com.business.app.school.DoctolibBackCodebase.exception.BadCredentialException;
import com.business.app.school.DoctolibBackCodebase.infra.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthInterface {

    private final UserRepository userJPARepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);


    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) throws AlreadyExistsException {

        logger.info("Register(RegisterRequest registerRequest)");
        logger.info("registerRequest", registerRequest);

    if (isAlreadyRegister(registerRequest.getEmail())== false){
        logger.info("RegisterRequest not exist in the database");
        var user = User
                .builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        logger.info("user to save ", user);
        userJPARepository.save(user);

        logger.info("Generate token");
        var jwtToken = jwtService.generateToken(user);

        logger.info("Return token");
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    logger.info("RegisterRequest already exist in the database");
    return AuthenticationResponse
            .builder()
            .token(null)
            .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticateRequest) throws BadCredentialException {
        logger.info("authenticate(AuthenticationRequest authenticateRequest)");
        logger.info("authenticateRequest ", authenticateRequest);

        logger.info("authenticationManager.authenticate(UsernamePasswordAuthenticationToken)");
        logger.info("UsernamePasswordAuthenticationToken->getEmail() ", authenticateRequest.getEmail());
        logger.info("UsernamePasswordAuthenticationToken->getPassword ", authenticateRequest.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getEmail(),
                        authenticateRequest.getPassword()
                )
        );

        logger.info("userJPARepository.findByEmail(authenticateRequest.getEmail())");
        var user = userJPARepository.findByEmail(authenticateRequest.getEmail())
                .orElseThrow(); //todo : refactor.  generalize isAlreadyRegister method to be call also from here

        logger.info("User", user);
        logger.info("Generate token");
        var jwtToken = jwtService.generateToken(user);
        logger.info("Return token");
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
    @Override
    public AuthenticationResponse resetPassword(ResetPasswordRequest resetPasswordRequest) throws BadCredentialException {
        logger.info("ResetPassword(ResetPasswordRequest resetPasswordRequest)");
        logger.info("resetPasswordRequest", resetPasswordRequest);
        logger.info("AuthenticationManager.authenticate(UsernamePasswordAuthenticationToken)");
        logger.info("UsernamePasswordAuthenticationToken->getEmail() ", resetPasswordRequest.getEmail());
        logger.info("UsernamePasswordAuthenticationToken->getPassword ", resetPasswordRequest.getOldPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        resetPasswordRequest.getEmail(),
                        resetPasswordRequest.getOldPassword()
                )
        );

        logger.info("userJPARepository.findByEmail(resetPasswordRequest.getEmail())");
        var user = userJPARepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(); //todo : refactor.  generalize isAlreadyRegister method to be call also from here
        logger.info("User", user);
        logger.info("Generate token");

        logger.info("Update password *********");
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));

        logger.info("userJPARepository.save(user)");
        userJPARepository.save(user);

        logger.info("userJPARepository.findByEmail(resetPasswordRequest.getEmail())");
        user = userJPARepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow();
        System.out.println(user);

        logger.info("User", user);
        logger.info("Generate token");
        var jwtToken = jwtService.generateToken(user);
        logger.info("Return token");
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

