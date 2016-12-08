package com.models.Documents;


import java.util.List;

import com.dataObjects.CategoryHistoryVO;

public class CategoryHistoryKeyBasedDocument  extends KeyBasedDocument{

	private List<CategoryHistoryVO>CategoryHistoryVO;

	public List<CategoryHistoryVO> getCategoryHistoryVO() {
		return CategoryHistoryVO;
	}

	public void setCategoryHistoryVO(List<CategoryHistoryVO> categoryHistoryVO) {
		CategoryHistoryVO = categoryHistoryVO;
	}



	

	
}
