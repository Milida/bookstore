package com.sbd.controller;

import com.sbd.bookstore.repository.OrderRepository;
import com.sbd.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping()
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(path="/order/add")
    public @ResponseBody
    ResponseEntity<List<Order>> addNewOrder() { //@PathVariable String title
        Order n = new Order();
        //n.setUser()
        //n.setStatus();
        //n.setPayment();
        //n.setShipment();
        BigDecimal price = BigDecimal.valueOf(0);
        //wyliczenie ceny zamiówienia
        n.setPrice(price);
        n.setDate(Calendar.getInstance().getTime()); //ustawianie czasu

        orderRepository.save(n);
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.CREATED);
    }

    @GetMapping(path="/orders")
    public @ResponseBody
    ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }
}

