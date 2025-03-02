package com.MyGreetingApp.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Greeting {

    @Id

    private Long id;
    private String message;
    private LocalDateTime createdAt;


    public Greeting(long id, String message) {
        this.id=id;
        this.message=message;
    }
}
