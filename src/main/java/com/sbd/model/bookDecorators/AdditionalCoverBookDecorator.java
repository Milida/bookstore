package com.sbd.model.bookDecorators;

import com.sbd.model.BaseBook;

import java.math.BigDecimal;

public class AdditionalCoverBookDecorator extends BookDecorator {

    private static final BigDecimal ADDITIONAL_COVER_PRICE = new BigDecimal(5.99);

    public AdditionalCoverBookDecorator(BaseBook book) {
        super(book);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(ADDITIONAL_COVER_PRICE);
    }

    /*@Override
    public String getFeaturesDescription() {
        return this.book.getFeaturesDescription() + "Book with additional cover";
    }

    @Override
    public String getTitle() {
        return book.getTitle();
    }*/
}
