package com.payriff.security_ms.service;
import com.payriff.security_ms.dto.UserDto;
import com.payriff.security_ms.entity.UserCredential;
import java.util.List;

public interface UserService {
    List<UserCredential> findAllUsers();
    UserCredential findUserById(Integer id);
    UserCredential findUserByUsername(String username);
    UserCredential updateUser(Integer id, UserDto userDto);
    UserCredential updateUserByUsername(String username, UserDto userDto);
    void deleteUserByUsername(String username);
    void deleteUser(Integer id);
}
