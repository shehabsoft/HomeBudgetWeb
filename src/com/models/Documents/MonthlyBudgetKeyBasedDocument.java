package com.models.Documents;


import java.util.List;

import com.dataObjects.CategoryVO;
import com.dataObjects.MonthlyBudgetVO;

public class MonthlyBudgetKeyBasedDocument extends KeyBasedDocument{

	private MonthlyBudgetVO MonthlyBudgetVO;

	public MonthlyBudgetVO getMonthlyBudgetVO() {
		return MonthlyBudgetVO;
	}

	public void setMonthlyBudgetVO(MonthlyBudgetVO monthlyBudgetVO) {
		MonthlyBudgetVO = monthlyBudgetVO;
	}



	
}
