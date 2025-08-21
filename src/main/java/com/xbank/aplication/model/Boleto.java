package com.xbank.aplication.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;

@Entity
public class Boleto extends Transfer {

    public Boleto() {}

    public Boleto(Accounts source, BigDecimal amount) {
        super(source, null, amount);
    }

    @Override
    public Accounts getDestinationAccount() {
        return null;
    }

    @Override
    public void setDestinationAccount(Accounts destinationAccount) {
        throw new UnsupportedOperationException("Boleto não possui conta de destino.");
    }

    @Override
    public String getType() {
        return "BOLETO";
    }
}
