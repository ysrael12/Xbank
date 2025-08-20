package com.xbank.aplication.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;
import com.xbank.aplication.model.User;

public interface UserRepository extends Repository<User, Long> {
	
	User findByEmail(String email);
	
	User findById(String string);
	
	void deleteById(String id);
	
	boolean existsByEmail(String email);
	
	boolean existsById(Long id);
	
	Optional<User> findByCpf(String cpf);
	
	List<User> findAll();
	
	User createUser(User user);
}