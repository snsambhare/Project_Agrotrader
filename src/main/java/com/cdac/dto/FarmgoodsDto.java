package com.cdac.dto;

public class FarmgoodsDto {

	private String userEmailId;
	private String goodsName;
	private double expectedPrice;
	private double quantity;
	
	
	
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
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
	
	
	
}
