package com.cdac.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cdac.Entity.BiddingEntityClass;

public interface BiddingRepo extends CrudRepository<BiddingEntityClass, Integer>{

	@Query(value="select * from bidsummary where buyer_id=:buyerId",nativeQuery = true)
	public List<BiddingEntityClass> getmybidhistory(int buyerId);

	@Query(value="select * from bidsummary where farmer_id=:farmerId", nativeQuery = true)
	public List<BiddingEntityClass> getfarmerbidhistory(int farmerId);

	@Query(value="select * from bidsummary where date=:date", nativeQuery = true)
	public List<BiddingEntityClass> fetchtodaysbids(LocalDate date);

	
}
