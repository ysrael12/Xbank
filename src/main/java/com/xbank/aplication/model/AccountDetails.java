package com.xbank.aplication.model;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDetails {
	void generateAccountNumber(String accountNumber);
	void generateAgency(String agency);;
	List<CreditCards> getCreditCards();
	void setCreditCards(CreditCards creditCard);
	
	void deposit(BigDecimal amount);
	void withdraw(BigDecimal amount);
	void setId(Long id);
	
}
