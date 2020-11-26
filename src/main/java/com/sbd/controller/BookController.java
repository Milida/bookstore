package com.sbd.controller;

import com.sbd.bookstore.repository.BookRepository;
import com.sbd.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping()
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(path="/book/add/{title}/{price}/{quantity}/{description}")
    public @ResponseBody
    ResponseEntity<List<Book>> addNewBook(@PathVariable String title
            , @PathVariable BigDecimal price, @PathVariable Integer quantity, @PathVariable String description) {
        Book n = new Book();
        n.setTitle(title);
        n.setPrice(price);
        n.setQuantity(quantity);
        n.setDescription(description);
        //n.setPublisher()
        //n.addCategory()
        //n.addAuthor()
        bookRepository.save(n);
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.CREATED);
    }

    @GetMapping(path="/books")
    public @ResponseBody
    ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }
}
