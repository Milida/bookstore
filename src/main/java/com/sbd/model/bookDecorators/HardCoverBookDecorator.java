package com.sbd.model.bookDecorators;

import com.sbd.model.BaseBook;

import java.math.BigDecimal;

public class HardCoverBookDecorator extends BookDecorator {
    private static final BigDecimal HARD_COVER_PRICE = new BigDecimal(10.99);


    public HardCoverBookDecorator(BaseBook book) {
        super(book);
    }

    public BigDecimal getPrice() {
        return super.getPrice().add(HARD_COVER_PRICE);
    }

}
