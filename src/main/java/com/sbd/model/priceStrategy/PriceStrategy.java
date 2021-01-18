package com.sbd.model.priceStrategy;

import java.math.BigDecimal;

public interface PriceStrategy {
    BigDecimal calculate(BigDecimal price);
}
