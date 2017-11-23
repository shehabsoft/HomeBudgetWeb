package com.dataObjects;

public class PurchaseVO extends DataObjectVO {

	private String arabicDescription;
	private String englishDescription;
	private int categoryId;
	private int locationId;
	private String creationDate;
	private double price;
	private double newPrice;
	private String categoryName;
	private String locationName;
	private String details;
	private double totalPrice;
	private int approvedPurchaseId;
	private String totalPriceStr;
	private String creationDateStr;
	public double getTotalPrice() {
		
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getArabicDescription() {
		return arabicDescription;
	}

	public void setArabicDescription(String arabicDescription) {
		this.arabicDescription = arabicDescription;
	}

	public String getEnglishDescription() {
		return englishDescription;
	}

	public void setEnglishDescription(String englisDescription) {
		this.englishDescription = englisDescription;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	public int getApprovedPurchaseId() {
		return approvedPurchaseId;
	}

	public void setApprovedPurchaseId(int approvedPurchaseId) {
		this.approvedPurchaseId = approvedPurchaseId;
	}

	public String getTotalPriceStr() {
		return totalPriceStr;
	}

	public void setTotalPriceStr(String totalPriceStr) {
		this.totalPriceStr = totalPriceStr;
	}

	public String getCreationDateStr() {
		return creationDateStr;
	}

	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}
	

}
