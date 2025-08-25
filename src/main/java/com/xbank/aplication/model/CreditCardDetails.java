package com.xbank.aplication.model;
import java.math.BigDecimal;
import java.util.Date;

public interface CreditCardDetails {
	void addPurchase(BigDecimal amount);
	void makePayment(BigDecimal amount);
	void setCardNumber();
	int calculateLuhnCheckDigit(String number);
	boolean isValidLuhn(String cardNumber);
	boolean isActive();
	BigDecimal getAvailableCredit();
	BigDecimal getCurrentBalance();
	BigDecimal getCreditLimit();
	String getCardType();
	String getCvv();
	String getExpirationDate();
	String getCardHolderName();
	Accounts getOwnerAccount();
	String getCardNumber();
	Long getId();
	void setAccount(Accounts account);
	void setActive(boolean isActive2);
	void setPassword(String password2);
	void setCurrentBalance(BigDecimal currentBalance2);
	void setAvailableCredit(BigDecimal availableCredit2);
	void setCreditLimit(BigDecimal creditLimit2);
	void setCardType(String cardType2);
	void setCvv();
	void setCardHolderName(String cardHolderName2);
	void setCardNumber(String cardNumber2);
	void setExpirationDate();
	
}
