package com.cdac.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.Entity.BiddingEntityClass;
import com.cdac.Entity.UserdetailsClass;
import com.cdac.repository.BiddingRepo;
import com.cdac.repository.UserdetailsRepo;

@Service
@Transactional
public class AdminService {

	@Autowired
	private UserdetailsRepo userrepo;
	
	@Autowired
	private BiddingRepo bidrepo;

	public List<UserdetailsClass> getActivefarmers() {
		return userrepo.fetchactivefarmers();
	}

	public List<UserdetailsClass> getActivebuyers() {
		return userrepo.fetchactivebuyers();
	}

	public List<UserdetailsClass> getdeactivatedusers() {
		return userrepo.fetchdeactivatedusers();
	}

	public String enableuser(int userId) {
		userrepo.enable(userId);
		return "User Activated";
	}

	public String disableuser(int userId) {
		userrepo.disable(userId);
		return "User Deactivated";
	}

	public List<BiddingEntityClass> getAllhistory() {
		return (List<BiddingEntityClass>) bidrepo.findAll() ;
	}

	public List<BiddingEntityClass> gettodaysBids() {
		LocalDate date = LocalDate.now();
		return bidrepo.fetchtodaysbids(date);
	}

	public String deleteuser(int userId) {
		userrepo.deleteById(userId);
		return "Deleted Successfully";
	}
}
