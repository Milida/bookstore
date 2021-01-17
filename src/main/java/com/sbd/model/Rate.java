package com.sbd.model;

import java.math.BigDecimal;
import java.util.Observable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "rates")
@SuppressWarnings("deprecation")
public class Rate extends Observable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 127, nullable = false)
    private String currency;

    @Column(length = 127, nullable = false)
    private String symbol;

    @Column(length = 127, nullable = false)
    private BigDecimal rate;

    public Rate() {

    }

    public Rate(String currency, String symbol, BigDecimal rate) {
        this.currency = currency;
        this.symbol = symbol;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
        this.setChanged();
        this.notifyObservers(this);
    }

}
