package com.models.Documents;


import java.util.List;

import com.dataObjects.CategoryVO;
import com.dataObjects.CurrencyVO;
import com.dataObjects.PurchaseVO;

public class CurrenciesKeyBasedDocument extends KeyBasedDocument{

	private List<CurrencyVO>CurrencyVO;

	public List<CurrencyVO> getCurrencyVO() {
		return CurrencyVO;
	}

	public void setCurrencyVO(List<CurrencyVO> currencyVO) {
		CurrencyVO = currencyVO;
	}

	

	

	
}
