package com.sbd.controller;

import com.sbd.bookstore.repository.AuthorRepository;
import com.sbd.model.Author;
import com.sbd.payroll.ConflictException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    ResponseEntity<List<Author>> getAuthors() {
        return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        if (authorRepository.existsByFirstnameAndLastname(author.getFirstName(), author.getLastName()))
            throw new ConflictException(String.format("Author '%s %s' already exists", author.getFirstName(), author.getLastName()));

        return new ResponseEntity<>(authorRepository.save(author), HttpStatus.CREATED);
    }
}