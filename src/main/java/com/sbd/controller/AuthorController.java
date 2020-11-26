package com.sbd.controller;

import com.sbd.bookstore.repository.AuthorRepository;
import com.sbd.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(path="/author/add/{firstname}/{lastname}")
    public @ResponseBody ResponseEntity<List<Author>>addNewAuthor (@PathVariable String firstname
            , @PathVariable String lastname) {
        Author n = new Author();
        n.setFirstName(firstname);
        n.setLastName(lastname);
        authorRepository.save(n);
        return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.CREATED);
    }

    @GetMapping(path="/authors")
    public @ResponseBody
    ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.OK);
    }
}