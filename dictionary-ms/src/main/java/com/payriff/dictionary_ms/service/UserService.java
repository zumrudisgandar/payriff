//package com.payriff.dictionary_ms.service;
//import com.payriff.dictionary_ms.dto.UserDto;
//import com.payriff.dictionary_ms.entity.UserCredential;
//import com.payriff.dictionary_ms.exception.RecordNotFoundException;
//import com.payriff.dictionary_ms.repository.UserCredentialRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserCredentialRepository userCredentialRepository;
//
//    public List<UserCredential> findAllUsers() {
//        return userCredentialRepository.findAll();
//    }
//
//    public Optional<UserCredential> findUserById(Integer id) {
//        return userCredentialRepository.findById(id);
//    }
//
//    public UserCredential saveUser(UserCredential user) {
//        return userCredentialRepository.save(user);
//    }
//
//    public Optional<UserCredential> findUserByUsername(String username) {
//        return userCredentialRepository.findByUsername(username);
//    }
//
//    public UserCredential updateUser(Integer id, UserDto userDto) {
//        UserCredential existingUser = userCredentialRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User not found"));
//        existingUser.setUsername(userDto.getUsername());
//        existingUser.setEmail(userDto.getEmail());
//        return userCredentialRepository.save(existingUser);
//    }
//
//    public void deleteUserById(Integer id) {
//        userCredentialRepository.deleteById(id);
//    }
//}
//
