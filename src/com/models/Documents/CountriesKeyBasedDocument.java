package com.models.Documents;


import java.util.List;

import com.dataObjects.CategoryVO;
import com.dataObjects.CountryVO;
import com.dataObjects.CurrencyVO;
import com.dataObjects.PurchaseVO;

public class CountriesKeyBasedDocument extends KeyBasedDocument{

	private List<CountryVO>CountryVO;

	public List<CountryVO> getCountryVO() {
		return CountryVO;
	}

	public void setCountryVO(List<CountryVO> countryVO) {
		CountryVO = countryVO;
	}

	

	

	

	
}
