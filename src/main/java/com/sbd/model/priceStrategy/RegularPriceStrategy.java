package com.sbd.model.priceStrategy;

import java.math.BigDecimal;
import java.util.Calendar;

public class RegularPriceStrategy implements PriceStrategy {
    @Override
    public BigDecimal calculate(BigDecimal price) {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (price.floatValue() > 150 && dayOfWeek == Calendar.SUNDAY)
            return BigDecimal.valueOf(price.floatValue() * 0.98f);
        return price;
    }
}
