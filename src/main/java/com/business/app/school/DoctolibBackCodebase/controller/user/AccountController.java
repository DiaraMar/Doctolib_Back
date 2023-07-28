package com.business.app.school.DoctolibBackCodebase.controller.user;

import com.business.app.school.DoctolibBackCodebase.config.interceptor.BearerTokenWrapper;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.PatientRequest;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.PatientResponse;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.ResetPasswordRequest;
import com.business.app.school.DoctolibBackCodebase.domain.account.Patient;
import com.business.app.school.DoctolibBackCodebase.exception.BadCredentialException;
import com.business.app.school.DoctolibBackCodebase.service.AuthenticationService;
import com.business.app.school.DoctolibBackCodebase.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class AccountController {

    private final AuthenticationService authenticationService;
    private final AccountService userService;
    private static final Logger logger = LogManager.getLogger(AccountController.class);

    private final BearerTokenWrapper tokenWrapper;


    private String extractUsername(){
        logger.info("Try extract userdetails from token");
        try {
            return  this.authenticationService.getEmail(tokenWrapper.getToken());
        }
        catch (Exception e) {
            logger.error("Failed extract operation", e);
            return null;
        }
    }


    /** ALL profil **/
    @PatchMapping("/password")
    public ResponseEntity<AuthenticationResponse> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws BadCredentialException {
        logger.info("@PatchMapping/password");
        logger.info("resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest)");
        try {
            logger.info("Try block ");
            logger.info("authenticationService.resetPassword(ResetPasswordRequest resetPasswordRequest)");
            logger.info("resetPasswordRequest  ", resetPasswordRequest);
            authenticationService.resetPassword(resetPasswordRequest);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (BadCredentialException e) {
            logger.info("Catch block handle exception ");
            logger.info("BadCredentialException ",e);
            logger.info("Response http : Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (Exception e) {
            logger.error("Catch block exception undefined", e);
            logger.info("Response http is INTERNAL_SERVER_ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse("Internal error occure"));
        }
    }


    /** Patient profil **/

    @GetMapping("/account")
    public Optional<PatientResponse> getPatientAccount() {
        logger.info(" @GetMapping/account");
        return Optional.of(new PatientResponse(this.userService.getPatientAccount(extractUsername())));
    }

    @PatchMapping("/account")
    public Optional<PatientResponse> updateAccount(@RequestBody PatientRequest patientRequest) {
        return Optional.of(new PatientResponse(this.userService.updateAccount(patientRequest.toPatient(), extractUsername())));
    }

    /** Patient profil **/



    /** Assistant profil **/




}
