package com.xbank.aplication.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;

@Entity
public class Pix extends Transfer {
	private String tipo;
  
    public Pix(Accounts source, Accounts destination, BigDecimal amount) {
        super(source, destination, amount);
    }

    @Override
    public void setAmount(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("5000")) > 0) {
            throw new IllegalArgumentException("PIX não pode exceder R$5000.");
        }
        super.setAmount(amount);
    }

    @Override
    public String getType() {
        return this.tipo;
    }
}
