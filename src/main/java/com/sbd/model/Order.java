package com.sbd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sbd.model.packing.*;
import com.sbd.model.priceStrategy.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("role")
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

    @Transient
    private PriceStrategy priceStrategy;

    @OneToOne
    private Packing packing;

    @Transient
    private PackingBuilder packingBuilder;

    @Transient
    private String typeOfPacking; //which type of packing builder should we use

    @Transient
    private String dedication; //name of person/team you want to dedicate your packing

    public Order(){
        this.priceStrategy = new RegularPriceStrategy();
    }

    public Order(User user, OrderStatus status, Payment payment, Shipment shipment, BigDecimal price, Date date) {
        this.user = user;
        this.status = status;
        this.payment = payment;
        this.shipment = shipment;
        this.date = date;
        if (user.getRole() != null) { //if user has assigned role we check which one strategy we should use
            if ("Student".equals(user.getRole().getName())) {
                this.priceStrategy = new StudentPriceStrategy();
            } else if ("Employee".equals(user.getRole().getName())) {
                this.priceStrategy = new WorkerPriceStrategy();
            } else if ("Company".equals(user.getRole().getName())) {
                this.priceStrategy = new CompanyPriceStrategy();
            } else {
                this.priceStrategy = new RegularPriceStrategy();
            }
        } else {
            this.priceStrategy = new RegularPriceStrategy();
        }
        this.price = price;
    }

    public String getTypeOfPacking() {
        return typeOfPacking;
    }

    public void setTypeOfPacking(String typeOfPacking) {
        this.typeOfPacking = typeOfPacking;
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

    public BigDecimal getPrice() { return price; }

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

    public void setUser(User user) { //when we change user, we should change the price strategy we use
        this.user = user;
        if (user.getRole() != null) {
           if ("Student".equals(user.getRole().getName())) {
               this.priceStrategy = new StudentPriceStrategy();
           } else if ("Employee".equals(user.getRole().getName())) {
               this.priceStrategy = new WorkerPriceStrategy();
           } else if ("Company".equals(user.getRole().getName())) {
               this.priceStrategy = new CompanyPriceStrategy();
           } else {
               this.priceStrategy = new RegularPriceStrategy();
           }
        } else {
            this.priceStrategy = new RegularPriceStrategy();
        }
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        if (this.priceStrategy != null) {
            this.price = priceStrategy.calculate(price);
        }
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

    public void setPriceStrategy(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
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

    public Packing getPacking() {
        return packing;
    }

    public void setPacking(Packing packing) {
        this.packing = packing;
    }

    public String getDedication() {
        return dedication;
    }

    public void setDedication(String dedication) {
        this.dedication = dedication;
    }

    /**
     * Creating packing of the order
     * @param packingType type of the packing / occasion (i.e. "Christmas", "Valentines")
     */
    public void createPacking(String packingType) {
        if("Christmas".equals(packingType)) { //choosing right builder
            this.packingBuilder = new ChristmasPackingBuilder();
        } else if ("Valentines".equals(packingType)){
            this.packingBuilder = new ValentinePackingBuilder();
        } else if ("Birthday".equals(packingType)){
            this.packingBuilder = new BirthdayPackingBuilder();
        } else {
            return;
        }
        //scenario
        packingBuilder.setDedication(dedication);
        packingBuilder.setPaper();
        packingBuilder.setPrice();
        //scenario
        this.packing = packingBuilder.getPacking();//getting built packing
    }
}
