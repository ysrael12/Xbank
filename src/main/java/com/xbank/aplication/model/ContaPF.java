package com.xbank.aplication.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PF")
public class ContaPF extends Accounts {
    protected String cpf;
    protected String nomeCompleto;
    protected String tipo = "PJ";

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

    public void addCreditCard(String cardHolderName, BigDecimal limit, String password) {
        CreditCards creditCard = new CreditCards();
        creditCard.setCardNumber();
        creditCard.setCardHolderName(cardHolderName);
        creditCard.setCreditLimit(limit);
        creditCard.setAccount(this);
        creditCard.setAvailableCredit(limit);
        creditCard.setCurrentBalance(BigDecimal.ZERO);
        creditCard.setActive(true);
        // Define a data de expiração para 3 anos a partir de agora
        Date date = new Date(10); // Ano, mês (0-11), dia
        creditCard.setExpirationDate();
        creditCard.setCvv();
        creditCard.setPassword(password);
        creditCard.setCardType("VISA");
        
        
        this.getCreditCards().add(creditCard);
        
    }

    public String getTipo(){
		return this.tipo;
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