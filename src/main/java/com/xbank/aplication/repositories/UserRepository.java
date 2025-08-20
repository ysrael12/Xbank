package com.xbank.aplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import com.xbank.aplication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByCpf(String cpf);
	
}