package com.cdac.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="agromeds")
public class AgromedsClass {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int medsId;
	private String medsName;
	private double medsPrice;
	private int medsQuantity;
	
	@ManyToOne
	@JoinColumn(name="fdealerId")
	private FertilizerdealerClass fdealerinfo;

	public int getMedsId() {
		return medsId;
	}

	public void setMedsId(int medsId) {
		this.medsId = medsId;
	}

	public String getMedsName() {
		return medsName;
	}

	public void setMedsName(String medsName) {
		this.medsName = medsName;
	}

	public double getMedsPrice() {
		return medsPrice;
	}

	public void setMedsPrice(double medsPrice) {
		this.medsPrice = medsPrice;
	}

	public int getMedsQuantity() {
		return medsQuantity;
	}

	public void setMedsQuantity(int medsQuantity) {
		this.medsQuantity = medsQuantity;
	}

	public FertilizerdealerClass getFdealerinfo() {
		return fdealerinfo;
	}

	public void setFdealerinfo(FertilizerdealerClass fdealerinfo) {
		this.fdealerinfo = fdealerinfo;
	}
	
	
}
	