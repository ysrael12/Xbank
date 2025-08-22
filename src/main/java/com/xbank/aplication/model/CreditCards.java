package com.xbank.aplication.model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;



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
	
	public CreditCards(Accounts ownerAccount, String cardHolderName, Date expirationDate,
			String cvv, String cardType, BigDecimal creditLimit, BigDecimal currentBalance,
			BigDecimal availableCredit, String password, boolean isActive) {
		this.ownerAccount = ownerAccount;
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
	
	private final Random random = new Random();
	@Override
	public void setCardNumber() {
		// gerador um número de cartão único e válido
		StringBuilder sb = new StringBuilder();

        // 1. Define o prefixo do cartão (ex: '4' para Visa)
        sb.append("4");

        // 2. Gera 14 dígitos aleatórios
        for (int i = 0; i < 14; i++) {
            sb.append(random.nextInt(10));
        }

        // 3. Constrói o número inicial (15 dígitos)
        String partialCardNumber = sb.toString();

        // 4. Calcula o dígito de verificação usando o algoritmo de Luhn
        int checkDigit = calculateLuhnCheckDigit(partialCardNumber);

        // 5. Adiciona o dígito de verificação ao número completo
        this.cardNumber = partialCardNumber + checkDigit;

	}
	
	@Override
	public int calculateLuhnCheckDigit(String number) {
        int sum = 0;
        boolean alternate = true;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }
	@Override
    // Metodo para validar um numero de cartao usando o algoritmo de Luhn
    public boolean isValidLuhn(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
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
	
	@Override
	public void setCardNumber(String cardNumber2) {
		this.cardNumber = cardNumber2;
	}
	@Override
	public void setCardHolderName(String cardHolderName2) {
		this.cardHolderName = cardHolderName2;
	}
	
	@Override
	public void setExpirationDate(Date expirationDate2) {
		this.expirationDate = expirationDate2;
	}
	
	@Override
	public void setCvv(String cvv2) {
		this.cvv = cvv2;
	}
	
	@Override
	public void setCardType(String cardType2) {
		this.cardType = cardType2;
	}
	
	@Override
	public void setCreditLimit(BigDecimal creditLimit2) {
		this.creditLimit = creditLimit2;
	}
	
	@Override
	public void setCurrentBalance(BigDecimal currentBalance2) {
		this.currentBalance = currentBalance2;
	}
	
	@Override
	public void setAvailableCredit(BigDecimal availableCredit2) {
		this.availableCredit = availableCredit2;
	}
	
	@Override
	public void setPassword(String password2) {
		this.password = password2;
	}
	
	@Override
	public void setActive(boolean isActive2) {
		this.isActive = isActive2;
	}
	
	@Override
	public void setAccount(Accounts account) {
		this.ownerAccount = account;
	}
	
	@Override
	public Long getId() {
		return this.id;
	}
	
	@Override
	public Accounts getOwnerAccount() {
		return this.ownerAccount;
	}
	
	@Override
	public String getCardNumber() {
		return this.cardNumber;
	}
	
	@Override
	public String getCardHolderName() {
		return this.cardHolderName;
	}
	
	@Override
	public Date getExpirationDate() {
		return this.expirationDate;
	}
	
	@Override
	public String getCvv() {
		return this.cvv;
	}
	
	@Override
	public String getCardType() {
		return this.cardType;
	}
	
	@Override
	public BigDecimal getCreditLimit() {
		return this.creditLimit;
	}
	
	@Override
	public BigDecimal getCurrentBalance() {
		return this.currentBalance;
	}
	
	@Override
	public BigDecimal getAvailableCredit() {
		return this.availableCredit;
	}
	
	@Override
	public boolean isActive() {
		return this.isActive;
	}
	
	
}

