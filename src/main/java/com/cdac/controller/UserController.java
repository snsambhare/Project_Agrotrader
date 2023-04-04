package com.cdac.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Entity.UserdetailsClass;
import com.cdac.dto.ForgetPassDto;
import com.cdac.dto.UserDto;
//import com.cdac.exceptions.ResourceNotFoundException;
import com.cdac.service.FarmerService;
import com.cdac.service.FdealerService;
import com.cdac.service.GoodsBuyerService;
import com.cdac.service.UserService;

//import net.bytebuddy.implementation.bytecode.Throw;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

	@Autowired
	private FdealerService fdealerservice;

	@Autowired
	private FarmerService farmerservice;

	@Autowired
	private UserService userservice;

	@Autowired
	private GoodsBuyerService buyerservice;

	@PostMapping("/register")
	public void adddata(@Valid @RequestBody UserdetailsClass userdetailsclass) {

		String newPass = BCrypt.hashpw(userdetailsclass.getUserPassword(), BCrypt.gensalt(10));
		userdetailsclass.setUserPassword(newPass);
		if (userdetailsclass.getUserType() != null) {
			try {
				if (userdetailsclass.getUserType().equals("ROLE_FARMER")) {
					userservice.adduser(userdetailsclass);
					farmerservice.addfarmer(userdetailsclass);
//				} else if (userdetailsclass.getUserType().equals("FertilizerDealer")) {
//					userservice.adduser(userdetailsclass);
//					fdealerservice.addfdealer(userdetailsclass);
				} else if (userdetailsclass.getUserType().equals("ROLE_BUYER")) {
					userservice.adduser(userdetailsclass);
					buyerservice.addbuyer(userdetailsclass);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Usertype is not given");
		}
	}

	/*
	 * @PostMapping("/login") public ResponseEntity<UserDto> UserLogin(@RequestBody
	 * UserdetailsClass user, Model model, HttpSession session) {
	 * System.out.println(user.getUserPassword()); String newPass =
	 * BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt(10));
	 * System.out.println(newPass); user.setUserPassword(newPass);
	 * 
	 * 
	 * UserDto userdtoobj= new UserDto(); try { UserdetailsClass signeduser
	 * =userservice.isPresent(user.getUserEmailId(),user.getUserPassword());
	 * if(signeduser!=null) { userdtoobj.setUserId(signeduser.getUserId());
	 * userdtoobj.setUserName(signeduser.getUserName());
	 * userdtoobj.setUserEmailId(signeduser.getUserEmailId());
	 * userdtoobj.setUserType(signeduser.getUserType());
	 * userdtoobj.setActive(signeduser.isActive()); }else
	 * System.out.println("null");
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * if(signeduser!=null) {session.setAttribute("currentuser", signeduser);} else
	 * System.out.println("User Not Found");
	 * 
	 * 
	 * return new ResponseEntity<UserDto>(userdtoobj,HttpStatus.OK); }
	 */

	@GetMapping("/getuserbyemail/{email}")
	public ResponseEntity<UserDto> UserLogin(@PathVariable String email) {
		System.out.println("called");
		UserDto userdtoobj = new UserDto();
		try {
			UserdetailsClass signeduser = userservice.getloggeduser(email);
			if (signeduser != null) {
				userdtoobj.setUserId(signeduser.getUserId());
				userdtoobj.setUserName(signeduser.getUserName());
				userdtoobj.setUserEmailId(signeduser.getUserEmailId());
				userdtoobj.setUserType(signeduser.getUserType());
				userdtoobj.setActive(signeduser.isActive());
			} else
				System.out.println("null");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<UserDto>(userdtoobj, HttpStatus.OK);
	}

	@PostMapping("/forgetpass")
	public String ForgetPassword(@RequestBody ForgetPassDto forgetpass) {
		System.out.println(forgetpass.getNewPass());
		String newPass = BCrypt.hashpw(forgetpass.getNewPass(), BCrypt.gensalt(10));
		return userservice.updatepass(forgetpass.getTo(), newPass);
	}

//	@PostMapping("/logout")
//	public String Logout(HttpSession session) {
//		session.removeAttribute("currentuser");
//		session.invalidate();
//		return "You have successfully logged out!";
//
//	}

}
