package com.sbd.model.packing;

import java.math.BigDecimal;

public class ChristmasPackingBuilder extends PackingBuilder {
    @Override
    public void setDedication(String dedication) {
        packing.setDedication("Merry Christmas " + dedication + "!");
    }

    @Override
    public void setPaper() {
        packing.setPaper("Christmas paper");
    }

    @Override
    public void setPrice() {
        packing.setPrice(BigDecimal.valueOf(10));
    }
}
