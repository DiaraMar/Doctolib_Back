package com.business.app.school.DoctolibBackCodebase.infra.user;

import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    User save(User user);
}
