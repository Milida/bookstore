package com.sbd.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shipment shipment;

    private BigDecimal price;
    private Date date;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Payment getPayment() {
        return payment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order )) return false;
        return id != null && id.equals(((Order) o).getId());
    }
 
    @Override
    public int hashCode() {
        return 32;
    }

}
