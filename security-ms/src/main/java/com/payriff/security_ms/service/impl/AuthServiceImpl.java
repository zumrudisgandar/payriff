package com.payriff.security_ms.service.impl;

import com.payriff.security_ms.client.DictionaryFeignClient;
import com.payriff.security_ms.dto.UserDto;
import com.payriff.security_ms.entity.UserCredential;
import com.payriff.security_ms.service.AuthService;
import com.payriff.security_ms.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final DictionaryFeignClient dictionaryFeignClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserCredential saveUser(UserDto userDto) {
        UserCredential user = new UserCredential();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println("MANUAL: " + user);
        return dictionaryFeignClient.saveUser(user);
    }

    public String generateToken(String userName) {
        Optional<UserCredential> userOptional = dictionaryFeignClient.findUserByUsername(userName);
        if (userOptional.isPresent()) {
            UserCredential user = userOptional.get();
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return jwtService.generateToken(userName, authorities);
        }
        throw new UsernameNotFoundException("User not found");
    }

    public boolean validateToken(String token) {
        if(jwtService.validateToken(token)) {
            return true;
        }
        return false;
    }
}
