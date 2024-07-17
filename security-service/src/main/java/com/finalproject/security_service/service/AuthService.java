package com.finalproject.security_service.service;

import com.finalproject.security_service.dto.UserDto;
import com.finalproject.security_service.entity.UserCredential;
import com.finalproject.security_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public String saveUser(UserDto userDto) {
        UserCredential user = new UserCredential();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        repository.save(user);
        return "User added to the system";
    }

    public String generateToken(String userName) {
        Optional<UserCredential> userOptional = repository.findByUsername(userName);
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
