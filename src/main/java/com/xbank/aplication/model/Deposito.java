package com.xbank.aplication.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;

@Entity
public class Deposito extends Transfer {

    public Deposito() {}

    public Deposito(Accounts destination, BigDecimal amount) {
        super(null, destination, amount);
    }

    @Override
    public Accounts getSourceAccount() {
        return null;
    }

    @Override
    public void setSourceAccount(Accounts sourceAccount) {
        throw new UnsupportedOperationException("Depósito não possui conta de origem.");
    }

    public String getType() {
        return "DEPOSITO";
    }
}
