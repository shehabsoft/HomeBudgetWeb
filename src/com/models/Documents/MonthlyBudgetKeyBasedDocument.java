package com.models.Documents;


import java.util.List;

import com.dataObjects.CategoryVO;
import com.dataObjects.MonthlyBudgetVO;

public class MonthlyBudgetKeyBasedDocument extends KeyBasedDocument{

	private MonthlyBudgetVO MonthlyBudgetVO;
	private List<MonthlyBudgetVO> MonthlyBudgetVOs;

	public List<MonthlyBudgetVO> getMonthlyBudgetVOs() {
		return MonthlyBudgetVOs;
	}

	public void setMonthlyBudgetVOs(List<MonthlyBudgetVO> monthlyBudgetVOs) {
		MonthlyBudgetVOs = monthlyBudgetVOs;
	}

	public MonthlyBudgetVO getMonthlyBudgetVO() {
		return MonthlyBudgetVO;
	}

	public void setMonthlyBudgetVO(MonthlyBudgetVO monthlyBudgetVO) {
		MonthlyBudgetVO = monthlyBudgetVO;
	}



	
}
