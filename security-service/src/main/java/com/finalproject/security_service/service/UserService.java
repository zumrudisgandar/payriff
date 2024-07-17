package com.finalproject.security_service.service;

import com.finalproject.security_service.dto.UserDto;
import com.finalproject.security_service.entity.UserCredential;
import com.finalproject.security_service.exception.RecordNotFoundException;
import com.finalproject.security_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserCredentialRepository userRepository;

    public List<UserCredential> findAllUsers() {
        return userRepository.findAll();
    }

    public UserCredential findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User not found"));
    }

    public UserCredential findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RecordNotFoundException("User not found"));
    }

    public UserCredential updateUser(Integer id, UserDto userDto) {
        UserCredential existingUser = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User not found"));
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        return userRepository.save(existingUser);
    }

    public UserCredential updateUserByUsername(String username, UserDto userDto) {
        Optional<UserCredential> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserCredential existingUser = optionalUser.get();
            existingUser.setUsername(userDto.getUsername());
            existingUser.setEmail(userDto.getEmail());
            return userRepository.save(existingUser);
        } else {
            throw new RecordNotFoundException("User not found");
        }
    }

    public void deleteUserByUsername(String username) {
        Optional<UserCredential> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new RecordNotFoundException("User not found");
        }
    }
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
