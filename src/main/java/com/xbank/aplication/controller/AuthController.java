package com.xbank.aplication.controller;

import com.xbank.aplication.model.User;
import com.xbank.aplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
    @GetMapping("/login")
    public String login() {
    		
        return "auth/login";
    }
    
    @PostMapping("/login")
    public String loginPost(String email, String password, Model model) {
        var userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.getPassword())) {
            model.addAttribute("loginError", "Email or password invalid");
            return "auth/login";
        }
        // Aqui você pode adicionar lógica de sessão se necessário
        return "redirect:/index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return "redirect:/login";
    }
}