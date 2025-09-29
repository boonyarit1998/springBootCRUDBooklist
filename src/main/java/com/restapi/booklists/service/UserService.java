package com.restapi.booklists.service;

import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> findByEmail(String Email){
        return  userRepository.findByEmail(Email);
    }

    public void registerUser(UserEntity user){
        userRepository.save(user);
    }
}
