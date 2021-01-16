package com.sbd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

//@Entity(name="decorators")
public abstract class Decorator implements BaseBook {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    */
    private BaseBook book;
    public Decorator(BaseBook book){this.book = book;}

    public Decorator() {
    }

    @Override
    public BigDecimal getPrice(){ return this.book.getPrice();}

  /*  @Override
    public void setPrice(BigDecimal price) {
        this.book.setPrice(price);
    }*/

    //public String getFeaturesDescription(){return this.book.getFeaturesDescription();}

    //public String getTitle(){return this.book.getTitle();}


}
