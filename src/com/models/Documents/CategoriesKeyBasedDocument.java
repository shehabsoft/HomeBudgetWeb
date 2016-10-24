package com.models.Documents;


import java.util.List;

import com.dataObjects.CategoryVO;

public class CategoriesKeyBasedDocument {

	private List<CategoryVO>CategoryVO;

	public List<CategoryVO> getCategoryVO() {
		return CategoryVO;
	}

	public void setCategoryVO(List<CategoryVO> categoryVOs) {
		this.CategoryVO = categoryVOs;
	}

	
}
