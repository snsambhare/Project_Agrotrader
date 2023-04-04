package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Entity.UserdetailsClass;
import com.cdac.repository.UserdetailsRepo;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserdetailsRepo userRepo;

	
	public void adduser(UserdetailsClass userdetailsclass) {
		userRepo.save(userdetailsclass);
		
	}

	public UserdetailsClass fetchuser(String email) {
		return userRepo.getuser(email);
	}
	
	public boolean isPresent(String email) {
		if(userRepo.finduser(email) == 1) {
			return true;
		}else
			return false;
	}

	public UserdetailsClass getBuyer(int buyerId) {
		return userRepo.getbiddingbuyer(buyerId);
	}

	public UserdetailsClass getloggeduser(String email) {
		System.out.println("hello");
		return userRepo.getuser(email);
	}

	public String updatepass(String email,String newPass) {
		try {
			UserdetailsClass user= userRepo.fetchUserByEmail(email);
			user.setUserPassword(newPass);
			userRepo.save(user);
			return "Password Updated Successfully";
		}catch(Exception e) {
			return "Password Not Updated";
		}
	}

	
	
	

	
	
	
	
}
