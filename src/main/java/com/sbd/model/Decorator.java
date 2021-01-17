package com.sbd.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="decorators")
public class Decorator implements BaseBook {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @ManyToMany(mappedBy = "decorators")
    @JsonIgnoreProperties("decorators")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Cart> carts = new ArrayList<>();

    @Transient
    @JsonIgnore
    private BaseBook book;
    
    public Decorator() {
    }
    public Decorator(String name){this.name = name;}
    public Decorator(BaseBook book){this.book = book;}

    public Long getId() {
      return id;
    }
    public String getName() {
      return name;
    }
    public List<Cart> getCarts() {
      return carts;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public void setName(String name) {
      this.name = name;
    }
    public void setBook(BaseBook book) {
      this.book = book;
    }

    @Override
    @JsonIgnore
    public BigDecimal getPrice(){ return this.book.getPrice();}

  /*  @Override
    public void setPrice(BigDecimal price) {
        this.book.setPrice(price);
    }*/

    //public String getFeaturesDescription(){return this.book.getFeaturesDescription();}

    //public String getTitle(){return this.book.getTitle();}


}
