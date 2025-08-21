package com.xbank.aplication.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Deposito extends Transfer {

    public Deposito() {}

    public Deposito(Accounts source, Accounts destination, BigDecimal amount) {
        this.sourceAccount = source;
        this.destinationAccount = destination;
        this.amount = amount;
        this.transferDate = new Date();
    }
    

    @Override
    public void setSourceAccount(Accounts sourceAccount) {
        throw new UnsupportedOperationException("Depósito não possui conta de origem.");
    }
    
    @Override
    public String getType() {
        return "DEPOSITO";
    }
}
