package com.payriff.security_ms.service;

import com.payriff.security_ms.client.DictionaryFeignClient;
import com.payriff.security_ms.dto.UserDto;
import com.payriff.security_ms.entity.UserCredential;
import com.payriff.security_ms.exception.RecordNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final DictionaryFeignClient dictionaryFeignClient;

    public UserService(DictionaryFeignClient dictionaryFeignClient) {
        this.dictionaryFeignClient = dictionaryFeignClient;
    }

    public List<UserCredential> findAllUsers() {
        return dictionaryFeignClient.findAllUsers();
    }

    public UserCredential findUserById(Integer id) {
        return dictionaryFeignClient.findUserById(id).orElseThrow(() -> new RecordNotFoundException("User not found"));
    }

    public UserCredential findUserByUsername(String username) {
        return dictionaryFeignClient.findUserByUsername(username).orElseThrow(() -> new RecordNotFoundException("User not found"));
    }

    public UserCredential updateUser(Integer id, UserDto userDto) {
        UserCredential existingUser = dictionaryFeignClient.findUserById(id).orElseThrow(() -> new RecordNotFoundException("User not found"));
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        return dictionaryFeignClient.saveUser(existingUser);
    }

    public UserCredential updateUserByUsername(String username, UserDto userDto) {
        Optional<UserCredential> optionalUser = dictionaryFeignClient.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            UserCredential existingUser = optionalUser.get();
            existingUser.setUsername(userDto.getUsername());
            existingUser.setEmail(userDto.getEmail());
            return dictionaryFeignClient.saveUser(existingUser);
        } else {
            throw new RecordNotFoundException("User not found");
        }
    }

    public void deleteUserByUsername(String username) {
        Optional<UserCredential> optionalUser = dictionaryFeignClient.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            dictionaryFeignClient.deleteUserByUsername(username);
        } else {
            throw new RecordNotFoundException("User not found");
        }
    }
    public void deleteUser(Integer id) {
        dictionaryFeignClient.deleteUser(id);
    }
}
