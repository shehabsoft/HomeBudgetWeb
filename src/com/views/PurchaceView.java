package com.views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.Action;

import com.TransactionService;
import com.TransactionServiceProxy;
import com.TransactionServiceService;
import com.TransactionServiceServiceLocator;
import com.TransactionServiceSoapBindingStub;
import com.dataObjects.CategoryVO;
import com.dataObjects.LocationVO;
import com.dataObjects.PurchaseVO;
import com.google.gson.Gson;
import com.models.Documents.CategoriesKeyBasedDocument;
import com.models.Documents.LocationKeyBasedDocument;
import com.models.Documents.PurchasesKeyBasedDocument;

import util.BusinessException;
import util.TransactionServiceParser;

@ManagedBean
@SessionScoped
public class PurchaceView {

	CategoryView categoryView=new CategoryView();
	private String arabicDescription;
	private String englishDescription;
	private double price;
	private int categoryId;
	private int locationId;
	private String detials;
	private List<PurchaseVO> purchaseList=new ArrayList<PurchaseVO>();
    private ArrayList<LocationVO> locationVOs=new ArrayList<LocationVO>();
	

	public PurchaceView() throws Exception
	{
		categoryVOs=categoryView.getExpensesCategories();
		for(CategoryVO categoryVO:categoryVOs)
		{
			categoryList.put(categoryVO.getEnglishDescription(),categoryVO.getId()); //label, value
		}
		purchaseList=getAllPurchases();
		locationVOs=getAllLocations();
		for(LocationVO categoryVO:locationVOs)
		{
			locationList.put(categoryVO.getEnglishName(),categoryVO.getId()); //label, value
		}
	}
	public Map<String, Object> getLocationList() {
		return locationList;
	}

	public void setLocationList(Map<String, Object> locationList) {
		this.locationList = locationList;
	}
	private Boolean status;
	public String getDetials() {
		return detials;
	}

	public void setDetials(String detials) {
		this.detials = detials;
	}

	private  ArrayList<CategoryVO> categoryVOs;
	//Generated by Map
	private Map<String,Object> categoryList=new LinkedHashMap<String,Object>();
	private Map<String,Object> locationList=new LinkedHashMap<String,Object>();

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Map<String,Object> getCategoryList() {
		return categoryList;
	}
	public String getArabicDescription() {
		return arabicDescription;
	}

	public void setArabicDescription(String arabicDescription) {
		this.arabicDescription = arabicDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public void setCategoryList(Map<String, Object> categoryList) {
		this.categoryList = categoryList;
	}
	public String getEnglishDescription() {
		return englishDescription;
	}
	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}
	public String message="Start Transaction..........";
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Action
	public void getAllCategories()
	{
		categoryVOs=categoryView.getExpensesCategories();
		for(CategoryVO categoryVO:categoryVOs)
		{
			categoryList.put(categoryVO.getEnglishDescription(),categoryVO.getId()); //label, value
		}
	}
	@Action
	public void getLocations() throws Exception
	{
		locationVOs=getAllLocations();
		for(LocationVO categoryVO:locationVOs)
		{
			locationList.put(categoryVO.getEnglishName(),categoryVO.getId()); //label, value
		}
	}
	@Action
	public void addPurchase() throws BusinessException
	{
	
		String content="";
		String responseMessage="";
		try {
			TransactionService transactionService=null;
			TransactionServiceServiceLocator  serviceServiceLocator=new TransactionServiceServiceLocator();
			try {
				 transactionService=	serviceServiceLocator.getTransactionService();
				
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		String response=	transactionService.createTransaction("<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>2</serviceCode><userId>37</userId><arabicDescription>"+getArabicDescription()+"</arabicDescription> <englishDescription>"+getEnglishDescription()+"</englishDescription><price>"+getPrice()+"</price><categoryId>"+getCategoryId()+"</categoryId><locationId>"+getLocationId()+"</locationId><details>"+getDetials()+"</details></createTransaction>]]>");
		TransactionServiceParser transactionServiceParser=new  TransactionServiceParser();
		responseMessage=transactionServiceParser.parseCreateTransactionResponse(response);
		
		System.out.print(responseMessage);	
		 
		status=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			status=false;
			
			if(e instanceof BusinessException)
			{
				System.out.println(e);
				throw new BusinessException(e.toString());
			}
			
			
		
		}finally
		{
			if(!status)
			{
				message="Error";
			}
			
		}
		
		message= responseMessage;
		reset();
	}
	public void reset()
	{
		setPrice(0);
		setArabicDescription("");
		setEnglishDescription("");
		setDetials("");
		
		
	}
	public void refesh() throws Exception
	{
		this.purchaseList=getAllPurchases();
	}
	public ArrayList<PurchaseVO> getAllPurchases() throws Exception
	{
		try {

			
			String serviceUrl = "http://localhost:8080/WebServices/getData" + "/" + "GetAllPurchases";
			URL url = new URL(serviceUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			Gson gson2 = new Gson();
			// String body="objectName="+className;

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
		    conn.setUseCaches(false);
		    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		    String output = "";
		    output = br.readLine();
		    Object obj = gson2.fromJson(output, PurchasesKeyBasedDocument.class);
		    PurchasesKeyBasedDocument purchasesKeyBasedDocument=(PurchasesKeyBasedDocument)obj;
		    ArrayList<PurchaseVO>purchaseVos=(ArrayList<PurchaseVO>)purchasesKeyBasedDocument.getPurchaseVO();
		    return purchaseVos;

	}catch(Exception e)
		
		{
		   throw new Exception(e);
		}
		}
	public ArrayList<LocationVO> getAllLocations() throws Exception
	{
		try {

			
			String serviceUrl = "http://localhost:8080/WebServices/getData" + "/" + "GetAllLocations";
			URL url = new URL(serviceUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			Gson gson2 = new Gson();
			// String body="objectName="+className;	

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
		    conn.setUseCaches(false);
		    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		    String output = "";
		    output = br.readLine();
		    Object obj = gson2.fromJson(output, LocationKeyBasedDocument.class);
		    LocationKeyBasedDocument locationKeyBasedDocument=(LocationKeyBasedDocument)obj;
		    ArrayList<LocationVO>locationsVOs=(ArrayList<LocationVO>)locationKeyBasedDocument.getLocationVO();
		    return locationsVOs;

	}catch(Exception e)
		
		{
		   throw new Exception(e);
		}
		}

	public List<PurchaseVO> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseVO> purchaseList) {
		this.purchaseList = purchaseList;
	}
	
}
