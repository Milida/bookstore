package com.sbd.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "bookId")
    private Integer bookId;
    
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private Integer quantity;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisherId")
    private Publisher publisherId;

    @Column(name = "description")
    private String description;

    public Book() {
    }
    public Integer getBookId() {
        return bookId;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public Publisher getPublisherId() {
        return publisherId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public String getTitle() {
        return title;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setPublisherId(Publisher publisherId) {
        this.publisherId = publisherId;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    

}
