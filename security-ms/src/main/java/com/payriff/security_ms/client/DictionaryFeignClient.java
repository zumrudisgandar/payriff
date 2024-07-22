package com.payriff.security_ms.client;

import com.payriff.security_ms.dto.UserDto;
import com.payriff.security_ms.entity.UserCredential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "dictionary-ms", url = "http://localhost:8081")
public interface DictionaryFeignClient {
    @GetMapping("/api/users")
    List<UserCredential> findAllUsers();

    @GetMapping("/api/users/{id}")
    Optional<UserCredential> findUserById(@PathVariable("id") Integer id);

    @PostMapping("/api/users")
    UserCredential saveUser(@RequestBody UserCredential user);

    @GetMapping("/api/users/username/{username}")
    Optional<UserCredential> findUserByUsername(@PathVariable("username") String username);

    @PutMapping("/api/users/{id}")
    UserCredential updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto);

    @PutMapping("/api/users/username/{username}")
    UserCredential updateUserByUsername(@PathVariable("username") String username, @RequestBody UserDto userDto);

    @DeleteMapping("/api/users/username/{username}")
    void deleteUserByUsername(@PathVariable("username") String username);

    @DeleteMapping("/api/users/{id}")
    void deleteUser(@PathVariable("id") Integer id);
}
