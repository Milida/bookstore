package com.sbd.controller;

import com.sbd.bookstore.repository.PaymentRepository;
import com.sbd.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    ResponseEntity<List<Payment>> getPayments() {
        return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Payment> addPayments(@RequestBody Payment payment) {
        return new ResponseEntity<>(paymentRepository.save(payment), HttpStatus.CREATED);
    }
}