package com.xbank.aplication.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Transfer implements TransferDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    protected Accounts sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
	protected Accounts destinationAccount;

    protected BigDecimal amount;
    protected LocalDateTime transferDate;

    
    public Transfer(Accounts source, Accounts destination, BigDecimal amount) {
        this.sourceAccount = source;
        this.destinationAccount = destination;
        this.amount = amount;
        this.transferDate = LocalDateTime.now();
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
    public Accounts getSourceAccount() {
        return this.sourceAccount;
    }

    @Override
    public void setSourceAccount(Accounts sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    @Override
    public Accounts getDestinationAccount() {
        return this.destinationAccount;
    }

    @Override
    public void setDestinationAccount(Accounts destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public LocalDateTime getTransferDate() {
        return this.transferDate;
    }

    @Override
    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }
    @Override
    public String getType() {
        return "GENERICO";
    }
}