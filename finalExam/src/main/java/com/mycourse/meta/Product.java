package com.mycourse.meta;

public class Product {

	private int id;
	private String title;
	private String summary;
	private byte[] detailByte;
	private byte[] imageByte;
	private int sqlPrice;
	private double price;
	private String image;
	private String detail;
	private String isBuy = null;
	private String isSell = null;
	private double buyPrice;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(String isBuy) {
		this.isBuy = isBuy;
	}
	public String getIsSell() {
		return isSell;
	}
	public void setIsSell(String isSell) {
		this.isSell = isSell;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getDetailByte() {
		return detailByte;
	}
	public void setDetailByte(byte[] detailByte) {
		this.detailByte = detailByte;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getSqlPrice() {
		return sqlPrice;
	}
	public void setSqlPrice(int sqlPrice) {
		this.sqlPrice = sqlPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice() {
		this.price = sqlPrice/100.0;
	}
	
	
	

	
	
	
	
	
}
