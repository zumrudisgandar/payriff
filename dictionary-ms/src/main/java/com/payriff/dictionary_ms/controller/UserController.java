package com.payriff.dictionary_ms.controller;

import com.payriff.dictionary_ms.dto.UserDto;
import com.payriff.dictionary_ms.entity.UserCredential;
import com.payriff.dictionary_ms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserCredential> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserCredential> saveUser(@RequestBody UserDto userDto) {
        System.out.println("TESTING::" + userDto);
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCredential> findUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findUserById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserCredential> findUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCredential> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @PutMapping("/username/{username}")
    public ResponseEntity<UserCredential> updateUserByUsername(@PathVariable String username, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUserByUsername(username, userDto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @DeleteMapping("/username/{username}")
    public void deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
    }
}