package com.xbank.aplication.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.xbank.aplication.model.Accounts;

// Repositorio responsavel por operacoes de acesso a dados da entidade User
public interface AccountRepository  extends Repository<Accounts, Long> {

    Accounts findByAccountNumber(String accountNumber);

    Optional<Accounts> findById(String id); // se houver relacao com o usuario

    boolean existsByAccountNumber(String accountNumber);

    void deleteById(String id);
    
    Accounts create(Accounts account);

	boolean existsById(String id);

	List<Accounts> findAll();


}
