package com.business.app.school.DoctolibBackCodebase.controller.DTO;

import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
    private String email;
    String oldPassword;
    String newPassword;

    public User toUser() {
        User user = new User();
        user.setEmail(this.email);
        user.setPassword(this.oldPassword);
        return user;
    }



}
