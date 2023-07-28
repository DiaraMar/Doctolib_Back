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
@Table(name = "account_doctolib")
public class Account {
    @Id
    Long id;
    String truc;
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @ManyToOne(optional = false) //fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
    private User user;
}
