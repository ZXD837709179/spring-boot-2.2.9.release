package com.ke.mytest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class welcomeController {
	@GetMapping("/api/v1/hello")
	public String welcome(){
		return "welcome";
	}
}
