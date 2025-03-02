package com.MyGreetingApp.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
