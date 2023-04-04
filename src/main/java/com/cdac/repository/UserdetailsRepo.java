package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.cdac.Entity.UserdetailsClass;

public interface UserdetailsRepo extends CrudRepository<UserdetailsClass, Integer>{

	@Query("select count(u) from UserdetailsClass u where userEmailId = ?1")
	public int finduser(String email);

	@Query(value="select * from userdetails where is_active=true and user_email_id=:email", nativeQuery = true)
	public UserdetailsClass getuser(String email);

	@Query(value="select * from userdetails u join goodsbuyer b where b.user_id=u.user_id and b.buyer_id=:buyerId",nativeQuery = true)
	public UserdetailsClass getbiddingbuyer(int buyerId);

	
	@Query(value="select * from userdetails where user_type='ROLE_FARMER' and is_active=true", nativeQuery=true)
	public List<UserdetailsClass> fetchactivefarmers();

	@Query(value="select * from userdetails where user_type='ROLE_BUYER' and is_active=true", nativeQuery= true)
	public List<UserdetailsClass> fetchactivebuyers();

	@Query(value="select * from userdetails where is_active=false",nativeQuery = true)
	public List<UserdetailsClass> fetchdeactivatedusers();

	@Modifying
	@Query(value="update userdetails set is_active=true where user_id=:userId",nativeQuery = true)
	public void enable(int userId);

	@Modifying
	@Query(value="update userdetails set is_active=false where user_id=:userId",nativeQuery = true)
	public void disable(int userId);

	
	
	@Query(value="select * from userdetails where user_email_id=:email", nativeQuery = true)
	public UserdetailsClass fetchUserByEmail(String email);

//	@Query(value="select count(*) from userdetails where user_email_id=:email and user_mobile_no=:mobileNo",nativeQuery = true)
//	public int getusertoUpdatepass(String email, String mobileNo);




}

