package com.sbd.model.packing;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "packings")
public class Packing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dedication;
    private String paper;
    private BigDecimal price;

    public Packing(){}

    public Packing(String dedication, String paper, BigDecimal price){
        this.dedication = dedication;
        this.paper = paper;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDedication() {
        return dedication;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPaper() {
        return paper;
    }

    public void setDedication(String dedication) {
        this.dedication = dedication;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
