package com.payriff.security_ms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {

    private Integer id;

    private String username;

    private String email;

    private String password;

    private String roles; //ROLE_USER;ROLE_ADMIN -> persisted in DB

    private List<String> authorities = new ArrayList<>(Arrays.asList("ROLE_USER"));

    public List<GrantedAuthority> getAuthorities() {
        return this.authorities.stream().map(
                        role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    public List<String> getAuthoritiesList() {
        return authorities;
    }

}
