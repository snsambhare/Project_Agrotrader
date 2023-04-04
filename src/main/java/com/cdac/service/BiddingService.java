package com.cdac.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.Entity.BiddingEntityClass;
import com.cdac.repository.BiddingRepo;

@Service
@Transactional
public class BiddingService {

	@Autowired
	private BiddingRepo bidrepo;
	
	public void addbid(BiddingEntityClass bidsum) {
		bidrepo.save(bidsum);
		
	}

	

	
}
