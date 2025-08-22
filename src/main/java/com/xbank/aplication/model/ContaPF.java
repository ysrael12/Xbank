package com.xbank.aplication.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PF")
public class ContaPF extends Accounts {
    protected String cpf;
    protected String nomeCompleto;

    public void setNomeCompleto(String nomeCompleto){
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeCompleto(){
        return this.nomeCompleto;
    }

    public String getCpfTitular() {
        return cpf;
    }

    public void setCpfTitular(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String cpf){
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

    public String getTipo(){
        return "PF";
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