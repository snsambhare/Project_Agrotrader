package com.cdac.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cdac.Entity.FarmersClass;

public interface FarmersRepo extends CrudRepository<FarmersClass, Integer> {

	
	@Query("select f from FarmersClass f join f.userinfo u where u.userEmailId = ?1")
	public FarmersClass getFarmerByEmail(String email);

}
