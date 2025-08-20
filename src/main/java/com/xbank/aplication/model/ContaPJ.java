package com.xbank.aplication.model;
import lombok.Data;
import java.util.List;

import jakarta.persistence.Entity;

@Data
@Entity
public class ContaPJ extends Accounts implements AccountDetails {
	
	private String cnpj;
	private String companyName;
	
	
	public ContaPJ() {
		
	}
	
	public ContaPJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public ContaPJ(User owner, String accountNumber, String agency, String bankName, CreditCards creditCard, String cnpj, String companyName) {
		super(owner, accountNumber, agency, bankName, creditCard);
		this.cnpj = cnpj;
		this.companyName = companyName;
	}

	@Override
	public List<CreditCards> getCreditCards() {
		return this.getCreditCards();
	}
	
	@Override
	public String toString() {
		return "ContaPJ [cnpj=" + cnpj + ", getId()=" + this.id + ", getAccountNumber()=" + this.accountNumber
				+ ", getAgency()=" + this.agency + ", getBankName()=" + this.bankName + ", getBalance()=" + this.balance
				+ ", getOwner()=" + this.owner + ", getCreditCards()=" + this.creditCards+ "getCompanyName()=" + this.companyName + "]";
	}
	
}
