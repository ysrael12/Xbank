package com.xbank.aplication.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;

@Entity
public class Ted extends Transfer {

    public Ted() {}

    public Ted(Accounts source, Accounts destination, BigDecimal amount) {
        super(source, destination, amount);
    }

    @Override
    public void setAmount(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("1000")) < 0) {
            throw new IllegalArgumentException("TED deve ser acima de R$1000.");
        }
        super.setAmount(amount);
    }

    @Override
    public String getType() {
        return "TED";
    }
}
