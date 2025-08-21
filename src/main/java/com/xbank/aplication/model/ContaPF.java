package com.xbank.aplication.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PF")
public class ContaPF extends Accounts {
    private String cpfTitular;
    private String nomeCompleto;

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
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
            "cpf='" + cpfTitular + '\'' +
            ", nomeCompleto='" + nomeCompleto + '\'' +
            ", id=" + getId() +
            ", balance=" + getBalance() +
            '}';
}

}