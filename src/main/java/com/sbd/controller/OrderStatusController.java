package com.sbd.controller;

import com.sbd.bookstore.repository.OrderStatusRepository;
import com.sbd.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class OrderStatusController {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @RequestMapping(path="/orderStatus/add/{name}")
    public @ResponseBody ResponseEntity<List<OrderStatus>>addNewOrderStatus (@PathVariable String name) {
        OrderStatus n = new OrderStatus();
        n.setName(name);
        orderStatusRepository.save(n);
        return new ResponseEntity<>(orderStatusRepository.findAll(), HttpStatus.CREATED);
    }

    @GetMapping(path="/orderStatuses")
    public @ResponseBody
    ResponseEntity<List<OrderStatus>> getAllOrderStatuses() {
        return new ResponseEntity<>(orderStatusRepository.findAll(), HttpStatus.OK);
    }
}