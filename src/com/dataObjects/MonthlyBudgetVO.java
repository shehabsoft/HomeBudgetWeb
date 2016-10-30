package com.dataObjects;

import java.util.Date;
import java.util.List;



public class MonthlyBudgetVO {
	
	private int Id;
	
	private String startDate;
	private String endDate;
	private String incomeCategoriesId;
	private String expenseCategoriesId;
	private double totalExpenses;
	private double totalIncomes;
	private String creationDate;
	private double completedRatio;
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





	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
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






	
}
