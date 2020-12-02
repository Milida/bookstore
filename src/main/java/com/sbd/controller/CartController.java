package com.sbd.controller;

import com.sbd.bookstore.repository.CartRepository;
import com.sbd.model.Cart;
import com.sbd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @GetMapping
    ResponseEntity<List<Cart>> getUserCarts(@RequestBody User user) {
        System.out.println(user.getId());
        return new ResponseEntity<>(cartRepository.findByUserId(user.getId()), HttpStatus.OK);
    }
}
