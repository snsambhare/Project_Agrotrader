package com.cdac.Entity;

import java.util.List;

//import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fertilizerdealer")
public class FertilizerdealerClass {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int fdealerId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private UserdetailsClass userinfo;
	
	
	
	public int getFdealerId() {
		return fdealerId;
	}



	public void setFdealerId(int fdealerId) {
		this.fdealerId = fdealerId;
	}



	public UserdetailsClass getUserinfo() {
		return userinfo;
	}



	public void setUserinfo(UserdetailsClass userinfo) {
		this.userinfo = userinfo;
	}



	public List<AgromedsClass> getAgromedicines() {
		return agromedicines;
	}



	public void setAgromedicines(List<AgromedsClass> agromedicines) {
		agromedicines = agromedicines;
	}



	@OneToMany(mappedBy= "fdealerinfo" , cascade = CascadeType.ALL)
	private List<AgromedsClass> agromedicines;

}