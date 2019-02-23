package com.models.Documents;


import java.util.List;

import com.dataObjects.LocationVO;

public class LocationKeyBasedDocument extends KeyBasedDocument {

	private List<LocationVO>LocationVO;

	public List<LocationVO> getLocationVO() {
		return LocationVO;
	}

	public void setLocationVO(List<LocationVO> locationVO) {
		LocationVO = locationVO;
	}



	

	
}
