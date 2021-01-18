package com.sbd.model.packing;

import java.math.BigDecimal;

public class BirthdayPackingBuilder extends PackingBuilder {
    protected String paper = "Birthday paper"; //Paper name
    protected BigDecimal price = BigDecimal.valueOf(12); //price of packing

    @Override
    public void setDedication(String dedication) {
        packing.setDedication("Happy birthday " + dedication + "!");
    }

    @Override
    public void setPaper() {
        packing.setPaper(paper);
    }

    @Override
    public void setPrice() {
        packing.setPrice(price);
    }
}
