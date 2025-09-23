package com.restapi.booklists.service.Impl;

import com.restapi.booklists.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(Long id);
    UserEntity registerUser(UserEntity user);
    UserEntity updateUser(UserEntity user,Long id);
    void deleteUser(Long id);
    List<UserEntity> searchUsers(String username,String email);
}
