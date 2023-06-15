package com.business.app.school.DoctolibBackCodebase.controller.auth;


import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationRequest;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.RegisterRequest;
import com.business.app.school.DoctolibBackCodebase.exception.AlreadyExistsException;
import com.business.app.school.DoctolibBackCodebase.exception.BadCredentialException;
import com.business.app.school.DoctolibBackCodebase.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) throws AlreadyExistsException {
        logger.info("@PostMapping/register");
        logger.info("register(@RequestBody RegisterRequest registerRequest)");
        try {
            logger.info("Try block ");
            logger.info("authenticationService.resetPassword(RegisterRequest registerRequest)");
            logger.info("registerRequest  ", registerRequest);
            AuthenticationResponse response = this.authenticationService.register(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AlreadyExistsException e) {
            logger.info("Catch block handle exception ");
            logger.info("AlreadyExistsException ",e);
            logger.info("Response http : Conflict");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (Exception e) {
            logger.error("Catch block exception undefined", e);
            logger.info("Response http is INTERNAL_SERVER_ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse("Internal error occure"));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest registerRequest) throws BadCredentialException{
        logger.info("@PostMapping/authenticate");
        logger.info("authenticate(@RequestBody AuthenticationRequest registerRequest)");
        try {
            logger.info("Try block ");
            logger.info("authenticationService.resetPassword(AuthenticationRequest registerRequest)");
            logger.info("registerRequest  ", registerRequest);
            AuthenticationResponse response = this.authenticationService.authenticate(registerRequest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (BadCredentialException e) {
            logger.info("Catch block handle exception ");
            logger.info("BadCredentialException ",e);
            logger.info("Response http : UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (Exception e) {
            logger.error("Catch block exception undefined", e);
            logger.info("Response http is INTERNAL_SERVER_ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse("Internal error occure"));
        }

    }

}
