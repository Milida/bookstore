package com.sbd.controller;

import com.sbd.bookstore.repository.OrderRepository;
import com.sbd.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Order> addOder(@RequestBody Order order) {
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.CREATED);
    }
}

