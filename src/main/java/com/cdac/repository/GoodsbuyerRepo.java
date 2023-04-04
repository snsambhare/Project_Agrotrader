package com.cdac.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cdac.Entity.GoodsbuyerClass;

public interface GoodsbuyerRepo extends CrudRepository<GoodsbuyerClass, Integer>{

	@Query("select b from GoodsbuyerClass b join b.userinfo u where u.userEmailId =?1")
	public GoodsbuyerClass getBuyerByEmail(String email);

}
