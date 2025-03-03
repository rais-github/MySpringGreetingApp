package com.MyGreetingApp.backend.service;

import com.MyGreetingApp.backend.model.Greeting;
import com.MyGreetingApp.backend.model.User;

import java.util.List;

public interface IGreetingService {

    List<Greeting> getAll();
    Greeting addGreeting(User user);
    Greeting getGreetingById(long id);
    Greeting updateGreetingById(Long id, Greeting updatedGreeting);
    void removeGreetingById(Long id);
    String sayHello();
}
