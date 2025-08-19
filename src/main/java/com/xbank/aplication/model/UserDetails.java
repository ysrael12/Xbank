package com.xbank.aplication.model;

import java.util.List;

// Usando o Padrão Criacional: Factory method 
public interface UserDetails {
	String getId();
	void setId(String id);
	
	String getName();
	void setName(String name);
	
	String getEmail();
	void setEmail(String email);
	
	String getPassword();
	void setPassword(String password);
	
	String getPhone();
	void setPhone(String phone);
	
	String getCPF(); 
	void setCPF(String cpf);
	
	List<Accounts> getAccounts(); 
	void setAccounts(Accounts accounts);
	
	
}