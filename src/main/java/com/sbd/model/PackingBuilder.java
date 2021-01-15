package com.sbd.model;


import javax.persistence.*;
import java.math.BigDecimal;

public abstract class PackingBuilder {
    protected Packing packing = new Packing();

    protected String paper;

    protected BigDecimal price;

    public abstract void setDedication(String dedication);

    public abstract void setWrapping();

    public abstract void setPaper();

    public abstract void setPrice();

    public Packing getPacking() {
        return packing;
    }
}
