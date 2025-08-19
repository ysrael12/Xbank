package com.xbank.aplication.repositories;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xbank.aplication.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findById(Long id);
	
	void deleteById(Long id);
	
	boolean existsByEmail(String email);
	
	boolean existsById(Long id);
	
	Optional<User> findByCpf(String cpf);
	
	
}