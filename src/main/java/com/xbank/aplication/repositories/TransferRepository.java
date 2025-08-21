package com.xbank.aplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xbank.aplication.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    
}
