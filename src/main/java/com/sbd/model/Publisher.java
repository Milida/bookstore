package com.sbd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "publisherId")
    private Integer publisherId;
    @Column(name = "name")
    private String name;

    public Publisher() {
    }

    public String getName() {
        return name;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }
}
