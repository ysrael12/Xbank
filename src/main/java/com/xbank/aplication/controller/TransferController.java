package com.xbank.aplication.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.xbank.aplication.service.TransferService;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public String transfer(@RequestParam Long sourceId,
                           @RequestParam Long destinationId,
                           @RequestParam BigDecimal amount) {
        transferService.executeTransfer(sourceId, destinationId, amount);
        return "redirect:/accounts";
    }
}


