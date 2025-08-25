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
import com.xbank.aplication.service.TransferService;
import com.xbank.aplication.service.UserService;

@Controller
@RequestMapping("/minha-conta")
public class MyAccountController {

    @Autowired
    private ContaPfService contaPfService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

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

    @GetMapping("/transferir")
    public String exibirTransferencia(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        ContaPF conta = contaPfService.findByCpf(user.getCpf()).iterator().next();
        model.addAttribute("conta", conta);
        return "account/transferForm";
    }

    @PostMapping("/transferir")
    public String transferir(Principal principal,
                             @RequestParam Long destinoId,
                             @RequestParam BigDecimal valor,
                             @RequestParam String tipo,
                             Model model) {
        User user = userService.findByEmail(principal.getName());
        ContaPF contaOrigem = contaPfService.findByCpf(user.getCpf()).iterator().next();
        try {
            switch (tipo.toLowerCase()) {
                case "pix":
                    transferService.executePix(contaOrigem.getId(), destinoId, valor);
                    break;
                case "ted":
                    transferService.executeTransfer(contaOrigem.getId(), destinoId, valor);
                    break;
                case "boleto":
                    transferService.executeTransfer(contaOrigem.getId(), destinoId, valor); // Substitua se houver lógica específica
                    break;
                case "depósito":
                case "deposit":
                    transferService.executeTransfer(contaOrigem.getId(), destinoId, valor); // Substitua se houver lógica específica
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de transferência inválido");
            }
            model.addAttribute("mensagem", "Transferência realizada com sucesso!");
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao transferir: " + e.getMessage());
        }
        model.addAttribute("conta", contaPfService.findById(contaOrigem.getId()));
        return "account/createMyAccount";
    }
    
    @GetMapping("/cartoes")
    public String exibirCartoes(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        if (user == null) {
            return "redirect:/login";
        }
        Iterator<ContaPF> iterator = contaPfService.findByCpf(user.getCpf()).iterator();
        if (iterator.hasNext()) {
            ContaPF conta = iterator.next();
            model.addAttribute("conta", conta);
            // Adiciona todos os cartões de crédito do usuário
            model.addAttribute("creditCards", conta.getCreditCards());
            return "account/cards";
        } else {
            return "redirect:/conta-pf/create";
        }
    }

    @PostMapping("/cartoes/adicionar")
    public String adicionarCartao(Principal principal,
                                  @RequestParam String cardHolderName,
                                  @RequestParam BigDecimal limit,
                                  @RequestParam String password,
                                  Model model) {
        User user = userService.findByEmail(principal.getName());
        if (user == null) {
            return "redirect:/login";
        }
        Iterator<ContaPF> iterator = contaPfService.findByCpf(user.getCpf()).iterator();
        if (iterator.hasNext()) {
            ContaPF conta = iterator.next();
            // Usa o service para adicionar e persistir o cartão
            contaPfService.addCreditCard(conta.getId(), cardHolderName, limit, password);
            ContaPF contaAtualizada = contaPfService.findById(conta.getId());
            model.addAttribute("conta", contaAtualizada);
            model.addAttribute("creditCards", contaAtualizada.getCreditCards());
            model.addAttribute("mensagem", "Cartão adicionado com sucesso!");
            return "account/cards";
        } else {
            return "redirect:/conta-pf/create";
        }
    }
}