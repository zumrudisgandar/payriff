package com.payriff.security_ms.controller;

import com.payriff.security_ms.dto.UserDto;
import com.payriff.security_ms.entity.UserCredential;
import com.payriff.security_ms.exception.RecordNotFoundException;
import com.payriff.security_ms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<UserCredential> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserCredential getUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    public UserCredential updateUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {
            return userService.updateUser(id, userDto);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
            userService.deleteUser(id);
    }


    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                UserCredential userCredential = userService.findUserByUsername(userDetails.getUsername());
                return ResponseEntity.ok(userCredential);
            } else {
                throw new RecordNotFoundException("User not authenticated or principal is not an instance of UserDetails");
            }
    }

    @PutMapping("/me")
    public UserCredential updateCurrentUser(@Valid @RequestBody UserDto user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userService.updateUserByUsername(userDetails.getUsername(), user);
    }

    @DeleteMapping("/me")
    public void deleteCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            userService.deleteUserByUsername(userDetails.getUsername());
    }
}