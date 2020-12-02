package com.sbd.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sbd.model.embedded.CartId;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "carts")
public class Cart {

    @EmbeddedId
    private CartId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @Column(nullable = false)
    private Integer quantity;

    public Cart() {
    }

    public CartId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(CartId id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Cart that = (Cart) o;
        return Objects.equals(user, that.user) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book);
    }
}
