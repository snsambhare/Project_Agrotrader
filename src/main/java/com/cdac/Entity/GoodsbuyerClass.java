package com.cdac.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="goodsbuyer")
public class GoodsbuyerClass {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int BuyerId;
	
	@OneToOne
	@JoinColumn(name = "UserId")
	private UserdetailsClass userinfo;
	
	@OneToMany(mappedBy = "goodsbuyer", cascade=CascadeType.REMOVE)
	private List<BuyerbidsClass> Allbids;

	public int getBuyerId() {
		return BuyerId;
	}

	public void setBuyerId(int buyerId) {
		BuyerId = buyerId;
	}

	public UserdetailsClass getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserdetailsClass userinfo) {
		this.userinfo = userinfo;
	}

	public List<BuyerbidsClass> getAllbids() {
		return Allbids;
	}

	public void setAllbids(List<BuyerbidsClass> allbids) {
		Allbids = allbids;
	}

	 
	
	
	
}
