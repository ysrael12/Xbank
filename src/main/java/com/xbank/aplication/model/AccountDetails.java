package com.xbank.aplication.model;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDetails {
	void generateAccountNumber();
	void generateAgency();;
	List<CreditCards> getCreditCards();
	void setCreditCards(CreditCards creditCard);
	
	void deposit(BigDecimal amount);
	void withdraw(BigDecimal amount);
	void setId(Long id);
	void setOwner(User owner);
	void setAccountNumber(String accountNumber);
	String getAccountNumber();
	Long getId();
	BigDecimal getBalance();
	void setNomeCompleto(String nome);
	String getNomeCompleto();
	String getBankName();
	void setBankName(String bankName);
	void setAgency(String agency);
	String getAgency();
	User getOwner();
	void setBalance(BigDecimal balance);
	
}
