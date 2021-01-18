package com.sbd.model.packing;

import java.math.BigDecimal;

public class ChristmasPackingBuilder extends PackingBuilder {
    protected String paper = "Christmas paper";
    protected BigDecimal price = BigDecimal.valueOf(10);

    @Override
    public void setDedication(String dedication) {
        packing.setDedication("Merry Christmas " + dedication + "!");
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
