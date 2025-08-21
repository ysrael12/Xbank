package com.xbank.aplication.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbank.aplication.model.Accounts;
import com.xbank.aplication.model.Transfer;
import com.xbank.aplication.repositories.TransferRepository;
import com.xbank.aplication.repositories.AccountRepository;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transfer executeTransfer(Long sourceId, Long destinationId, BigDecimal amount) {
        Accounts source = accountRepository.findById(sourceId).orElse(null);
        Accounts destination = accountRepository.findById(destinationId).orElse(null);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Conta de origem ou destino não encontrada.");
        }

        if (source.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente na conta de origem.");
        }

        source.withdraw(amount);
        destination.deposit(amount);

        accountRepository.save(source);
        accountRepository.save(destination);

        Transfer transfer = new Transfer(source, destination, amount);
        return transferRepository.save(transfer);
    }
    
    public Transfer executePix(Long sourceId, Long destinationId, BigDecimal amount) {
		Optional<Accounts> source = accountRepository.findById(sourceId);
		Optional<Accounts> destination = accountRepository.findById(destinationId);
		if (source.isEmpty() || destination.isEmpty()) {
			throw new IllegalArgumentException("Conta de origem ou destino não encontrada.");
			
		}
		if (source.get().getBalance().compareTo(amount) < 0) {
			throw new IllegalArgumentException("Saldo insuficiente na conta de origem.");
		}
		source.get().withdraw(amount);
		destination.get().deposit(amount);
		accountRepository.save(source.get());
		accountRepository.save(destination.get());
		Transfer transfer = new Transfer(source.get(), destination.get(), amount);
		return transferRepository.save(transfer);
    }	
    
    public Transfer executeDeposit(Long destinationId, BigDecimal amount) {
		Optional<Accounts> destination = accountRepository.findById(destinationId);
		if (destination.isEmpty()) {
			throw new IllegalArgumentException("Conta de destino não encontrada.");
		}
		destination.get().deposit(amount);
		accountRepository.save(destination.get());
		Transfer transfer = new Transfer(null, destination.get(), amount);
		return transferRepository.save(transfer);
    }
    
    public Transfer executeTED(Long sourceId, Long destinationId, BigDecimal amount) {
    	Accounts source = accountRepository.findById(sourceId).orElse(null);
    	Accounts destination = accountRepository.findById(destinationId).orElse(null);
    	if (source == null || destination == null) {
			throw new IllegalArgumentException("Conta de origem ou destino não encontrada.");
		}
    	if (source.getBalance().compareTo(amount) < 0) {
    		throw new IllegalArgumentException("Saldo insuficiente na conta de origem.");
    	}
    	
    	source.withdraw(amount);
    	destination.deposit(amount);
    	accountRepository.save(source);
    	accountRepository.save(destination);
    	Transfer transfer = new Transfer(source, destination, amount);
    	return transferRepository.save(transfer);
    			
    }
    
    public Transfer executeBoleto(Long destinationId, BigDecimal amount) {
    	Optional<Accounts> destination = accountRepository.findById(destinationId);
    	if (destination.isEmpty()) {
    		throw new IllegalArgumentException("Conta de destino não encontrada.");
    	}
    	destination.get().deposit(amount);
    	accountRepository.save(destination.get());
    	Transfer transfer = new Transfer(null, destination.get(), amount);
    	return transferRepository.save(transfer);
    }
}
