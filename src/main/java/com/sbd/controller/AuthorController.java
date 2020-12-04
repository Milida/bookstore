package com.sbd.controller;

import com.sbd.bookstore.repository.AuthorRepository;
import com.sbd.model.Author;
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
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    ResponseEntity<List<Author>> getAuthors() {
        return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with ID = %d was not found", id)));
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        if (authorRepository.existsByFirstnameAndLastname(author.getFirstName(), author.getLastName()))
            throw new ConflictException(String.format("Author '%s %s' already exists", author.getFirstName(), author.getLastName()));

        return new ResponseEntity<>(authorRepository.save(author), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateAuthor(@RequestBody Author author, @PathVariable Long id) {

        authorRepository.findById(id).map(oldAuthor -> {
            BeanUtils.copyProperties(author, oldAuthor, new String[] { "id" });
            return authorRepository.save(oldAuthor);
        }).orElseGet(() -> authorRepository.save(author));

        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> removeAuthor(@PathVariable Long id) {
        Author author = getAuthor(id).getBody();
        if(author.getBooks().isEmpty())
        {
            authorRepository.delete(getAuthor(id).getBody());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } else {
            throw new ConflictException("Cannot remove author with assigned books!");
        }
    }
}