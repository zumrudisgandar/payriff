//package com.finalproject.security_service.repository;
//
//import com.finalproject.security_service.entity.Person;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//public interface PersonRepository extends CrudRepository<Person, Integer> {
//
//    @Query("SELECT p.name FROM Person p WHERE p.name LIKE %:personName%")
//    String findByName(String personName);
//}
