package com.xbank.aplication.model;
import lombok.Data;
import java.util.List;
import jakarta.persistence.Entity;
@Data
@Entity
public class ContaPF extends Accounts implements AccountDetails {
	
	private String cpf;
	
	public ContaPF() {
		
	}
	
	public ContaPF(String cpf) {
		this.cpf = cpf;
	}

	public ContaPF(User owner, String accountNumber, String agency, String bankName, CreditCards creditCard, String cpf) {
		super(owner, accountNumber, agency, bankName, creditCard);
		this.cpf = cpf;
	}

	@Override
	public List<CreditCards> getCreditCards() {
		return this.getCreditCards();
	}
	
	@Override
	public String toString() {
		return "ContaPF [cpf=" + this.cpf + ", getId()=" + this.id + ", getAccountNumber()=" + this.accountNumber
				+ ", getAgency()=" + this.agency + ", getBankName()=" + this.bankName + ", getBalance()=" + this.balance
				+ ", getOwner()=" + this.owner + ", getCreditCards()=" + this.creditCards+ "]";
	}
	
}
