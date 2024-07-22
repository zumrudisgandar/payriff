package com.payriff.dictionary_ms.controller;

import com.payriff.dictionary_ms.dto.UserDto;
import com.payriff.dictionary_ms.entity.UserCredential;
import com.payriff.dictionary_ms.repository.UserCredentialRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserCredentialRepository userCredentialRepository;

    public UserController(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    public List<UserCredential> findAllUsers() {
        return userCredentialRepository.findAll();
    }

    @PostMapping
    public UserCredential saveUser(@RequestBody UserCredential user) {
        return userCredentialRepository.save(user);
    }

    @GetMapping("/{id}")
    public UserCredential findUserById(@PathVariable Integer id) {
        return userCredentialRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/username/{username}")
    public UserCredential findUserByUsername(@PathVariable String username) {
        return userCredentialRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/{id}")
    public UserCredential updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        UserCredential user = userCredentialRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        return userCredentialRepository.save(user);
    }

    @PutMapping("/username/{username}")
    public UserCredential updateUserByUsername(@PathVariable String username, @RequestBody UserDto userDto) {
        UserCredential user = userCredentialRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        return userCredentialRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        UserCredential user = userCredentialRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userCredentialRepository.delete(user);
    }

    @DeleteMapping("/username/{username}")
    public void deleteUserByUsername(@PathVariable String username) {
        UserCredential user = userCredentialRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        userCredentialRepository.delete(user);
    }
}
