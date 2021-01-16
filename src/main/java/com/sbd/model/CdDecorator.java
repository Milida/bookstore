package com.sbd.model;

import java.math.BigDecimal;

class CdDecorator extends Decorator {
    private static final BigDecimal ADD_CD_PRICE = new BigDecimal(19.99);

    public CdDecorator(BaseBook book) {
        super(book);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(ADD_CD_PRICE);
    }

    /*@Override
    public String getFeaturesDescription() {
        return this.book.getFeaturesDescription() + "Book with book's CD";
    }

    @Override
    public String getTitle() {
        return this.book.getTitle();
    }*/
}
