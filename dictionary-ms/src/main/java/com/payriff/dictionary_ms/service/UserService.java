package com.payriff.dictionary_ms.service;
import com.payriff.dictionary_ms.dto.UserDto;
import com.payriff.dictionary_ms.entity.UserCredential;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserCredential> findAllUsers();
    UserCredential saveUser(UserDto userDto);
    Optional<UserCredential> findUserById(Integer id);
    Optional<UserCredential> findUserByUsername(String username);
    UserCredential updateUser(Integer id, UserDto userDto);
    UserCredential updateUserByUsername(String username, UserDto userDto);
    void deleteUser(Integer id);
    void deleteUserByUsername(String username);
}

