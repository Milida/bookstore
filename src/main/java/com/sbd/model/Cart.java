package com.sbd.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.sbd.model.embedded.CartId;

@Entity(name = "carts")
public class Cart {
    @EmbeddedId
    private CartId id;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;
    private Cart() {}
    public CartId getId() {
        return id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public User getUser() {
        return user;
    }
    public Book getBook() {
        return book;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Cart that = (Cart) o;
        return Objects.equals(user, that.user) &&
               Objects.equals(book, that.book);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(user, book);
    }
}
