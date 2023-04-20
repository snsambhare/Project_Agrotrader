package com.cdac.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class firstctr {

	@RequestMapping("/hello123.everyone")
	public String sayhello() {
		return "Hello Everyone";
	}
}
