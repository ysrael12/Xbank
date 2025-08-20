package com.xbank.aplication.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbank.aplication.model.User;
import com.xbank.aplication.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean existsByEmail(String email) {
		if(userRepository.findByEmail(email) != null) {
			return true;
		}
		return false;
	}
	
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}
	
	public boolean existsByCpf(String cpf) {
		return userRepository.findByCpf(cpf).isPresent();
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public User findByCpf(String cpf) {
		return userRepository.findByCpf(cpf).orElse(null);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
}