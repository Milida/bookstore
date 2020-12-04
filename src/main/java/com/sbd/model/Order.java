package com.sbd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderStatus status;

    @JsonIgnoreProperties({"orders", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;

    @JsonIgnoreProperties({"orders", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Shipment shipment;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Date date = new Date();

    @OneToMany(mappedBy = "order")
    @JsonIgnoreProperties("order")
    private List<OrderBook> orderBook;

    public Order(){}

    public Order(User user, OrderStatus status, Payment payment, Shipment shipment, BigDecimal price, Date date) {
        this.user = user;
        this.status = status;
        this.payment = payment;
        this.shipment = shipment;
        this.price = price;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnoreProperties({"orders", "hibernateLazyInitializer"})
    public User getUser() {
        return user;
    }

    @JsonIgnoreProperties({"orders", "hibernateLazyInitializer"})
    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public Payment getPayment() {
        return payment;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;
        return id != null && id.equals(((Order) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public List<OrderBook> getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(List<OrderBook> orderBook) {
        this.orderBook = orderBook;
    }

    public void addOrderBook(OrderBook orderBook) {
        this.orderBook.add(orderBook);
    }
}
