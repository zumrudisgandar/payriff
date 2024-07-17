package com.finalproject.security_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String email;

    private String password;

    private String roles; //ROLE_USER;ROLE_ADMIN -> persisted in DB

    @Transient
    private List<String> authorities = new ArrayList<>(Arrays.asList("ROLE_USER"));

    public UserCredential(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public List<GrantedAuthority> getAuthorities() {
        return this.authorities.stream().map(
                        role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    public UserCredential addRole(String authority) {
        this.authorities.add(authority);
        return this;
    }

    public List<String> getAuthoritiesList() {
        return authorities;
    }

    @PrePersist
    @PreUpdate
    private void saveRoles() {
        this.roles = String.join(";", this.authorities);
    }

    @PostLoad
    private void readRoles() {
        this.authorities = Arrays.stream(this.roles.split(";"))
                .collect(Collectors.toList());
    }
}
