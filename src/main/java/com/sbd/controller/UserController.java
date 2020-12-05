package com.sbd.controller;

import com.sbd.bookstore.repository.UserRepository;
import com.sbd.model.User;
import com.sbd.payroll.ConflictException;
import com.sbd.payroll.Credentials;
import com.sbd.payroll.NotFoundException;
import com.sbd.payroll.UnauthorisedException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with ID = %d was not found", id)));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<User> addUser(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail()))
            throw new ConflictException(String.format("User with email '%s' already exists", user.getEmail()));

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        if (user.getPassword() == null || user.getPassword().equals("")) {
            Optional<User> tmp = userRepository.findById(id);
            String password = tmp.get().getPassword();
            user.setPassword(password);
        }
        userRepository.findById(id).map(oldUser -> {
            BeanUtils.copyProperties(user, oldUser, new String[] { "id" });
            return userRepository.save(oldUser);
        }).orElseGet(() -> userRepository.save(user));

        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> removeUser(@PathVariable Long id) {
        User user = getUser(id).getBody();
        user.setIsActive(false);
        userRepository.save(user);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Credentials credentials) {
        User user = userRepository.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword())
                .orElseThrow(() -> new UnauthorisedException("Invalid login or password!"));
        System.out.println(user.getIsActive());
        if (user.getIsActive()) {
            HttpHeaders responseHeaders = new HttpHeaders();

            responseHeaders.set("isWorker", Boolean.toString(false));

            return ResponseEntity.ok().headers(responseHeaders).body(user);
        } else {
            throw new UnauthorisedException("Account is inactive!");
        }

    }

}