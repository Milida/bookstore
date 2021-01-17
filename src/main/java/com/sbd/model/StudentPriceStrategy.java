package com.sbd.model;

import java.math.BigDecimal;
import java.util.Calendar;

public class StudentPriceStrategy implements PriceStrategy{
    @Override
    public BigDecimal calculate(BigDecimal price) {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.THURSDAY) { //W czwartki studenci mają taniej o połowę
            return BigDecimal.valueOf(price.floatValue() * 0.5f);
        }
        return BigDecimal.valueOf(price.floatValue() * 0.7f); // w pozostałe dni tygodnia 30% taniej
    }
}
