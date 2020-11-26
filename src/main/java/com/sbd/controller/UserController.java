package com.sbd.controller;

import com.sbd.bookstore.repository.UserRepository;
import com.sbd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="/user/add/{firstname}/{lastname}/{isStaff}")
    public @ResponseBody ResponseEntity<List<User>>addNewUser (@PathVariable String firstname
            , @PathVariable String lastname, @PathVariable boolean isStaff) {
        User n = new User();
        n.setFirstname(firstname);
        n.setLastname(lastname);
        n.setAddress(" ");
        n.setCity("Białystok");
        String email = "123@";
        //TODO sprawdzanie formy e-maila, czy ma format xxxxx@xxxx.xxx
        n.setEmail(email);
        n.setIsActive(true);
        n.setPassword("admin");//TODO ustawianie hasła
        n.setPhone("123456789");
        n.setPostalCode("18-300");
        userRepository.save(n);
        /*if (isStaff) { //TODO zapisywanie pracowników
            Employee e = new Employee();
            e.setUser(n);
            employeeRepository.save(e);
        }*/
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.CREATED);
    }

    @GetMapping(path="/users")
    public @ResponseBody
    ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}