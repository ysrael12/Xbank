package com.xbank.aplication.controller;

import com.xbank.aplication.model.ContaPF;
import com.xbank.aplication.model.ContaPJ;
import com.xbank.aplication.model.User;
import com.xbank.aplication.repositories.AccountRepository;
import com.xbank.aplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/new/pf")
    public String showCreatePF(Model model) {
        model.addAttribute("contaPF", new ContaPF());
        return "account/form_pf";
    }

    @PostMapping("/new/pf")
    public String createPF(@ModelAttribute ContaPF contaPF, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        if (user == null) return "redirect:/login";
        contaPF.setOwner(user);
        accountRepository.save(contaPF);
        return "redirect:/users";
    }

    @GetMapping("/new/pj")
    public String showCreatePJ(Model model) {
        model.addAttribute("contaPJ", new ContaPJ());
        return "account/form_pj";
    }

    @PostMapping("/new/pj")
    public String createPJ(@ModelAttribute ContaPJ contaPJ, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        if (user == null) return "redirect:/login";
        contaPJ.setOwner(user);
        accountRepository.save(contaPJ);
        return "redirect:/users";
    }
}