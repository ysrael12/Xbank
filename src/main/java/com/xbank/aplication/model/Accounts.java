package com.xbank.aplication.model;
import java.math.BigDecimal;
import java.util.ArrayList; // Import ArrayList
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.util.List;
import java.util.Random;

@Entity
public abstract class Accounts implements AccountDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	protected User owner;
	public String accountNumber;
	public String agency;
	public String bankName;
	
	
	// Balance
	protected BigDecimal balance = BigDecimal.ZERO;
	
	// Credit Cards
	// Initialize the list here
	@OneToMany(mappedBy = "ownerAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<CreditCards> creditCards = new ArrayList<>();
	
	
	public Accounts() {
		// Initialize list in no-arg constructor as well
		this.creditCards = new ArrayList<>();
	}
	
	public Accounts(User owner, String accountNumber, String agency, String bankName, CreditCards creditCard) {
		// No need to initialize here if it's done in the declaration or no-arg constructor
		setCreditCards(creditCard);
		generateAccountNumber();
		generateAgency();
	}
	
	@Override
	public void setCreditCards(CreditCards creditCard) {
		if (this.creditCards == null) {
			this.creditCards = new ArrayList<>();
		}
		this.creditCards.add(creditCard);
	}
	
	@Override
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Override
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	@Override
	public void generateAccountNumber() {
		Random random = new Random();
		int number = random.nextInt(100000-10000) + 10000;
		this.accountNumber = String.valueOf(number);
	}
	
	@Override
	public void generateAgency() {
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

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}
	
	@Override
	public BigDecimal getBalance() {
		return this.balance;

	}
	
	@Override
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	@Override
	public User getOwner() {
		return this.owner;
	}
	
	@Override
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	@Override
	public String getAgency() {
	    return this.agency;
	}

	@Override
	public void setAgency(String agency) {
	    this.agency = agency;
	}
	
	@Override
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Override
	public String getBankName() {
		return this.bankName;
	}
	
	@Override
	public String getNomeCompleto(){
		return owner.getName();
	}

	@Override
	public void setNomeCompleto(String nome){
		this.owner.setName(nome);
	}
}