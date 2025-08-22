package com.xbank.aplication.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xbank.aplication.model.ContaPF;
import com.xbank.aplication.model.User;
import com.xbank.aplication.service.ContaPfService;
import com.xbank.aplication.service.UserService;

@Controller
@RequestMapping("/minha-conta")
public class MyAccountController {

    @Autowired
    private ContaPfService contaPfService;
    
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String exibirMinhaConta(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        
        if (user == null) {
            return "redirect:/login";
        }
        
        // Adiciona um tratamento de erro para o caso de o iterador estar vazio
        Iterator<ContaPF> iterator = contaPfService.findByCpf(user.getCpf()).iterator();
        
        if (iterator.hasNext()) {
            ContaPF conta = iterator.next();
            model.addAttribute("conta", conta);
            return "account/createMyAccount"; 
        } else {
            // Redireciona o usuário para a página de criação de conta se nenhuma for encontrada
            return "redirect:/conta-pf/create";
        }
    }

    @PostMapping("/depositar")
    public String depositar(Principal principal,
                            @RequestParam BigDecimal valor,
                            Model model) {
        User user = userService.findByEmail(principal.getName());
        ContaPF conta = contaPfService.findByCpf(user.getCpf()).iterator().next();
        contaPfService.deposit(conta.getId(), valor);
        model.addAttribute("conta", contaPfService.findById(conta.getId()));
        model.addAttribute("mensagem", "Depósito realizado com sucesso!");
        return "account/createMyAccount";
    }

    @PostMapping("/sacar")
    public String sacar(Principal principal,
                        @RequestParam BigDecimal valor,
                        Model model) {
        User user = userService.findByEmail(principal.getName());
        ContaPF conta = contaPfService.findByCpf(user.getCpf()).iterator().next();
        contaPfService.withdraw(conta.getId(), valor);
        model.addAttribute("conta", contaPfService.findById(conta.getId()));
        model.addAttribute("mensagem", "Saque realizado com sucesso!");
        return "account/createMyAccount";
    }
}
