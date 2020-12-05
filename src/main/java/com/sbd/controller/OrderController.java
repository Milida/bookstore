package com.sbd.controller;

import com.sbd.bookstore.repository.OrderRepository;
import com.sbd.model.Order;
import com.sbd.payroll.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID = %d was not found", id)));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Order> addOder(@RequestBody Order order) {
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateOrder(@RequestBody Order order, @PathVariable Long id) {
        orderRepository.findById(id).map(oldOrder -> {
            BeanUtils.copyProperties(order, oldOrder, new String[] { "id" });
            return orderRepository.save(oldOrder);
        }).orElseGet(() -> orderRepository.save(order));

        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderRepository.delete(getOrder(id).getBody());
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}

