package com.MyGreetingApp.backend.service;

import com.MyGreetingApp.backend.custom_exceptions.UserNotFoundException;
import com.MyGreetingApp.backend.model.User;
import com.MyGreetingApp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    public UserService() {
        super();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userInDb = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        userInDb.setLastName(user.getLastName());
        userInDb.setFirstName(user.getFirstName());
        return userRepository.save(userInDb);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
        return;
    }


}
