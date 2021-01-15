package com.sbd.model;

import java.math.BigDecimal;

public class ValentinePackingBuilder extends PackingBuilder{
    protected String paper = "Valentine paper";
    protected BigDecimal price = BigDecimal.valueOf(9);

    @Override
    public void setDedication(String dedication) {
        packing.setDedication("Be my valentine " + dedication + "!");
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
