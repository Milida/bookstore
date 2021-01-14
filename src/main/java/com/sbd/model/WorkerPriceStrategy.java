package com.sbd.model;


import java.math.BigDecimal;

public class WorkerPriceStrategy implements PriceStrategy {
    @Override
    public BigDecimal calculate(BigDecimal price) {
        return BigDecimal.valueOf(price.floatValue() * 0.5f);
    }
}
