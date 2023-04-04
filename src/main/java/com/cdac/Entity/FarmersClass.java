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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "farmers")
public class FarmersClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int farmerId;
	
	@OneToOne
	@JoinColumn(name = "UserId")
	private UserdetailsClass userinfo;
	
	@OneToOne
	@JoinColumn(name = "OrderId")
	private CartdetailsClass cartdetail;

	@JsonIgnore
	@OneToMany(mappedBy = "farmer", cascade = CascadeType.REMOVE)
	private List<FarmgoodsClass> farmgoods;
	
	
	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public UserdetailsClass getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserdetailsClass userinfo) {
		this.userinfo = userinfo;
	}

	public CartdetailsClass getCartdetail() {
		return cartdetail;
	}

	public void setCartdetail(CartdetailsClass cartdetail) {
		this.cartdetail = cartdetail;
	}
	
	
}
