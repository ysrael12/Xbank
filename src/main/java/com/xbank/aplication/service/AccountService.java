package com.xbank.aplication.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbank.aplication.model.Accounts;
import com.xbank.aplication.model.CreditCards;
import com.xbank.aplication.repositories.AccountRepository;

// servico responsavel pela logica de negocio relacionada as contas bancarias
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Accounts createAccount(Accounts account) {
        return accountRepository.save(account);
    }

    public Accounts findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public boolean existsById(Long id) {
        return accountRepository.existsById(id);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    public List<Accounts> findAll() {
        return accountRepository.findAll();
    }

    public Accounts deposit(Long id, BigDecimal amount) {
        Accounts account = findById(id);
        if (account != null) {
            account.deposit(amount);
            return accountRepository.save(account);
        }
        return null;
    }

    public Accounts withdraw(Long id, BigDecimal amount) {
        Accounts account = findById(id);
        if (account != null) {
            account.withdraw(amount);
            return accountRepository.save(account);
        }
        return null;
    }

    public List<CreditCards> getCreditCards(Long id) {
        Accounts account = findById(id);
        return account != null ? account.getCreditCards() : null;
    }

    public Accounts addCreditCard(Long id, CreditCards card) {
        Accounts account = findById(id);
        if (account != null) {
            account.setCreditCards(card);
            return accountRepository.save(account);
        }
        return null;
    }
}
