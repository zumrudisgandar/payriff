package com.finalproject.security_service.controller;

import com.finalproject.security_service.dto.AuthRequest;
import com.finalproject.security_service.dto.AuthResponse;
import com.finalproject.security_service.dto.UserDto;
import com.finalproject.security_service.exception.BadRequestException;
import com.finalproject.security_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserDto userDto) {
            return ResponseEntity.ok(authService.saveUser(userDto));
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody AuthRequest authRequest) {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if(authenticate.isAuthenticated()) {
                return ResponseEntity.ok(authService.generateToken(authRequest.getUsername()));
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
