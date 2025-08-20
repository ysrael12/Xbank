package com.xbank.aplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.util.List;


// Usando o Padrão Criacional: Factory method
@Entity
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String name;
	private String email;
	private String password;
	private String phone;
	private String cpf;
	private List<Accounts> accounts;
	
	public User() {
		// id will be set by MongoDB
	}
	
	public User(String name, String email, String password, String phone, String cpf) {
		setName(name);
		setEmail(email);
		setPassword(password);
		setPhone(phone);
		setCPF(cpf);
	}

	public User(Long id, String name, String email, String password, String phone, String cpf) {
		this.id = id;
		setName(name);
		setEmail(email);
		setPassword(password);
		setPhone(phone);
		setCPF(cpf);
	}
	
	@Override
	public List<Accounts> getAccounts() {
		return this.accounts;
	}
	
	public void setAccounts(Accounts account) {
		this.accounts.add(account);
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
		
	}

	@Override
	public String getPhone() {
		return this.phone;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
		
	}

	@Override
	public String getCPF() {
		return this.cpf;
	}

	@Override
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCpf() {
        return this.cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", cpf=" + cpf + "]";
	}

	
}