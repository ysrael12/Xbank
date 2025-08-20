package com.xbank.aplication.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.xbank.aplication.model.Accounts;

// Repositorio responsavel por operacoes de acesso a dados da entidade User
public interface AccountRepository extends MongoRepository<Accounts, Long> {

    Optional<Accounts> findByAccountNumber(String accountNumber);

    Optional<Accounts> findByUserId(Long userId); // se houver relacao com o usuario

    boolean existsByAccountNumber(String accountNumber);

    void deleteById(Long id);
}
