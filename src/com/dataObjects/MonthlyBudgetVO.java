package com.dataObjects;

public class MonthlyBudgetVO extends DataObjectVO{
	

	private String startDate;
	private String endDate;
	private String incomeCategoriesId;
	private String expenseCategoriesId;
	private double totalExpenses;
	private double totalIncomes;
	private String creationDate;
	private double completedRatio;
	private int categoryExpenseId;
	private int categoryIncomeId;
	private String[] categoryIncomeIds;
	private String[] categoryExpenseIds;
	private double totalExpectedExpenses;
	private double totalLimitExpenses;
	private double remaining;
	private double limitValue;
	private double actualValue;
	private double savingPlanned = 0;
	private double futureSaving=0;
	private Integer unApprovedPurchaseCount=0;
	public double getFutureSaving() {
		return futureSaving;
	}

	 

	public double getSavingPlanned() {
		return savingPlanned;
	}

	public void setSavingPlanned(double savingPlanned) {
		this.savingPlanned = savingPlanned;
	}

	public double getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(double limitValue) {
		this.limitValue = limitValue;
	}

	public double getActualValue() {
		return actualValue;
	}

	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}

	public double getTotalLimitExpenses() {
		return totalLimitExpenses;
	}

	public void setTotalLimitExpenses(double totalLimitExpenses) {
		this.totalLimitExpenses = totalLimitExpenses;
	}


	private double exceedLimit;
    private double savingValue;
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


	public int getCategoryExpenseId() {
		return categoryExpenseId;
	}


	public void setCategoryExpenseId(int categoryExpenseId) {
		this.categoryExpenseId = categoryExpenseId;
	}


	public double getTotalExpectedExpenses() {
		return totalExpectedExpenses;
	}


	public void setTotalExpectedExpenses(double totalExpectedExpenses) {
		this.totalExpectedExpenses = totalExpectedExpenses;
	}


	public int getCategoryIncomeId() {
		return categoryIncomeId;
	}


	public void setCategoryIncomeId(int categoryIncomeId) {
		this.categoryIncomeId = categoryIncomeId;
	}


	public String[] getCategoryIncomeIds() {
		return categoryIncomeIds;
	}


	public void setCategoryIncomeIds(String[] categoryIncomeIds) {
		this.categoryIncomeIds = categoryIncomeIds;
	}


	public String[] getCategoryExpenseIds() {
		return categoryExpenseIds;
	}


	public void setCategoryExpenseIds(String[] categoryExpenseIds) {
		this.categoryExpenseIds = categoryExpenseIds;
	}


	public double getCompletedRatio() {
		return completedRatio;
	}


	public void setCompletedRatio(double completedRatio) {
		this.completedRatio = completedRatio;
	}


	public String getIncomeCategoriesId() {
		return incomeCategoriesId;
	}


	public void setIncomeCategoriesId(String incomeCategoriesId) {
		this.incomeCategoriesId = incomeCategoriesId;
	}


	public double getTotalExpenses() {
		return totalExpenses;
	}


	public void setTotalExpenses(double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}


	public double getTotalIncomes() {
		return totalIncomes;
	}


	public void setTotalIncomes(double totalIncomes) {
		this.totalIncomes = totalIncomes;
	}



	public String getExpenseCategoriesId() {
		return expenseCategoriesId;
	}


	public void setExpenseCategoriesId(String expenseCategoriesId) {
		this.expenseCategoriesId = expenseCategoriesId;
	}



	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


	public double getRemaining() {
		return remaining;
	}

	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}

	public void setFutureSaving(double futureSaving2) {
		this.futureSaving = futureSaving2;
		
	}



	public Integer getUnApprovedPurchaseCount() {
		return unApprovedPurchaseCount;
	}



	public void setUnApprovedPurchaseCount(Integer unApprovedPurchaseCount) {
		this.unApprovedPurchaseCount = unApprovedPurchaseCount;
	}







	
}
