package com.xbank.aplication.controller;

import com.xbank.aplication.model.Accounts;
import com.xbank.aplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("account", new Accounts());
        return "account/form";
    }

    @PostMapping
    public String createAccount(@ModelAttribute Accounts account) {
        accountService.createAccount(account);
        return "redirect:/accounts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Accounts account = accountService.findById(id);
        if (account == null) {
            return "redirect:/accounts";
        }
        model.addAttribute("account", account);
        return "account/form";
    }

    @PostMapping("/update")
    public String updateAccount(@ModelAttribute Accounts account) {
        accountService.createAccount(account);
        return "redirect:/accounts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
        return "redirect:/accounts";
    }

    @GetMapping
    public String listAccounts(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "account/list";
    }
}
