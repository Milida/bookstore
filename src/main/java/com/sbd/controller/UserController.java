package com.sbd.controller;

import com.sbd.bookstore.repository.UserRepository;
import com.sbd.model.User;
import com.sbd.payroll.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<User> addUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new ConflictException(String.format("User with email '%s' already exists", user.getEmail()));

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
}