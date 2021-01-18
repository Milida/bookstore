package com.sbd.model.bookDecorators;

import com.sbd.model.BaseBook;

import java.math.BigDecimal;

public class BigFormatDecorator extends Decorator {
    private static final BigDecimal BIG_FORMAT_PRICE = new BigDecimal(24.99);

    public BigFormatDecorator(BaseBook book) {
        super(book);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(BIG_FORMAT_PRICE);
    }

   /* @Override
    public String getFeaturesDescription() {
        return this.book.getFeaturesDescription() + "Book written in big format font";
    }

    @Override
    public String getTitle() {
        return this.book.getTitle();
    }*/
}
