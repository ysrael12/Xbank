package com.xbank.aplication.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbank.aplication.model.ContaPF;
import com.xbank.aplication.model.User;
import com.xbank.aplication.repositories.ContaPfRepository;

@Service
public class ContaPfService {

    @Autowired
    private ContaPfRepository contaPfRepository;

    public ContaPF createContaPf(ContaPF conta, User user) {
        conta.generateAccountNumber();
        conta.generateAgency();

        // Define o usuário como dono da conta
        conta.setOwner(user);

        // Seta o saldo inicial da conta
        conta.setBalance(BigDecimal.ZERO);

        return contaPfRepository.save(conta);
    }

    public ContaPF findById(Long id) {
        return contaPfRepository.findById(id).orElse(null);
    }

    public boolean existsById(Long id) {
        return contaPfRepository.existsById(id);
    }

    public void deleteById(Long id) {
        contaPfRepository.deleteById(id);
    }

    public Iterable<ContaPF> findAll() {
        return contaPfRepository.findAll();
    }

    public ContaPF deposit(Long id, BigDecimal amount) {
        ContaPF contaPf = findById(id);
        if (contaPf != null) {
            contaPf.deposit(amount);
            return contaPfRepository.save(contaPf);
        }
        return null;
    }

    public ContaPF withdraw(Long id, BigDecimal amount) {
        ContaPF contaPf = findById(id);
        if (contaPf != null) {
            contaPf.withdraw(amount);
            return contaPfRepository.save(contaPf);
        }
        return null;
    }

    public Iterable<ContaPF> findByCpf(String cpf) {
        return contaPfRepository.findByCpf(cpf);
    }

    public boolean existsByCpf(String cpf) {
        return contaPfRepository.findByCpf(cpf).iterator().hasNext();
    }

    public ContaPF addCreditCard(Long id, String cardHolderName, BigDecimal limit) {
        ContaPF contaPf = findById(id);
        if (contaPf != null) {
            contaPf.addCreditCard(cardHolderName, limit);
            return contaPfRepository.save(contaPf);
        }
        return null;
    }
}
