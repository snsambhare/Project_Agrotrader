package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Entity.FarmgoodsClass;

public interface FarmgoodsRepo extends CrudRepository<FarmgoodsClass, Integer> {

	@Query(value = "select * from farmgoods where goods_id=:id", nativeQuery = true)
	public FarmgoodsClass getsingle(int id);

	@Query(value = "select * from farmgoods where status='UNSOLD' and farmer_id=:id", nativeQuery = true)
	public List<FarmgoodsClass> GetGoodsByFId(int id);

	@Query(value = "select * from farmgoods where status='UNSOLD' and farmer_id in(select farmer_id from userdetails, farmers where state=:state and userdetails.user_id=farmers.user_id)", nativeQuery = true)
	public List<FarmgoodsClass> getGoodsByState(String state);

	@Query(value = "select * from farmgoods where buyer_id=:buyerId and status='UNSOLD'", nativeQuery = true)
	public List<FarmgoodsClass> getbids(int buyerId);

	@Modifying
	@Transactional
	@Query(value = "delete from farmgoods where goods_id=:id", nativeQuery = true)
	public void deletegoods(int id);

}
