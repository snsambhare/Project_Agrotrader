package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.helper.JwtUtil;
import com.cdac.model.JwtRequest;
import com.cdac.model.JwtResponse;
import com.cdac.service.CustomUserDetailServices;

@RestController
@CrossOrigin("*")
public class JwtController {
	
	@Autowired(required=false)
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailServices customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/token")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		 System.out.println(jwtRequest);
		 try {
			 this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		 }catch(UsernameNotFoundException ex){
			 ex.printStackTrace();
			 throw new Exception("Bad Credentials1");
		 }catch (BadCredentialsException ex) {
			ex.printStackTrace();
			throw new Exception("Bad Credentials2");
		 }catch(AuthenticationException ex) {
			ex.printStackTrace();
			 throw new Exception("Bad Credentials3");
		 }
		 
		 
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT"+token);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
