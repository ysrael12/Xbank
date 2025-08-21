package com.xbank.aplication.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Transfer implements TransferDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Accounts sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Accounts destinationAccount;

    private BigDecimal amount;
    private Date transferDate;

    public Transfer() {
        this.transferDate = new Date();
    }

    public Transfer(Accounts source, Accounts destination, BigDecimal amount) {
        this.sourceAccount = source;
        this.destinationAccount = destination;
        this.amount = amount;
        this.transferDate = new Date();
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
    public Date getTransferDate() {
        return this.transferDate;
    }

    @Override
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }
}
