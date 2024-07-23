package com.payriff.security_ms.client;

import com.payriff.security_ms.dto.UserDto;
import com.payriff.security_ms.entity.UserCredential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "dictionary-ms", url = "http://localhost:8081")
public interface DictionaryFeignClient {
    @GetMapping("/api/auth/user")
    List<UserCredential> findAllUsers();

    @GetMapping("/api/auth/user/{id}")
    Optional<UserCredential> findUserById(@PathVariable("id") Integer id);

    @PostMapping("/api/auth/user")
    UserCredential saveUser(@RequestBody UserCredential user);

    @GetMapping("/api/auth/user/username/{username}")
    Optional<UserCredential> findUserByUsername(@PathVariable("username") String username);

    @PutMapping("/api/auth/user/{id}")
    UserCredential updateUser(@PathVariable("id") Integer id, @RequestBody UserCredential user);

    @PutMapping("/api/auth/user/username/{username}")
    UserCredential updateUserByUsername(@PathVariable("username") String username, @RequestBody UserCredential user);

    @DeleteMapping("/api/auth/user/username/{username}")
    void deleteUserByUsername(@PathVariable("username") String username);

    @DeleteMapping("/api/auth/user/{id}")
    void deleteUser(@PathVariable("id") Integer id);
}
