package com.sbd.controller;

import com.sbd.bookstore.repository.CartRepository;
import com.sbd.model.Book;
import com.sbd.model.Cart;
import com.sbd.model.User;
import com.sbd.payroll.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @GetMapping("/{id}")
    ResponseEntity<List<Cart>> getUserCarts(@PathVariable Long id) {
        List<Cart> carts = cartRepository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with ID = %d was not found", id)));
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addBook(@RequestBody User user, @RequestBody Book book) {
        try {
            return new ResponseEntity<>(cartRepository.save(new Cart(user, book)), HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>("Incorrect user/book ID", HttpStatus.BAD_REQUEST);
        }
    }

}
