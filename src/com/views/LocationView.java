package com.views;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.ws.Action;

import com.dataObjects.Constants;
import com.dataObjects.LocationVO;
import com.google.gson.Gson;
import com.models.Documents.LocationKeyBasedDocument;

import util.BusinessException;
import util.TransactionServiceParser;


@ManagedBean
@SessionScoped
public class LocationView extends JSFView {

	private LocationVO locationVO;
	private ArrayList<LocationVO> locationVOs=new ArrayList<LocationVO>();
	public LocationView() throws Exception
	{
		if(getUserVO()==null)
			return;
		locationVO=new LocationVO();
		try {
			locationVOs=getAllLocations();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	@Action
	public void add() throws BusinessException
	{
		String responseMessage="";
		try {
			String requestData="<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>"+Constants.ADD_LOCATION_SERVICE+"</serviceCode><arabicDescription>"+locationVO.getArabicName()+"</arabicDescription> <englishDescription>"+locationVO.getEnglishName()+"</englishDescription></createTransaction>]]>";
		    String response= callTransactionService(requestData);
		    TransactionServiceParser transactionServiceParser=new  TransactionServiceParser();
		    responseMessage=transactionServiceParser.parseCreateTransactionResponse(response);
	    	System.out.print(responseMessage);	
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			if(e instanceof BusinessException)
			{
				System.out.println(e);
				throw new BusinessException(e.toString());
			}

		}
		setStatus(true);
		setMessage(responseMessage);
		reset();
	
	}
	public void reset()
	{
		if(getLocationVO()!=null)
		{
			getLocationVO().setArabicName("");
			getLocationVO().setEnglishName("");
		}
		
	}

	@Override
	public void refesh() throws Exception {
		// TODO Auto-generated method stub
	}
	public LocationVO getLocationVO() {
		return locationVO;
	}
	public void setLocationVO(LocationVO locationVO) {
		this.locationVO = locationVO;
	}


	public ArrayList<LocationVO> getLocationVOs() {
		return locationVOs;
	}
	public void setLocationVOs(ArrayList<LocationVO> locationVOs) {
		this.locationVOs = locationVOs;
	}
	public ArrayList<LocationVO> getAllLocations() throws Exception
	{
		try {

			String output = "";
			System.out.println("Calling .........GetAllLocations.");
			output=callGetWebService("GetAllLocations");
			System.out.println("Output ..."+output);
			Gson gson=new Gson();
		    Object obj = gson.fromJson(output, LocationKeyBasedDocument.class);
		    LocationKeyBasedDocument locationKeyBasedDocument=(LocationKeyBasedDocument)obj;
		    ArrayList<LocationVO>locationsVOs=(ArrayList<LocationVO>)locationKeyBasedDocument.getLocationVO();
		    return locationsVOs;

	}catch(Exception e)
		{
		   throw new Exception(e);
		}
		}



    
	
}
