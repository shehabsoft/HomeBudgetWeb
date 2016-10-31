package com.dataObjects;

import java.util.Date;

public class PurchaseVO extends DataObjectVO{
	
	
	private String arabicDescription;
	
	private String englishDescription;



	private int categoryId;
	private int locationId;
	private String creationDate;
	private double price;
	 private String categoryName;
	 private String  locationName;
	 private String details;


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


}
