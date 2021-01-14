package com.sbd.model;


import java.math.BigDecimal;

public class CompanyPriceStrategy implements PriceStrategy {
    @Override
    public BigDecimal calculate(BigDecimal price) {
        if (price.floatValue() > 200)
            return BigDecimal.valueOf(price.floatValue() * 0.5f);
        else
            return price;
    }
}
