package com.cdac.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Entity.BiddingEntityClass;
import com.cdac.Entity.FarmersClass;
import com.cdac.Entity.UserdetailsClass;
import com.cdac.service.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminController {

	@Autowired
	private AdminService adminservice;
	
	@GetMapping("/activefarmers")
	public ResponseEntity<List<UserdetailsClass>> Activefarmers (){
		List<UserdetailsClass> list= adminservice.getActivefarmers();
		return new ResponseEntity<List<UserdetailsClass>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/activegoodsbuyers")
	public ResponseEntity<List<UserdetailsClass>> Activegoodsbuyer(){
		List<UserdetailsClass> list = adminservice.getActivebuyers();
		return new ResponseEntity<List<UserdetailsClass>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/deactivatedusers")
	public ResponseEntity<List<UserdetailsClass>> DeactivatedUsers(){
		List<UserdetailsClass> list = adminservice.getdeactivatedusers();
		return new ResponseEntity<List<UserdetailsClass>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/activateuser/{userId}")
	public String Activateuser(@PathVariable String userId) {
		return adminservice.enableuser(Integer.parseInt(userId));
	}
	
	@GetMapping("/deactivateuser/{userId}")
	public String Deactivateuser(@PathVariable String userId) {
		return adminservice.disableuser(Integer.parseInt(userId));
	}
	
	
	@GetMapping("/todaysbids")
	public ResponseEntity<List<BiddingEntityClass>> TodaysBids(){
		List<BiddingEntityClass> list = adminservice.gettodaysBids();
		return new ResponseEntity<List<BiddingEntityClass>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/bidshistory")
	public ResponseEntity<List<BiddingEntityClass>> BidsHistory(){
		List<BiddingEntityClass> list = adminservice.getAllhistory();
		LocalDate date= LocalDate.now();
		System.out.println(date);
		return new ResponseEntity<List<BiddingEntityClass>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public String DeleteUser(@PathVariable String userId) {
		return adminservice.deleteuser(Integer.parseInt(userId));
	}
	
	
	
	
	
}
