package com.MyGreetingApp.backend.controller;

import com.MyGreetingApp.backend.model.Greeting;
import com.MyGreetingApp.backend.model.User;
import com.MyGreetingApp.backend.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

    @Autowired
    private IGreetingService greetingService;

    @GetMapping
    public ResponseEntity<List<Greeting>> getAllGreetings() {
        List<Greeting> greetings = greetingService.getAll();
        return ResponseEntity.ok(greetings);
    }

    @PostMapping("/add")
    public ResponseEntity<Greeting> createNewGreeting(@RequestBody User user) {
        Greeting greeting = greetingService.addGreeting(user);
        return ResponseEntity.ok(greeting);
    }

    @GetMapping("g/id/{myId}")
    public ResponseEntity<Greeting> getGreetingById(@PathVariable long myId) {

        try {
            Greeting greeting = greetingService.getGreetingById(myId);

           
            System.out.println("Greeting: " + greeting);

            if (greeting != null) {
                return ResponseEntity.ok(greeting);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Greeting> updateGreeting(@PathVariable Long id, @RequestBody Greeting updatedGreeting) {
        Greeting updated = greetingService.updateGreetingById(id, updatedGreeting);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("d/id/{myId}")
    public String removeGreetingById(@PathVariable long myId){
        return "Remove Greeting with id :"+myId;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return greetingService.sayHello();
    }

    @GetMapping("/greet")
    public ResponseEntity<Greeting> greet(
            @RequestParam(value = "firstName", defaultValue = "") String firstName,
            @RequestParam(value = "lastName", defaultValue = "") String lastName) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);

        return ResponseEntity.ok(greetingService.addGreeting(user));
    }

    
}
