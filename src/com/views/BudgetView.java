package com.views;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.Action;
import javax.xml.ws.WebServiceException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.TransactionService;
import com.TransactionServiceProxy;
import com.TransactionServiceService;
import com.TransactionServiceServiceLocator;
import com.TransactionServiceSoapBindingStub;

import com.dataObjects.CategoryVO;
import com.dataObjects.PurchaseVO;
import com.google.gson.Gson;
import com.models.Documents.CategoriesKeyBasedDocument;

import util.BusinessException;
import util.TransactionServiceParser;


@ManagedBean
@SessionScoped
public class BudgetView {

	
	public BudgetView()
	{
		 categoryList=getExpensesCategories();
	     categoryIncomeList=getBudgetCategories();
	}
	
	public String message;
	private Boolean status;
	private List<CategoryVO> categoryIncomeList=new ArrayList<CategoryVO>();
	private String[] categoryId;
	private String startDate; 
	private String endDate; 
	
	

	private String[] categoryIncomeId;

	public List<CategoryVO> getCategoryIncomeList() {
		return categoryIncomeList;
	}
	public String[] getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String[] categoryId) {
		this.categoryId = categoryId;
	}
	public void setCategoryIncomeList(List<CategoryVO> categoryIncomeList) {
		this.categoryIncomeList = categoryIncomeList;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String[] getCategoryIncomeId() {
		return categoryIncomeId;
	}
	public void setCategoryIncomeId(String[] categoryIncomeId) {
		this.categoryIncomeId = categoryIncomeId;
	}
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	private String englishDescription;
	private String arabicDescription;
	private double limitValue;
	private double planedValue;
	private double actualValue;
	private String categoryStatus;
	public int getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}
	private int categoryTypeId;

	//Generated by Map
	private List<CategoryVO> categoryList=new ArrayList<CategoryVO>();
	private static Map<String,Object> categoryStatusList;
	private static Map<String,Object> categoryTypeList;
	static{
		categoryStatusList = new LinkedHashMap<String,Object>();
		categoryStatusList.put("Active", "2"); //label, value
		categoryStatusList.put("Canceled", "3");
		categoryStatusList.put("Suspended", "1");
		categoryTypeList=new LinkedHashMap<String,Object>();
		categoryTypeList.put("Revenues", "1");
		categoryTypeList.put("Expenses", "2");
	}

	public  Map<String, Object> getCategoryStatusList() {
		return categoryStatusList;
	}

	public  void setCategoryStatusList(Map<String, Object> categoryStatusList) {
		BudgetView.categoryStatusList = categoryStatusList;
	}

	public  Map<String, Object> getCategoryTypeList() {
		return categoryTypeList;
	}

	public static void setCategoryTypeList(Map<String, Object> categoryTypeList) {
		BudgetView.categoryTypeList = categoryTypeList;
	}

	
	public String getEnglishDescription() {
		return englishDescription;
	}
	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}
	public String getArabicDescription() {
		return arabicDescription;
	}
	public void setArabicDescription(String arabicDescription) {
		this.arabicDescription = arabicDescription;
	}
	public double getLimitValue() {
		return limitValue;
	}
	public void setLimitValue(double limitValue) {
		this.limitValue = limitValue;
	}
	public double getPlanedValue() {
		return planedValue;
	}
	public void setPlanedValue(double planedValue) {
		this.planedValue = planedValue;
	}
	public double getActualValue() {
		return actualValue;
	}
	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}
	public String getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void refesh() throws Exception
	{
		this.categoryList=getBudgetCategories();
	}
	public List<CategoryVO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryVO> categoryList) {
		this.categoryList = categoryList;
	}

	@Action
	public void add() throws BusinessException
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
			
		String response=	transactionService.createTransaction("<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>5</serviceCode><startDate>"+getStartDate()+"</startDate><endDate>"+getEndDate()+"</endDate><incomeCategoriesId>"+Arrays.toString(getCategoryIncomeId())+"</incomeCategoriesId><expenseCategoriesId>"+Arrays.toString(getCategoryId())+"</expenseCategoriesId></createTransaction>]]>");
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
		status=true;
		message= responseMessage;
		reset();
	
	
	}
	
	public void reset()
	{
		setStartDate("");
		
		
	}
	public ArrayList<CategoryVO> getExpensesCategories()
	{
		try {

			
			String serviceUrl = "http://localhost:8080/WebServices/getData" + "/" + "GetExpensesCategories";
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
		    Object obj = gson2.fromJson(output, CategoriesKeyBasedDocument.class);
		    CategoriesKeyBasedDocument categoriesKeyBasedDocument=(CategoriesKeyBasedDocument)obj;
		    ArrayList<CategoryVO>categoryVOs=(ArrayList<CategoryVO>)categoriesKeyBasedDocument.getCategoryVO();
		    return categoryVOs;

	}catch(Exception e)
		{
		return null;
		}
		}
	public ArrayList<CategoryVO> getBudgetCategories()
	{
		try {

			
			String serviceUrl = "http://localhost:8080/WebServices/getData" + "/" + "GetBudgetCategories";
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
		    Object obj = gson2.fromJson(output, CategoriesKeyBasedDocument.class);
		    CategoriesKeyBasedDocument categoriesKeyBasedDocument=(CategoriesKeyBasedDocument)obj;
		    ArrayList<CategoryVO>categoryVOs=(ArrayList<CategoryVO>)categoriesKeyBasedDocument.getCategoryVO();
		    return categoryVOs;

	}catch(Exception e)
		{
		return null;
		}
		}
    
	
}
