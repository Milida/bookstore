package com.sbd.controller;

import com.sbd.bookstore.repository.OrderStatusRepository;
import com.sbd.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderstatuses")
public class OrderStatusController {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @GetMapping
    ResponseEntity<List<OrderStatus>> getOrderStatuses() {
        return new ResponseEntity<>(orderStatusRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<OrderStatus> addOrderStatus(@RequestBody OrderStatus orderStatus) {
        return new ResponseEntity<>(orderStatusRepository.save(orderStatus), HttpStatus.CREATED);
    }
}