package com.MyGreetingApp.backend.service;

import com.MyGreetingApp.backend.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User addUser(User user);
    User updateUser(Long id,User user);
    void removeUser(Long id);

}
