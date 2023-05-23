package com.business.app.school.DoctolibBackCodebase.controller.auth;


import com.business.app.school.DoctolibBackCodebase.controller.auth.DTO.AuthenticationRequest;
import com.business.app.school.DoctolibBackCodebase.controller.auth.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.auth.DTO.RegisterRequest;
import com.business.app.school.DoctolibBackCodebase.exception.AlreadyExistsException;
import com.business.app.school.DoctolibBackCodebase.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) throws AlreadyExistsException {
        try {
            AuthenticationResponse response = this.authenticationService.register(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest registerRequest){
        return ResponseEntity.ok(this.authenticationService.authenticate(registerRequest));
    }


}
