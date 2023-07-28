package com.business.app.school.DoctolibBackCodebase.controller.DTO;

import com.business.app.school.DoctolibBackCodebase.domain.account.Account;
import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private List<Account> accounts= new ArrayList<>();

    public User toUser() {
        User user = new User();
        user.setEmail(this.email);
        user.setFirstname(this.firstname);
        user.setLastname(this.lastname);
        user.setPassword(this.password);
        user.setAccounts(this.accounts);
        return user;
    }



}
