package com.sbd.model.bookDecorators;

import com.sbd.model.BaseBook;

import java.math.BigDecimal;

public class CdBookDecorator extends BookDecorator {
    private static final BigDecimal ADD_CD_PRICE = new BigDecimal(19.99);

    public CdBookDecorator(BaseBook book) {
        super(book);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(ADD_CD_PRICE);
    }

    // @Override
    // public String getFeaturesDescription() {
    //     return this.book.getFeaturesDescription() + "Book with book's CD";
    // }

}
