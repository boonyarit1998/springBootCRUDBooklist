package com.restapi.booklists.service;

import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.repository.UserRepository;
import com.restapi.booklists.service.Impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserEntity user, Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null){
            userEntity.setUsername(user.getUsername());
            userEntity.setEmail(user.getEmail());
            userEntity.setPassword(user.getPassword());
            return userRepository.save(userEntity);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> searchUsers(String username,String email) {
        return userRepository.searchUsers(username,email);
    }
}
