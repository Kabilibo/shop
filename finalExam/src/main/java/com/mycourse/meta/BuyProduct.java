package com.mycourse.meta;

public class BuyProduct {

	private int personId;
	private int id;
	private byte[] imageByte;
	private String title;
	private String image;
	private long buyTime;
	private int buySqlPrice;
	private double buyPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(long buyTime) {
		this.buyTime = buyTime;
	}
	
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public byte[] getImageByte() {
		return imageByte;
	}
	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getBuySqlPrice() {
		return buySqlPrice;
	}
	public void setBuySqlPrice(int buySqlPrice) {
		this.buySqlPrice = buySqlPrice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buySqlPrice/100.0;
	}
	
	
	
	
	
	
	
}
