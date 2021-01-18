package com.sbd.model.packing;

import java.math.BigDecimal;

public abstract class PackingBuilder {
    protected Packing packing = new Packing();

    /**
     * Sets dedication of the packing
     * @param dedication name of person/team you want to dedicate your packing
     */
    public abstract void setDedication(String dedication);

    public abstract void setPaper();

    public abstract void setPrice();


    /**
     * Used to get built packing
     * @return ready packing
     */
    public Packing getPacking() {
        return packing;
    }
}
