package com.xbank.aplication.model;

import java.math.BigDecimal;


import jakarta.persistence.Entity;

@Entity
public class Deposito extends Transfer {
	private String tipo;
   

    public Deposito(Accounts source, Accounts destination, BigDecimal amount) {
    	super(source, destination, amount);
    }
    

    @Override
    public void setSourceAccount(Accounts sourceAccount) {
        throw new UnsupportedOperationException("Depósito não possui conta de origem.");
    }
    
    @Override
    public String getType() {
        return this.tipo;
    }
}
