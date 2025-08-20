package com.xbank.aplication.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbank.aplication.model.User;
import com.xbank.aplication.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}
	
	public boolean existsByCpf(String cpf) {
		return userRepository.findByCpf(cpf).isPresent();
	}
	
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}
	
	public User createUser(User user) {
		return userRepository.createUser(user);
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findById(String id) {
		return userRepository.findById(id);
	}
	
	public User findByCpf(String cpf) {
		return userRepository.findByCpf(cpf).orElse(null);
	}
	
	public User updateUser(User user) {
		return userRepository.createUser(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
}