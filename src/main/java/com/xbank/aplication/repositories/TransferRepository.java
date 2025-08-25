package com.xbank.aplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xbank.aplication.model.Transfer;
import com.xbank.aplication.model.ContaPF;

import java.math.BigDecimal;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    // busca  as transferencias feitas por uma conta
    List<Transfer> findBySourceAccount(ContaPF sourceAccount);

    // busca todas as transferencias recebidas por uma conta
    List<Transfer> findByDestinationAccount(ContaPF destination);

    //busca transferencias por valor
    List<Transfer> findByAmountGreaterThanEqual(BigDecimal amount);
}
