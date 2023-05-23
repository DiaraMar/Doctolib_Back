package com.business.app.school.DoctolibBackCodebase.domain.user;

import com.business.app.school.DoctolibBackCodebase.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * This class represents users
 * @Data, @NoArgsConstructor, @AllArgsConstructor are used to reduce boilerplate code (getter, setter, controllers ...)
 * @Entity used to make this user class entity in springboot sense
 * @Table is used to renamed to avoid conflicts in case of changing management dependency tool, like postegre that already have User Table
 * This class implements UserDetails which is used when SpringSecurity start and set up the application. This embeds methods necessary to manage authentication of user
 */

@Data //getter setter ...
@Builder // simplify object building
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_doctolib")
public class User implements UserDetails {


    /**
     * @Id annotation added because Persistent Entity should have primary key according to documentation
     * @GeneratedValue is set with default value wich is GenerationType.AUTO )
     */

    @Id
    @GeneratedValue // Default value is GenerationType.AUTO )
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    /**
     * @EnumType by default is set to Ordinal (based on number). Data model will break if post changement occured, like adding and renaming enum, or changing enum's order.
     * String Enumtype is choosen for better stability, but renaming enum is still not safe.
     */
    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    /**
     * getPassword() from UserDetails was not override, because of the local variable password and it lombok annotation
     * change momently local variable name
     * implement method
     * and rename variable
     */
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
