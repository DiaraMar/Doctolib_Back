package com.business.app.school.DoctolibBackCodebase.infra.user;

import com.business.app.school.DoctolibBackCodebase.domain.Role;
import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter setter ...
@Builder // simplify object building
@NoArgsConstructor
@AllArgsConstructor
public class UserDAO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    public UserDAO toRegisterUserDAO(User user){
        return UserDAO.builder().build();
    }

    public User toRegisterUser(){
        return User.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .build();
    }

}
