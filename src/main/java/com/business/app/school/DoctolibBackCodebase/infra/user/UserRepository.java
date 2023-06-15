package com.business.app.school.DoctolibBackCodebase.infra.user;

import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserRepository {

    private final UserJPARepository userJPARepository;

    public Optional<User> findByEmail(String email){
        return userJPARepository.findByEmail(email);
    }

    public User save(User user){
        return userJPARepository.save(user);
    }



}
