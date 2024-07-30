package com.payriff.dictionary_ms.service.impl;


import com.payriff.dictionary_ms.dto.UserDto;
import com.payriff.dictionary_ms.entity.UserCredential;
import com.payriff.dictionary_ms.repository.UserCredentialRepository;
import com.payriff.dictionary_ms.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserCredentialRepository userCredentialRepository;

    public UserServiceImpl(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    @Override
    public List<UserCredential> findAllUsers() {
        return userCredentialRepository.findAll();
    }

    @Override
    public UserCredential saveUser(UserDto userDto) {
        UserCredential user = new UserCredential();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userCredentialRepository.save(user);
    }

    @Override
    public Optional<UserCredential> findUserById(Integer id) {
        return userCredentialRepository.findById(id);
    }

    @Override
    public Optional<UserCredential> findUserByUsername(String username) {
        return userCredentialRepository.findByUsername(username);
    }

    @Override
    public UserCredential updateUser(Integer id, UserDto userDto) {
        UserCredential user = userCredentialRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userCredentialRepository.save(user);
    }

    @Override
    public UserCredential updateUserByUsername(String username, UserDto userDto) {
        UserCredential user = userCredentialRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userCredentialRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        UserCredential user = userCredentialRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userCredentialRepository.delete(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        UserCredential user = userCredentialRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        userCredentialRepository.delete(user);
    }
}