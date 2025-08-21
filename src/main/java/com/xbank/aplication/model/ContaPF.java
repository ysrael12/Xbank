package com.xbank.aplication.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PF")
public class ContaPF extends Accounts {
    private String cpf;
    private String nomeCompleto;

    public String getCpfTitular() {
        return cpf;
    }

    public void setCpfTitular(String cpf) {
        this.cpf = cpf;
    }

    public void addCreditCard(String cardNumber, String cardHolderName, BigDecimal limit) {
        CreditCards creditCard = new CreditCards();
        creditCard.setCardNumber(cardNumber);
        creditCard.setCardHolderName(cardHolderName);
        creditCard.setCreditLimit(limit);
        this.getCreditCards().add(creditCard);
        creditCard.setAccount(this);
    }
    @Override
    public String toString() {
    return "ContaPF{" +
            "cpf='" + cpf + '\'' +
            ", nomeCompleto='" + nomeCompleto + '\'' +
            ", id=" + getId() +
            ", balance=" + getBalance() +
            '}';
}

}