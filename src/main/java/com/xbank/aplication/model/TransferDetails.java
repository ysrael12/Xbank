package com.xbank.aplication.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public interface TransferDetails {
    Long getId();
    void setId(Long id);

    Accounts getSourceAccount();
    void setSourceAccount(Accounts sourceAccount);

    Accounts getDestinationAccount();
    void setDestinationAccount(Accounts destinationAccount);

    BigDecimal getAmount();
    void setAmount(BigDecimal amount);

    LocalDateTime getTransferDate();
    void setTransferDate(LocalDateTime transferDate);
    String getType();
}
