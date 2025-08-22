package com.xbank.aplication.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xbank.aplication.model.ContaPF;
import com.xbank.aplication.model.User;
import com.xbank.aplication.service.ContaPfService;

@Controller
@RequestMapping("/minha-conta")
public class MyAccountController {

    @Autowired
    private ContaPfService contaPfService;

    @GetMapping
    public String exibirMinhaConta(@AuthenticationPrincipal User user, Model model) {
        ContaPF conta = contaPfService.findByCpf(user.getCpf()).iterator().next();
        model.addAttribute("conta", conta);
        return "minha-conta";
    }

    @PostMapping("/depositar")
    public String depositar(@AuthenticationPrincipal User user,
                            @RequestParam BigDecimal valor,
                            Model model) {
        ContaPF conta = contaPfService.findByCpf(user.getCpf()).iterator().next();
        contaPfService.deposit(conta.getId(), valor);
        model.addAttribute("conta", contaPfService.findById(conta.getId()));
        model.addAttribute("mensagem", "Depósito realizado com sucesso!");
        return "minha-conta";
    }

    @PostMapping("/sacar")
    public String sacar(@AuthenticationPrincipal User user,
                        @RequestParam BigDecimal valor,
                        Model model) {
        ContaPF conta = contaPfService.findByCpf(user.getCpf()).iterator().next();
        contaPfService.withdraw(conta.getId(), valor);
        model.addAttribute("conta", contaPfService.findById(conta.getId()));
        model.addAttribute("mensagem", "Saque realizado com sucesso!");
        return "minha-conta";
    }
}
