package com.xbank.aplication.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller 
@RequestMapping("/")
public class IndexController {
	
	@GetMapping("")
	public String getIndex() {
		return "index";
	}

}
