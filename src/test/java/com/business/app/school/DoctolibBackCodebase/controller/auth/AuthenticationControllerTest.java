package com.business.app.school.DoctolibBackCodebase.controller.auth;

import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationRequest;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.RegisterRequest;
import com.business.app.school.DoctolibBackCodebase.exception.AlreadyExistsException;
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
import static org.mockito.Mockito.*;


class AuthenticationControllerTest {


    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void if_register_succeed_HTPP_status_OK_is_expected() throws AlreadyExistsException {
        RegisterRequest registerRequest = new RegisterRequest();
        AuthenticationResponse expectedResponse = new AuthenticationResponse();
        when(authenticationService.register(registerRequest)).thenReturn(expectedResponse);

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(registerRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void if_register_succeed_response_entity_should_contain_body() throws AlreadyExistsException {
        RegisterRequest registerRequest = new RegisterRequest();
        AuthenticationResponse expectedResponse = new AuthenticationResponse();
        when(authenticationService.register(registerRequest)).thenReturn(expectedResponse);
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(registerRequest);
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void if_register_failed_with_AlreadyExistsException_error_HTPP_status_CONFLICT_is_expected() throws AlreadyExistsException {
        RegisterRequest registerRequest = new RegisterRequest();
        when(authenticationService.register(registerRequest)).thenThrow(new AlreadyExistsException(""));

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(registerRequest);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    public void if_register_failed_without_AlreadyExistsException_error_HTPP_status_CONFLICT_is_not_expected() throws AlreadyExistsException {
        RegisterRequest registerRequest = new RegisterRequest();
        when(authenticationService.register(registerRequest)).thenThrow(new Exception(""));

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(registerRequest);
        assertNotEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    public void verify_that_service_has_been_call_once() throws AlreadyExistsException {
        RegisterRequest registerRequest = new RegisterRequest();
        AuthenticationResponse expectedResponse = new AuthenticationResponse();
        when(authenticationService.register(registerRequest)).thenReturn(expectedResponse);
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(registerRequest);

        verify(authenticationService, times(1)).register(registerRequest);
    }

    @Test
    public void if_authenticate_succeed_HTTP_status_OK_is_expected() throws BadCredentialException {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        when(authenticationService.authenticate(authenticationRequest)).thenReturn(authenticationResponse);

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(authenticationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void if_authenticate_failed_HTTP_status_CONFLICT_is_expected() throws BadCredentialException {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        when(authenticationService.authenticate(authenticationRequest)).thenThrow(new BadCredentialException(""));

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(authenticationRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void if_authenticate_failed_HTTP_status_CONFLICT_is_not_expected() throws BadCredentialException {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        when(authenticationService.authenticate(authenticationRequest)).thenThrow(new Exception(""));

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(authenticationRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }



}