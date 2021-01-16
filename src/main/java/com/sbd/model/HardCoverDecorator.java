package com.sbd.model;

import java.math.BigDecimal;

public class HardCoverDecorator extends Decorator {
    private static final BigDecimal HARD_COVER_PRICE = new BigDecimal(10.99);


    public HardCoverDecorator(BaseBook book) {
        super(book);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(HARD_COVER_PRICE);
    }

    /*@Override
    public String getFeaturesDescription() {
        return this.book.getFeaturesDescription() + "Book in hard cover";
    }

    @Override
    public String getTitle() {
        return this.book.getTitle();
    }*/
}
