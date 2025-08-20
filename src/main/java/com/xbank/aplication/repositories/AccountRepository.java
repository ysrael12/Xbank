package com.xbank.aplication.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.xbank.aplication.model.Accounts;

// Repositorio responsavel por operacoes de acesso a dados da entidade User
public interface AccountRepository  extends JpaRepository<Accounts, Long> {



}
