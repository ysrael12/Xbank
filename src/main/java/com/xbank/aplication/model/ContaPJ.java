package com.xbank.aplication.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PJ")
public class ContaPJ extends Accounts {
    private String cnpj;
    private String nomeEmpresa;

    public String getcnpj() {
        return cnpj;
    }

    public void setcnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
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
		return "ContaPJ{" +
				"cnpj='" + cnpj + '\'' +
				", nomeEmpresa='" + nomeEmpresa + '\'' +
				", id=" + getId() +
				", balance=" + getBalance() +
				'}';
		
	}
}