package com.sbd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(
        mappedBy = "publisher",
        orphanRemoval = true
    )
    private List<Book> books = new ArrayList<>();

    public Publisher() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setPublisher(this);
    }
 
    public void removeBook(Book book) {
        books.remove(book);
        book.setPublisher(null);
    }

}
