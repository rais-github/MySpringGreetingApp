package com.MyGreetingApp.backend.service;

import com.MyGreetingApp.backend.model.Greeting;
import com.MyGreetingApp.backend.model.User;

public interface IGreetingService {

    Greeting addGreeting(User user);
    Greeting getGreetingById(long id);
    String sayHello();
}
