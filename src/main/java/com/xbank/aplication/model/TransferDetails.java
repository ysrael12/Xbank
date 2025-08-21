package com.xbank.aplication.model;

import java.math.BigDecimal;
import java.util.Date;

public interface TransferDetails {
    Long getId();
    void setId(Long id);

    Accounts getSourceAccount();
    void setSourceAccount(Accounts sourceAccount);

    Accounts getDestinationAccount();
    void setDestinationAccount(Accounts destinationAccount);

    BigDecimal getAmount();
    void setAmount(BigDecimal amount);

    Date getTransferDate();
    void setTransferDate(Date transferDate);
}
