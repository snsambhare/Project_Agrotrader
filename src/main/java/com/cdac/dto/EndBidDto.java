package com.cdac.dto;

public class EndBidDto {

	private String farmeremail;
	private int buyerid;
	private String subject;
	private String message;
	public String getFarmeremail() {
		return farmeremail;
	}
	public void setFarmeremail(String farmeremail) {
		this.farmeremail = farmeremail;
	}
	
	public int getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public EndBidDto(String farmeremail, String buyeremail, String subject, String message) {
		super();
		this.farmeremail = farmeremail;
		this.buyerid = buyerid;
		this.subject = subject;
		this.message = message;
	}
	public EndBidDto() {
		super();
	}
	
	
	
}
