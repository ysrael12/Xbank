package com.xbank.aplication.model;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "credit_cards")
public class CreditCards implements CreditCardDetails {
	
	private Accounts ownerAccount;
	private String cardNumber;
	private String cardHolderName;
	private Date expirationDate;
	private String cvv;
	private String cardType; // Ex: Visa, MasterCard, Elo.
	private BigDecimal creditLimit;
	private BigDecimal currentBalance;
	private BigDecimal availableCredit;
	private String password;
	private boolean isActive;
	
	public CreditCards() {
		
	}
	
	public CreditCards(Accounts ownerAccount, String cardNumber, String cardHolderName, Date expirationDate,
			String cvv, String cardType, BigDecimal creditLimit, BigDecimal currentBalance,
			BigDecimal availableCredit, String password, boolean isActive) {
		this.ownerAccount = ownerAccount;
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
		this.cardType = cardType;
		this.creditLimit = creditLimit;
		this.currentBalance = currentBalance;
		this.availableCredit = availableCredit;
		this.password = password;
		this.isActive = isActive;
	}
	
	@Override
	public void setcardNumberByCardType(String cardNumber) {
		if(cardType.equalsIgnoreCase("Visa")) {
			this.cardNumber = "4" + cardNumber.substring(1);
		} else if(cardType.equalsIgnoreCase("MasterCard")) {
			this.cardNumber = "5" + cardNumber.substring(1);
		} else if(cardType.equalsIgnoreCase("Elo")) {
			this.cardNumber = "6" + cardNumber.substring(1);
		} else {
			throw new IllegalArgumentException("Unsupported card type: " + cardType);
		}
	}
	
	@Override
	public void addPurchase(BigDecimal amount) {
		if(amount.compareTo(availableCredit) > 0) {
			throw new IllegalArgumentException("Insufficient available credit for this purchase.");
		}
		this.currentBalance = this.currentBalance.add(amount);
		this.availableCredit = this.availableCredit.subtract(amount);
	}
	
	@Override
	public void makePayment(BigDecimal amount) {
		if(amount.compareTo(currentBalance) > 0) {
			throw new IllegalArgumentException("Payment amount exceeds current balance.");
		}
		this.currentBalance = this.currentBalance.subtract(amount);
		this.availableCredit = this.availableCredit.add(amount);
	}
	
	
	
	@Override
	public String toString() {
		return "CreditCards [ownerAccount=" + ownerAccount + ", cardNumber=" + cardNumber + ", cardHolderName="
				+ cardHolderName + ", expirationDate=" + expirationDate + ", cvv=" + cvv + ", cardType=" + cardType
				+ ", creditLimit=" + creditLimit + ", currentBalance=" + currentBalance + ", availableCredit="
				+ availableCredit + ", password=" + password + ", isActive=" + isActive + "]";
	}
}
