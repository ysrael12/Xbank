package com.xbank.aplication.model;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class CreditCards implements CreditCardDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	// Correctly define the ManyToOne relationship to the Accounts entity.
	// This tells Hibernate that many credit cards belong to one account.
	@ManyToOne
	// The @JoinColumn annotation specifies the foreign key column in this table.
	// It will create a column named 'accounts_id' that holds the primary key of the Accounts table.
	@JoinColumn(name = "accounts_id")
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

	public void setCardNumber(String cardNumber2) {
		this.cardNumber = cardNumber2;
	}
	
	public void setCardHolderName(String cardHolderName2) {
		this.cardHolderName = cardHolderName2;
	}
	

	public void setExpirationDate(Date expirationDate2) {
		this.expirationDate = expirationDate2;
	}
	
	public void setCvv(String cvv2) {
		this.cvv = cvv2;
	}
	
	public void setCardType(String cardType2) {
		this.cardType = cardType2;
	}
	
	public void setCreditLimit(BigDecimal creditLimit2) {
		this.creditLimit = creditLimit2;
	}
	
	public void setCurrentBalance(BigDecimal currentBalance2) {
		this.currentBalance = currentBalance2;
	}
	
	public void setAvailableCredit(BigDecimal availableCredit2) {
		this.availableCredit = availableCredit2;
	}
	
	public void setPassword(String password2) {
		this.password = password2;
	}
	
	public void setActive(boolean isActive2) {
		this.isActive = isActive2;
	}
	
	public void setAccount(Accounts account) {
		this.ownerAccount = account;
	}

	

	
}

