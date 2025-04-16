package ru.pavel.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usertable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The name must not be empty.")
    @Size(min = 2, max = 30, message = "Enter from 2 to 30 characters.")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "The lastName must not be empty.")
    @Size(min = 2, max = 30, message = "Enter from 2 to 30 characters.")
    @Column(name = "lastName")
    private String lastName;

    @Min(value = 0, message = "Enter a positive number.")
    @Column(name = "age")
    private int age;

    public User(int age, String lastName, String name) {
        this.age = age;
        this.lastName = lastName;
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
