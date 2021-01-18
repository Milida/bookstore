package com.sbd.model.packing;

import java.math.BigDecimal;

public class BirthdayPackingBuilder extends PackingBuilder {

    @Override
    public void setDedication(String dedication) {
        packing.setDedication("Happy birthday " + dedication + "!");
    }

    @Override
    public void setPaper() {
        packing.setPaper("Birthday paper");
    }

    @Override
    public void setPrice() {
        packing.setPrice(BigDecimal.valueOf(12));
    }
}
