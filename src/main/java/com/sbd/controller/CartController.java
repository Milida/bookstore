package com.sbd.controller;

import com.sbd.bookstore.repository.CartRepository;
import com.sbd.model.Cart;

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
    ResponseEntity<List<Cart>> getUserCart(@PathVariable Long id) {
        List<Cart> carts = cartRepository.findByUserId(id);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addBook(@RequestBody Cart cart) {
        System.out.println(cart);
        try {
            return new ResponseEntity<>(cartRepository.save(cart), HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>("Incorrect user/book ID", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/remove")
    ResponseEntity<?> removeItem(@RequestBody Cart cart) {
        cartRepository.delete(cart);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);

    }

    @DeleteMapping("/remove/{id}")
    ResponseEntity<?> removeUserItems(@PathVariable Long id) {
        List<Cart> carts = cartRepository.findByUserId(id);
        for (Cart cart: carts) {
            System.out.println(cart.getUser().getFirstname());
            cartRepository.delete(cart);
        }
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);

    }



}
