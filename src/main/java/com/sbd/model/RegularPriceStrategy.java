package com.sbd.model;

import java.math.BigDecimal;

public class RegularPriceStrategy implements PriceStrategy {
    @Override
    public BigDecimal calculate(BigDecimal price) {
        return price;
    }
}
