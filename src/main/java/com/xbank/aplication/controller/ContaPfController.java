package com.xbank.aplication.controller;

import com.xbank.aplication.model.ContaPF;
import com.xbank.aplication.model.User;
import com.xbank.aplication.service.ContaPfService;
import com.xbank.aplication.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/conta-pf")
public class ContaPfController {

    @Autowired
    private ContaPfService contaPfService;

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contaPF", new ContaPF());
        return "account/createContaPf";
    }

    @PostMapping("/create")
    public String register(@ModelAttribute("contaPF") ContaPF conta, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        contaPfService.createContaPf(conta, user);
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ContaPF contaPF = contaPfService.findById(id);
        if (contaPF == null) {
            return "redirect:/accounts";
        }
        model.addAttribute("contaPF", contaPF);
        return "account/createContaPf";
    }

    @PostMapping("/update")
    public String updateAccount(@ModelAttribute("contaPF") ContaPF conta, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        contaPfService.createContaPf(conta, user);
        return "redirect:/accounts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        contaPfService.deleteById(id);
        return "redirect:/accounts";
    }

    @GetMapping
    public String listAccounts(Model model) {
        model.addAttribute("accounts", contaPfService.findAll());
        return "account/list";
    }

    @PostMapping("/deposit")
    public ResponseEntity<ContaPF> deposit(@RequestBody ContaPF contaPf) {
        ContaPF updatedContaPf = contaPfService.deposit(contaPf.getId(), contaPf.getBalance());
        if (updatedContaPf != null) {
            return ResponseEntity.ok(updatedContaPf);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ContaPF> withdraw(@RequestBody ContaPF contaPf) {
        ContaPF updatedContaPf = contaPfService.withdraw(contaPf.getId(), contaPf.getBalance());
        if (updatedContaPf != null) {
            return ResponseEntity.ok(updatedContaPf);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
