package com.sbd.controller;

import com.sbd.bookstore.repository.PublisherRepository;
import com.sbd.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

    @RequestMapping(path="/publisher/add/{name}")
    public @ResponseBody ResponseEntity<List<Publisher>>addNewPublisher (@PathVariable String name) {
        Publisher n = new Publisher();
        n.setName(name);
        publisherRepository.save(n);
        return new ResponseEntity<>(publisherRepository.findAll(), HttpStatus.CREATED);
    }

    @GetMapping(path="/publishers")
    public @ResponseBody
    ResponseEntity<List<Publisher>> getAllPublishers() {
        return new ResponseEntity<>(publisherRepository.findAll(), HttpStatus.OK);
    }
}