package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdac.Entity.UserdetailsClass;
import com.cdac.model.CustomUserDetail;
import com.cdac.repository.UserdetailsRepo;


@Service
public class CustomUserDetailServices implements UserDetailsService{

	@Autowired
	private UserdetailsRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserdetailsClass user = this.userRepo.fetchUserByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new CustomUserDetail(user);
	}

}
