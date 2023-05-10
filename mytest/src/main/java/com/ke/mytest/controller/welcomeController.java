package com.ke.mytest.controller;


import com.ke.mytest.service.ServiceA;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class welcomeController {

	@GetMapping("/api/v1/hello")
	public String welcome() {
		return "welcome";
	}

}
