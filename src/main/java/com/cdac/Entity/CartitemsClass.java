package com.cdac.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cartitems")
public class CartitemsClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ItemsId;
	
	private String ItemName;
	private double Price;
	
	@ManyToOne
	@JoinColumn(name ="OderId")
	private CartdetailsClass cartdetail;

	public int getItemsId() {
		return ItemsId;
	}

	public void setItemsId(int itemsId) {
		ItemsId = itemsId;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public CartdetailsClass getCartdetails() {
		return cartdetail;
	}

	public void setCartdetails(CartdetailsClass cartdetails) {
		this.cartdetail = cartdetails;
	}
	
}
