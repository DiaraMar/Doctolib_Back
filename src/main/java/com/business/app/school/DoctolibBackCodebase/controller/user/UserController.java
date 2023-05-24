package com.business.app.school.DoctolibBackCodebase.controller.user;

import com.business.app.school.DoctolibBackCodebase.controller.auth.DTO.AuthenticationResponse;
import com.business.app.school.DoctolibBackCodebase.controller.auth.DTO.RegisterRequest;
import com.business.app.school.DoctolibBackCodebase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/password")
    public ResponseEntity<AuthenticationResponse> resetPassword(@RequestBody RegisterRequest registerRequest) {
        try {
            System.out.println("Controller " + registerRequest);
            userService.updateUser(registerRequest.toUser());
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }

}}
