package com.cdac.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="farmgoods")
public class FarmgoodsClass {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int goodsId;
	
	@NotNull
	private String goodsName;
	
	@NotNull
	private double expectedPrice;
	
	@NotNull
	private double quantity;
	
	
	private double finalPrice=0.0;
	
	private String status ="UNSOLD";
	
	private String buyerId="None";
	
	private String prevbuyerId="None";
	private double prevFinalPrice=0.0;
	
	@ManyToOne
	@JoinColumn(name="farmerId")
	private FarmersClass farmer;

	
	
	
	
	
	public String getPrevbuyerId() {
		return prevbuyerId;
	}

	public void setPrevbuyerId(String prevbuyerId) {
		this.prevbuyerId = prevbuyerId;
	}

	public double getPrevFinalPrice() {
		return prevFinalPrice;
	}

	public void setPrevFinalPrice(double prevFinalPrice) {
		this.prevFinalPrice = prevFinalPrice;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(double expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public FarmersClass getFarmer() {
		return farmer;
	}

	public void setFarmer(FarmersClass farmer) {
		this.farmer = farmer;
	}

	
	
	
	
	
}
