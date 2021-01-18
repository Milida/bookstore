package com.sbd.model.packing;

import java.math.BigDecimal;

public class ValentinePackingBuilder extends PackingBuilder {

    @Override
    public void setDedication(String dedication) {
        packing.setDedication("Be my valentine " + dedication + "!");
    }

    @Override
    public void setPaper() {
        packing.setPaper("Valentine paper");
    }

    @Override
    public void setPrice() {
        packing.setPrice(BigDecimal.valueOf(9));
    }
}
