package com.MyGreetingApp.backend.service;

import com.MyGreetingApp.backend.model.Greeting;
import com.MyGreetingApp.backend.model.User;
import com.MyGreetingApp.backend.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService implements IGreetingService{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public Greeting addGreeting(User user) {
        String message;

        if (user.getFirstName() != null && user.getLastName() != null) {
            message = String.format(template, user.getFirstName(), user.getLastName());
        } else if (user.getFirstName() != null) {
            message = String.format("Hello, %s!", user.getFirstName());
        } else if (user.getLastName() != null) {
            message = String.format("Hello, %s!", user.getLastName());
        } else {
            message = "Hello, World!";
        }
        return greetingRepository.save(new Greeting(counter.incrementAndGet(),message));
    }

    @Override
    public Greeting getGreetingById(long id) {
        return greetingRepository.findById(id).get();
    }

    @Override
    public String sayHello(){
        return "Hello";
    }
}
