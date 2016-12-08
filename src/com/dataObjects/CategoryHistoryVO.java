/**
 * 
 */
package com.dataObjects;

import java.util.Date;



/**
 * @author Shehab
 *
 */
public class CategoryHistoryVO extends DataObjectVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date creationDate;
	private double  actualValue;
	private double plannedValue;
	private double limitValue;
	private String creation_date;
	private int categoryTypeId;
		public String getCreation_date() {
			return creation_date;
		}
		public void setCreation_date(String creation_date) {
			this.creation_date = creation_date;
		}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public double getActualValue() {
		return actualValue;
	}
	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}
	public double getPlannedValue() {
		return plannedValue;
	}
	public void setPlannedValue(double plannedValue) {
		this.plannedValue = plannedValue;
	}
	public double getLimitValue() {
		return limitValue;
	}
	public void setLimitValue(double limitValue) {
		this.limitValue = limitValue;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getCategoryTypeId() {
		return categoryTypeId;
	}
	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}
	
	



	}
	
	

