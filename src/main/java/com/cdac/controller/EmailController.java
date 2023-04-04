package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Entity.EmailRequest;
import com.cdac.service.EmailService;
import com.cdac.service.UserService;


@RestController
@RequestMapping
@CrossOrigin("*")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userservice;
	
	
	@PostMapping("/sendemail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		
		if(userservice.isPresent(request.getTo())) {
		
			boolean result = this.emailService.sendEmail(request.getSubject(),request.getMessage(),request.getTo());
			if(result) {
				return ResponseEntity.ok("Email sent");
			}else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent !!");
			}	
		}else {
			return ResponseEntity.ok("User Is Not Registered");
		}
	}
	
}
