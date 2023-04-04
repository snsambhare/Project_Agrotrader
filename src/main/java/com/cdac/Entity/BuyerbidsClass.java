package com.cdac.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="buyerbids")
public class BuyerbidsClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int goodsId;
	
	private double minimumPrice;
	private double quantity;
	private double buyerPrice;
	
	@ManyToOne
	@JoinColumn(name = "buyerId")
	private GoodsbuyerClass goodsbuyer;

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getBuyerPrice() {
		return buyerPrice;
	}

	public void setBuyerPrice(double buyerPrice) {
		this.buyerPrice = buyerPrice;
	}

	public GoodsbuyerClass getGoodsbuyer() {
		return goodsbuyer;
	}

	public void setGoodsbuyer(GoodsbuyerClass goodsbuyer) {
		this.goodsbuyer = goodsbuyer;
	}

	
	
	

	
}
