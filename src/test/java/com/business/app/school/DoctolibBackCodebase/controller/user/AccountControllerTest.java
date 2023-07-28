package com.business.app.school.DoctolibBackCodebase.controller.user;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.ResetPasswordRequest;
import com.business.app.school.DoctolibBackCodebase.exception.BadCredentialException;
import com.business.app.school.DoctolibBackCodebase.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class AccountControllerTest {


    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void if_resetPasswordRequest_succeed_HTPP_status_OK_is_expected() throws BadCredentialException {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        when(authenticationService.resetPassword(resetPasswordRequest)).thenReturn(authenticationResponse);

        ResponseEntity<AuthenticationResponse> responseEntity = accountController.resetPassword(resetPasswordRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void if_email_not_recognized_HTPP_status_BAD_REQUEST_is_expected() throws BadCredentialException {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        when(authenticationService.resetPassword(resetPasswordRequest)).thenThrow(new BadCredentialException(""));

        ResponseEntity<AuthenticationResponse> responseEntity = accountController.resetPassword(resetPasswordRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void if_oldPassword_not_recognized_HTPP_status_BAD_REQUEST_is_expected() throws BadCredentialException {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setOldPassword(null);
        AuthenticationResponse expectedResponse = new AuthenticationResponse();
        when(authenticationService.resetPassword(resetPasswordRequest)).thenThrow(new BadCredentialException(""));

        ResponseEntity<AuthenticationResponse> responseEntity = accountController.resetPassword(resetPasswordRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }




}