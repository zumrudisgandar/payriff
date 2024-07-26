package com.payriff.security_ms.service;

import com.payriff.security_ms.dto.UserDto;
import com.payriff.security_ms.entity.UserCredential;


public interface AuthService {
    UserCredential saveUser(UserDto userDto);
    String generateToken(String userName);
    boolean validateToken(String token);
}
