package com.ke.mytest.controller;


import com.ke.mytest.event.RegisterSuccessEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1")
public class WelcomeController {
	@Resource
	ApplicationContext applicationContext;

	@GetMapping("/hello")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/eventTest")
	public void publishEvent() {
		applicationContext.publishEvent(new RegisterSuccessEvent("event 测试"));
	}

}
