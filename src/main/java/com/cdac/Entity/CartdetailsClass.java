package com.cdac.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="cartdetails")
public class CartdetailsClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderId;
	
	private LocalDate OrderDate;
	private LocalDate DeliverDate;
	private int TotalIem;
	private double TotalPrice;
	
	@OneToOne(mappedBy = "cartdetail", cascade = CascadeType.ALL)
	private FarmersClass farmer;
	
	
	@OneToMany(mappedBy = "cartdetail", cascade=CascadeType.REMOVE)
	private List<CartitemsClass> items;
	
	

	public FarmersClass getFarmer() {
		return farmer;
	}

	public void setFarmer(FarmersClass farmer) {
		this.farmer = farmer;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public LocalDate getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		OrderDate = orderDate;
	}

	public LocalDate getDeliverDate() {
		return DeliverDate;
	}

	public void setDeliverDate(LocalDate deliverDate) {
		DeliverDate = deliverDate;
	}

	public int getTotalIem() {
		return TotalIem;
	}

	public void setTotalIem(int totalIem) {
		TotalIem = totalIem;
	}

	public double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}

	public List<CartitemsClass> getItems() {
		return items;
	}

	public void setItems(List<CartitemsClass> items) {
		this.items = items;
	}
	
	
}
