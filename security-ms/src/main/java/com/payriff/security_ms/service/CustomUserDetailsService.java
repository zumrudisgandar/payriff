package com.payriff.security_ms.service;

import com.payriff.security_ms.client.DictionaryFeignClient;
import com.payriff.security_ms.dto.CustomUserDetailsDto;
import com.payriff.security_ms.entity.UserCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DictionaryFeignClient dictionaryFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = dictionaryFeignClient.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetailsDto(userCredential);
    }
}