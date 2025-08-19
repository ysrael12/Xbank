package com.xbank.aplication.model;
import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.UUID;
import java.util.Random;

import lombok.Data;

@Data
public class Accounts implements AccountDetails {
	@Id
	protected Long id;
	protected User owner; 
	protected String accountNumber;
	protected String agency;
	protected String bankName;
	
	// Balance 
	protected BigDecimal balance = BigDecimal.ZERO;
	
	// Credit Cards 
	List<CreditCards> creditCards;
	
	
	public Accounts() {
		setId();
	}
	
	public Accounts(User owner, String accountNumber, String agency, String bankName, CreditCards creditCard) {
		setId();
		setCreditCards(creditCard);
		generateAccountNumber(accountNumber);
		generateAgency(agency);
	}
	
	
	@Override
	public void setId() {
		this.id = UUID.randomUUID().toString().hashCode() & Long.MAX_VALUE;
	}

	@Override
	public void setCreditCards(CreditCards creditCard) {
		this.creditCards.add(creditCard);
	}
	
	@Override
	public void generateAccountNumber(String accountNumber) {
		Random random = new Random();
		int number = random.nextInt(100000-10000) + 10000;
		this.accountNumber = String.valueOf(number);
	}
	
	@Override
	public void generateAgency(String agency) {
		Random random = new Random();
		int number = random.nextInt(1000-100) + 100;
		this.agency = String.valueOf(number);
	}
	
	@Override
	public void deposit(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) > 0) {
			this.balance = this.balance.add(amount);
		} else {
			throw new IllegalArgumentException("Deposit amount must be positive");
		}
	}
	
	@Override
	public void withdraw(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) > 0 && this.balance.compareTo(amount) >= 0) {
			this.balance = this.balance.subtract(amount);
		} else {
			throw new IllegalArgumentException("Insufficient balance or invalid amount");
		}
	}
	
	@Override
	public String toString() {
		return "Accounts [id=" + id + ", owner=" + owner + ", accountNumber=" + accountNumber + ", agency=" + agency
				+ ", bankName=" + bankName + ", balance=" + balance + ", creditCards=" + creditCards + "]";
	}

	@Override
	public List<CreditCards> getCreditCards() {
		return this.creditCards;
	}
}
