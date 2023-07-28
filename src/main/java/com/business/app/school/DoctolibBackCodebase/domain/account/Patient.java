package com.business.app.school.DoctolibBackCodebase.domain.account;

import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter setter ...
@Builder // simplify object building
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_doctolib")
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    private int securityNumber;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
}
