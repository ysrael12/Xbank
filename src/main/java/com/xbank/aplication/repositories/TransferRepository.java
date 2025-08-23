package com.xbank.aplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xbank.aplication.model.Transfer;
import com.xbank.aplication.model.ContaPF;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    // busca  as transferencias feitas por uma conta
    List<Transfer> findBySource(ContaPF source);

    // busca todas as transferencias recebidas por uma conta
    List<Transfer> findByDestination(ContaPF destination);

    //busca transferencias por valor
    List<Transfer> findByAmountGreaterThanEqual(java.math.BigDecimal amount);
}
