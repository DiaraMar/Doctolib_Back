package com.business.app.school.DoctolibBackCodebase.controller.user;

import com.business.app.school.DoctolibBackCodebase.controller.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.DTO.ResetPasswordRequest;
import com.business.app.school.DoctolibBackCodebase.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PatchMapping("/password")
    public ResponseEntity<AuthenticationResponse> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            System.out.println("Controller " + resetPasswordRequest);

            authenticationService.resetPassword(resetPasswordRequest);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
}


}
