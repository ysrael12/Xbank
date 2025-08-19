package com.xbank.aplication.model;
import java.math.BigDecimal;

public interface CreditCardDetails {
	void setcardNumberByCardType(String cardNumber);
	void addPurchase(BigDecimal amount);
	void makePayment(BigDecimal amount);

}
