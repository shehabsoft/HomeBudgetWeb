package com.dataObjects;


public class CategoryVO {
	
	private int Id;
	
	private String arabicDescription;
	
	private String englishDescription;

	private int parentCategoryId;
	
	private double limitValue;
	
	private double planedValue;
	
	private double actualValue;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public double getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(double limitValue) {
		this.limitValue = limitValue;
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

	public int getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}



	public double getPlanedValue() {
		return planedValue;
	}

	public void setPlanedValue(double planedValue) {
		this.planedValue = planedValue;
	}

	public double getActualValue() {
		return actualValue;
	}

	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}

	public int getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	int categoryStatus;
}
