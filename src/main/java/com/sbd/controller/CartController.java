package com.sbd.controller;

import com.sbd.bookstore.repository.BookRepository;
import com.sbd.bookstore.repository.CartRepository;
import com.sbd.model.AdditionalCoverDecorator;
import com.sbd.model.BigFormatDecorator;
import com.sbd.model.Cart;
import com.sbd.model.Decorator;
import com.sbd.model.HardCoverDecorator;
import com.sbd.model.CdDecorator;

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

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/{id}")
    ResponseEntity<List<Cart>> getUserCart(@PathVariable Long id) {
        List<Cart> carts = cartRepository.findByUserId(id);
        for (Cart cart : carts) {
            for (Decorator decorator : cart.getDecorators()) {
                switch(decorator.getId().intValue()) {
                    case 1:
                    cart.getBook().setPrice(new HardCoverDecorator(cart.getBook()).getPrice());;
                    break;
                    case 2:
                    cart.getBook().setPrice(new AdditionalCoverDecorator(cart.getBook()).getPrice());;
                    break;
                    case 3:
                    cart.getBook().setPrice(new BigFormatDecorator(cart.getBook()).getPrice());;
                    break;
                    case 4:
                    cart.getBook().setPrice(new CdDecorator(cart.getBook()).getPrice());;
                    break;
                }
            }
        }

        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addBook(@RequestBody Cart cart) {
        try {
            return new ResponseEntity<>(cartRepository.save(cart), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
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
