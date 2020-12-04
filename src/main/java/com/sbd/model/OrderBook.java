package com.sbd.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sbd.model.embedded.OrderBookId;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "ordersBooks")
public class OrderBook {

    @EmbeddedId
    private OrderBookId id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    @MapsId("bookId")
    private Book book;

    public OrderBook() {
    }

    public OrderBookId getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Book getBook() {
        return book;
    }

    public Order getOrder() {
        return order;
    }

    public void setId(OrderBookId id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderBook that = (OrderBook) o;
        return Objects.equals(order, that.order) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, book);
    }

}
