package com.sbd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sbd.bookstore.repository.BookRepository;
import com.sbd.controller.BookController;


@Entity(name = "bookstores")
@SuppressWarnings("deprecation")
public class Bookstore implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 127, nullable = false)
    private String name;

    @JsonIgnoreProperties("bookstore")
    @OneToMany(mappedBy = "bookstore", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    @JsonIgnore()
    @Transient
    private static Bookstore INSTANCE;

    @JsonIgnore()
    @Transient
    public BookRepository bookRepository;

    private Bookstore() {
    }

    public static Bookstore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Bookstore();
        }
        return INSTANCE;
    }

    @Override
    public void update(Observable o, Object arg) {
        Rate rate = (Rate) arg;
        for (Book book : books) {
            book.setPriceEur(book.getPrice().multiply(rate.getRate()));
            
        }
        bookRepository.saveAll(books);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setBookstore(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setBookstore(null);
    }


}
