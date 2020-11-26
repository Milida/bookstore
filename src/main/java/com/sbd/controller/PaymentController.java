package com.sbd.controller;

import com.sbd.bookstore.repository.PaymentRepository;
import com.sbd.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @RequestMapping(path="/payment/add/{name}")
    public @ResponseBody ResponseEntity<List<Payment>>addNewPayment (@PathVariable String name) {
        Payment n = new Payment();
        n.setName(name);
        paymentRepository.save(n);
        return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.CREATED);
    }

    @GetMapping(path="/payments")
    public @ResponseBody
    ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.OK);
    }
}