package com.sbd.controller;

import com.sbd.bookstore.repository.PublisherRepository;
import com.sbd.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping
    ResponseEntity<List<Publisher>> getPublishers() {
        return new ResponseEntity<>(publisherRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
        return new ResponseEntity<>(publisherRepository.save(publisher), HttpStatus.CREATED);
    }
}