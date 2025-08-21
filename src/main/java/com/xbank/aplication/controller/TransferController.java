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
    
	@PostMapping("/pix")
	public String pix(@RequestParam Long sourceId,
					  @RequestParam Long destinationId,
					  @RequestParam BigDecimal amount) {
		transferService.executePix(sourceId, destinationId, amount);
		return "redirect:/accounts";
	}
    
	@PostMapping("/TED")
	public String ted(@RequestParam Long sourceId,
					  @RequestParam Long destinationId,
					  @RequestParam BigDecimal amount) {
		transferService.executeTransfer(sourceId, destinationId, amount);
		return "redirect:/accounts";
	}
	
	@PostMapping("/boleto")
	public String boleto(@RequestParam Long sourceId,
						 @RequestParam Long destinationId,
						 @RequestParam BigDecimal amount) {
		transferService.executeTransfer(sourceId, destinationId, amount);
		return "redirect:/accounts";
	}
	
	@PostMapping("/deposit")
	public String deposit(@RequestParam Long sourceId,
						  @RequestParam Long destinationId,
						  @RequestParam BigDecimal amount) {
		transferService.executeTransfer(sourceId, destinationId, amount);
		return "redirect:/accounts";
	}
}


