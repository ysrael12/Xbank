package com.xbank.aplication.controller;

import com.xbank.aplication.model.Accounts;
import com.xbank.aplication.model.CreditCards;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Controlador responsavel por gerenciar operaoes relacionadas a contas bancarias
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private List<Accounts> accountsList = new ArrayList<>();

    // criar uma nova conta
    @PostMapping("/create")
    public Accounts createAccount(@RequestBody Accounts account) {
        if (account.getCreditCards() == null) {
            account.setCreditCards(new ArrayList<>());
        }
        accountsList.add(account);
        return account;
    }

    // buscar conta por ID
    @GetMapping("/{id}")
    public Accounts getAccountById(@PathVariable Long id) {
        return accountsList.stream()
                .filter(acc -> acc.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    //depositar
    @PostMapping("/{id}/deposit")
    public Accounts deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        Accounts account = getAccountById(id);
        account.deposit(amount);
        return account;
    }

    // sacar
    @PostMapping("/{id}/withdraw")
    public Accounts withdraw(@PathVariable Long id, @RequestParam BigDecimal amount) {
        Accounts account = getAccountById(id);
        account.withdraw(amount);
        return account;
    }

    // adicionar cartao de credito
    @PostMapping("/{id}/add-card")
    public Accounts addCreditCard(@PathVariable Long id, @RequestBody CreditCards card) {
        Accounts account = getAccountById(id);
        account.setCreditCards(card);
        return account;
    }

    // listar os cartoes de credito
    @GetMapping("/{id}/cards")
    public List<CreditCards> getCreditCards(@PathVariable Long id) {
        Accounts account = getAccountById(id);
        return account.getCreditCards();
    }

    // listar  as contas
    @GetMapping("/all")
    public List<Accounts> getAllAccounts() {
        return accountsList;
    }
}
