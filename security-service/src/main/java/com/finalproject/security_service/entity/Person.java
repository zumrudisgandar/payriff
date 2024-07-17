package com.finalproject.security_service.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "persons")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String height;

    public Person(String name, String height) {
        this.name = name;
        this.height = height;
    }

    public Person() {
    }
}
