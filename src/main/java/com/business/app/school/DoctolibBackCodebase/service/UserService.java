package com.business.app.school.DoctolibBackCodebase.service;

import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import com.business.app.school.DoctolibBackCodebase.domain.user.UserInterface;
import com.business.app.school.DoctolibBackCodebase.infra.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {

    private final UserRepository userRepository;
    @Override
    public void updateUser(User user) {
        Optional<User> userInDatabase = userRepository.findByEmail(user.getEmail());

        System.out.println("Service " + userInDatabase);

        user.setId(userInDatabase.get().getId());
        //user.setRole(userInDatabase.se);


    }
}
