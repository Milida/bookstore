package com.sbd.model;

import java.math.BigDecimal;

public abstract class PackingBuilder {
    protected Packing packing = new Packing();

    protected String paper;

    protected BigDecimal price;

    public abstract void setDedication(String dedication);

    public abstract void setPaper();

    public abstract void setPrice();

    public Packing getPacking() {
        return packing;
    }
}
