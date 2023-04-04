package com.cdac.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="userdetails")
public class UserdetailsClass {

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	@NotNull
	private String userName;
	
	@Column(unique = true)
	private String userMobileNo;
	
	@Email
	@Column(unique = true)
	private String userEmailId;
	
	@NotNull
	private String userPassword;
	
	@NotNull
	private String city;
	@NotNull
	private String state;
	@NotNull
	private int pincode;
	@NotNull
	private String userType;
	
	private boolean isActive = true;
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
	@OneToOne(mappedBy ="userinfo", cascade = CascadeType.ALL)
	private FertilizerdealerClass fertilizerdealer;
	
	@JsonIgnore
	@OneToOne(mappedBy = "userinfo",cascade = CascadeType.REMOVE)
	private FarmersClass farmer;
	
	@JsonIgnore
	@OneToOne(mappedBy = "userinfo",cascade = CascadeType.REMOVE)
	private GoodsbuyerClass goodsbuyer;
	
	

	
	

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public FarmersClass getFarmer() {
		return farmer;
	}

	public void setFarmer(FarmersClass farmer) {
		this.farmer = farmer;
	}

	public GoodsbuyerClass getGoodsbuyer() {
		return goodsbuyer;
	}

	public void setGoodsbuyer(GoodsbuyerClass goodsbuyer) {
		this.goodsbuyer = goodsbuyer;
	}

	public FertilizerdealerClass getFertilizerdealer() {
		return fertilizerdealer;
	}

	public void setFertilizerdealer(FertilizerdealerClass fertilizerdealer) {
		this.fertilizerdealer = fertilizerdealer;
	}

	@Override
	public String toString() {
		return "UserdetailsClass [userId=" + userId + ", userName=" + userName + ", userMobileNo=" + userMobileNo
				+ ", userEmailId=" + userEmailId + ", userPassword=" + userPassword + ", city=" + city + ", state="
				+ state + ", pincode=" + pincode + ", userType=" + userType + ", fertilizerdealer=" + fertilizerdealer
				+ ", farmer=" + farmer + ", goodsbuyer=" + goodsbuyer + "]";
	}
	


}
