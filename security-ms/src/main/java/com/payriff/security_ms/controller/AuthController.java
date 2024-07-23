package com.payriff.security_ms.controller;

import com.payriff.security_ms.dto.AuthRequest;
import com.payriff.security_ms.dto.AuthResponse;
import com.payriff.security_ms.dto.UserDto;
import com.payriff.security_ms.entity.UserCredential;
import com.payriff.security_ms.exception.BadRequestException;
import com.payriff.security_ms.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserCredential> addNewUser(@Valid @RequestBody UserDto userDto) {
            return ResponseEntity.ok(authService.saveUser(userDto));
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody AuthRequest authRequest) {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if(authenticate.isAuthenticated()) {
                return ResponseEntity.ok(new AuthResponse(authService.generateToken(authRequest.getUsername()), "Token generated!"));
            } else {
                throw new BadCredentialsException("Invalid access");
            }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new BadRequestException("Token is missing or empty");
        }
            boolean isValid = authService.validateToken(token);
            if (isValid) {
                return ResponseEntity.ok(new AuthResponse(token, "Valid token!"));
            } else {
                throw new BadCredentialsException("Invalid token");
            }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            if (authenticate.isAuthenticated()) {
                String token = authService.generateToken(authRequest.getUsername());
                authService.validateToken(token);
                return ResponseEntity.ok(new AuthResponse(token, "Successful login!"));
            } else {
                throw new BadCredentialsException("Invalid access");
            }
    }
}
