package com.dataObjects;


public class CategoryVO extends DataObjectVO{
	
	
	private String arabicDescription;
	
	private String englishDescription;

	private int parentCategoryId;
	
	private double limitValue;
	
	private double planedValue;
	private String actualValueStr;
	public String getActualValueStr() {
		return actualValueStr;
	}

	public void setActualValueStr(String actualValueStr) {
		this.actualValueStr = actualValueStr;
	}

	private double actualValue;
    private double exceedLimit;
    private double savingValue;
    private double newValue;
	public double getNewValue() {
		return newValue;
	}

	public void setNewValue(double newValue) {
		this.newValue = newValue;
	}

	public double getExceedLimit() {
		return exceedLimit;
	}

	public void setExceedLimit(double exceedLimit) {
		this.exceedLimit = exceedLimit;
	}

	public double getSavingValue() {
		return savingValue;
	}

	public void setSavingValue(double savingValue) {
		this.savingValue = savingValue;
	}

	private int categoryTypeId;
	private boolean withenLimit;
	 
	public boolean isWithenLimit() {
		return withenLimit;
	}

	public void setWithenLimit(boolean withenLimit) {
		this.withenLimit = withenLimit;
	}

	public int getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
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
