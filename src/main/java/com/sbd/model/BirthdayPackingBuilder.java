package com.sbd.model;

import java.math.BigDecimal;

public class BirthdayPackingBuilder extends PackingBuilder{
    protected String paper = "Birthday paper";
    protected BigDecimal price = BigDecimal.valueOf(12);

    @Override
    public void setDedication(String dedication) {
        packing.setDedication("Happy birthday " + dedication + "!");
    }

    @Override
    public void setWrapping() {

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
