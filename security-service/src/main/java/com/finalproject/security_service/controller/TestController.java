package com.finalproject.security_service.controller;

import com.finalproject.security_service.entity.Person;
import com.finalproject.security_service.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class TestController {

    private final PersonRepository personRepository;

    public TestController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("person")
    public String createPerson(@RequestParam String name) {
        personRepository.save(new Person(name, "6.7"));
        return personRepository.findByName(name) + " Saved successfully";
    }

    @GetMapping("person")
    public List<Person> getAllThePeople() {
        return (List<Person>)  personRepository.findAll();
    }

}
