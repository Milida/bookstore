package com.sbd.controller;

import com.sbd.bookstore.repository.AuthorRepository;
import com.sbd.bookstore.repository.BookRepository;
import com.sbd.bookstore.repository.CategoryRepository;
import com.sbd.bookstore.repository.PublisherRepository;
import com.sbd.model.Author;
import com.sbd.model.Book;
import com.sbd.model.Category;
import com.sbd.model.Publisher;
import com.sbd.payroll.NotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    ResponseEntity<List<Book>> getBooks(
        @RequestParam(defaultValue = "") String title) {
        return new ResponseEntity<>(bookRepository.findByTitleContaining(title), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with ID = %d was not found", id)));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Book> addBook(@RequestBody Book book) {
        if (book.getPublisher().getId() != null){
            Optional<Publisher> publisher = publisherRepository.findById(book.getPublisher().getId());
            publisher.ifPresent(book::setPublisher);
        }
        if (book.getCategories() != null) {
            List<Category> categories = book.getCategories();
            book.setCategories(new ArrayList<>());
            for (Category category: categories) {
                if (category.getId() != null){
                    Optional<Category> cat = categoryRepository.findById(category.getId());
                    cat.ifPresent(book::addCategory);
                    if (cat.isEmpty()) {
                        throw new NotFoundException("Tried to add book with not existing category");
                    }
                } else {
                    throw new NotFoundException("Tried to add book with not existing category");
                }
            }
        }
        if (book.getAuthors() != null) {
            List<Author> authors = book.getAuthors();
            book.setAuthors(new ArrayList<>());
            for (Author author: authors) {
                if (author.getId() != null){
                    Optional<Author> auth = authorRepository.findById(author.getId());
                    auth.ifPresent(book::addAuthor);
                    if (auth.isEmpty()) {
                        throw new NotFoundException("Tried to add book with not existing author");
                    }
                } else {
                    throw new NotFoundException("Tried to add book with not existing author");
                }
            }
        }
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long id) {

        bookRepository.findById(id).map(oldBook -> {  
            BeanUtils.copyProperties(book, oldBook, new String[]{"id"});
            return bookRepository.save(oldBook);
        }).orElseGet(() -> {
            return bookRepository.save(book);
        });
        
        return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookRepository.delete(getBook(id).getBody());
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}