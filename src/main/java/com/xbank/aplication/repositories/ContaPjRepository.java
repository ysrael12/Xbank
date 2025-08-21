package com.xbank.aplication.repositories;
import com.xbank.aplication.model.ContaPJ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaPjRepository extends JpaRepository<ContaPJ, Long> {

	Iterable<ContaPJ> findByCnpj(String cnpjEmpresa);
}