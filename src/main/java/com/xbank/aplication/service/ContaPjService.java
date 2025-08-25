package com.xbank.aplication.service;
import com.xbank.aplication.repositories.ContaPjRepository;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbank.aplication.model.ContaPJ;
import com.xbank.aplication.model.User;

@Service
public class ContaPjService {
	
	@Autowired
	ContaPjRepository contaPjRepository;
	
	public ContaPJ createContaPj(ContaPJ conta, User user) {
		conta.generateAccountNumber();
		conta.generateAgency();
		
		//Pega o usuario logado e atribui como dono da conta
		conta.setOwner(user);
		//Seta o CNPJ e nome da empresa
	
		//Seta o saldo inicial da conta
		conta.setBalance(BigDecimal.ZERO);
		
		return contaPjRepository.save(conta);
	}
	
	public ContaPJ findById(Long id) {
		return contaPjRepository.findById(id).orElse(null);
	}
	
	public boolean existsById(Long id) {
		return contaPjRepository.existsById(id);
	}
	
	public void deleteById(Long id) {
		contaPjRepository.deleteById(id);
	}
	
	public Iterable<ContaPJ> findAll() {
		return contaPjRepository.findAll();
	}
	
	public ContaPJ deposit(Long id, BigDecimal amount) {
		ContaPJ contaPj = findById(id);
		if (contaPj != null) {
			contaPj.deposit(amount);
			return contaPjRepository.save(contaPj);
		}
		return null;
	}
	
	public ContaPJ withdraw(Long id, BigDecimal amount) {
		ContaPJ contaPj = findById(id);
		if (contaPj != null) {
			contaPj.withdraw(amount);
			return contaPjRepository.save(contaPj);
		}
		return null;
	}
	
	public Iterable<ContaPJ> findByCnpj(String cnpj) {
		return contaPjRepository.findByCnpj(cnpj);
	}
	
	public boolean existsByCnpj(String cnpj) {
		return contaPjRepository.findByCnpj(cnpj).iterator().hasNext();
	}
	
	//add credit card to contaPJ
	public ContaPJ addCreditCard(Long id, String cardHolderName, BigDecimal limit) {
		ContaPJ contaPj = findById(id);
		if (contaPj != null) {
			contaPj.addCreditCard(cardHolderName, limit);
			return contaPjRepository.save(contaPj);
		}
		return null;
	}


}
