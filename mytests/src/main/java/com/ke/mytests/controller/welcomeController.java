package com.ke.mytests.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class welcomeController {
	@GetMapping("/api/v1/hello")
	public String welcome(){
		return "welcome";
	}
}
