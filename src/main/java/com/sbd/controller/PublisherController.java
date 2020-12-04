package com.sbd.controller;

import com.sbd.bookstore.repository.PublisherRepository;
import com.sbd.model.Publisher;
import com.sbd.payroll.ConflictException;

import com.sbd.payroll.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping
    ResponseEntity<List<Publisher>> getPublishers() {
        return new ResponseEntity<>(publisherRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Publisher> getPublisher(@PathVariable Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Publisher with ID = %d was not found", id)));
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
        if (publisherRepository.existsByName(publisher.getName()))
            throw new ConflictException(String.format("Publisher with name '%s' already exists", publisher.getName()));

        return new ResponseEntity<>(publisherRepository.save(publisher), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updatePublisher(@RequestBody Publisher publisher, @PathVariable Long id) {
        System.out.println(publisher.toString());
        publisherRepository.findById(id).map(oldPublisher -> {
            BeanUtils.copyProperties(publisher, oldPublisher, new String[] { "id" });
            return publisherRepository.save(oldPublisher);
        }).orElseGet(() -> publisherRepository.save(publisher));

        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePublisher(@PathVariable Long id) {
        Publisher publisher = getPublisher(id).getBody();
        if (publisher.getBooks().isEmpty())
        {
            publisherRepository.delete(getPublisher(id).getBody());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } else {
            throw new ConflictException("Cannot remove publisher with assigned books!");
        }

    }
}