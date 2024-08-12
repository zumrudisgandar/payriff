package com.payriff.payment_ms.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Collection;
import java.util.Map;

public interface JwtService {
    boolean validateToken(final String token);
    String generateToken(String userName, String email, Collection<? extends GrantedAuthority> authorities);
    String createToken(Map<String, Object> claims, String userName);
    Key getSignKey();
    Claims extractAllClaims(String token);
    String extractUsername(String token);
}
