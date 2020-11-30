package com.sbd.controller;

import com.sbd.bookstore.repository.BookRepository;
import com.sbd.model.Book;
import com.sbd.payroll.NotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    ResponseEntity<List<Book>> getBooks(@RequestParam(defaultValue = "") String title) {
        return new ResponseEntity<>(bookRepository.findByTitleContaining(title), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with ID = %d was not found", id)));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>("Incorrect publisher/author/category", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long id) {

        bookRepository.findById(id).map(oldBook -> {
            BeanUtils.copyProperties(book, oldBook, new String[] { "id" });
            return bookRepository.save(oldBook);
        }).orElseGet(() -> {
            return bookRepository.save(book);
        });

        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookRepository.delete(getBook(id).getBody());
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}