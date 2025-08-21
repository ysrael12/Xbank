package com.xbank.aplication.controller;

import com.xbank.aplication.model.ContaPJ;
import com.xbank.aplication.model.User;
import com.xbank.aplication.service.ContaPjService;
import com.xbank.aplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/conta-pj")
public class ContaPjController {

    @Autowired
    ContaPjService contaPjService;

    @Autowired
    UserService userService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contaPJ", new ContaPJ());
        return "account/createContaPj";
    }

    @PostMapping("/create")
    public String register(@ModelAttribute("contaPJ") ContaPJ conta, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        contaPjService.createContaPj(conta, user);
        return "redirect:/login";
    }
    

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ContaPJ contaPJ = contaPjService.findById(id);
        if (contaPJ == null) {
            return "redirect:/accounts";
        }
        model.addAttribute("contaPJ", contaPJ);
        return "account/createContaPj";
    }

    @PostMapping("/update")
    public String updateAccount(@ModelAttribute("contaPJ") ContaPJ conta, Principal principal) {
    	User user = userService.findByEmail(principal.getName());
        contaPjService.createContaPj(conta, user);
        return "redirect:/accounts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        contaPjService.deleteById(id);
        return "redirect:/accounts";
    }

    @GetMapping
    public String listAccounts(Model model) {
        model.addAttribute("accounts", contaPjService.findAll());
        return "account/list";
    }

    @PostMapping("/deposit")
    public ResponseEntity<ContaPJ> deposit(@RequestBody ContaPJ contaPj) {
        ContaPJ updatedContaPj = contaPjService.deposit(contaPj.getId(), contaPj.getBalance());
        if (updatedContaPj != null) {
            return ResponseEntity.ok(updatedContaPj);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ContaPJ> withdraw(@RequestBody ContaPJ contaPj) {
        ContaPJ updatedContaPj = contaPjService.withdraw(contaPj.getId(), contaPj.getBalance());
        if (updatedContaPj != null) {
            return ResponseEntity.ok(updatedContaPj);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
