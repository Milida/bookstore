package com.sbd.controller;

import java.util.List;

import com.sbd.bookstore.repository.RateRepository;
import com.sbd.model.Bookstore;
import com.sbd.model.Rate;
import com.sbd.payroll.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rates")
@SuppressWarnings("deprecation")
public class RateController {
    @Autowired
    private RateRepository rateRepository;

    @GetMapping
    ResponseEntity<List<Rate>> getRates() {
        return new ResponseEntity<>(rateRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    ResponseEntity<Rate> getRate(@PathVariable Long id) {
        Rate rate = rateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Rate with ID = %d was not found", id)));
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<List<Rate>> setRate(@RequestBody List<Rate> rates) {

        for (Rate rate : rates) {
            
            rate.addObserver(Bookstore.getInstance());
            rate.setRate(rate.getRate());
            rateRepository.save(rate);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
