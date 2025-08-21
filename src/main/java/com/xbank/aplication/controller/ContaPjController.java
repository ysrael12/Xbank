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
    UserService userService; // Injete o UserService para encontrar o proprietário

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contaPJ", new ContaPJ());
        return "account/createContaPj";
    }

    @PostMapping("/create")
    public String register(@ModelAttribute("contaPJ") ContaPJ conta, Principal principal) {
        // Encontra o usuário logado pelo nome (email) do Principal
        User user = userService.findByEmail(principal.getName());
        // Associa o usuário encontrado à conta antes de salvá-la
        contaPjService.createContaPj(conta, user);

        return "redirect:/login";
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
