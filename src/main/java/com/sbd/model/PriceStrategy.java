package com.sbd.model;

import java.math.BigDecimal;

public interface PriceStrategy {
    BigDecimal calculate(BigDecimal price);
}
