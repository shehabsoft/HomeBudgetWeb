package com.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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
import com.dataObjects.Constants;
import com.dataObjects.CountryVO;
import com.dataObjects.CurrencyVO;
import com.dataObjects.LocationVO;
import com.dataObjects.PurchaseVO;
import com.dataObjects.StatusVO;
import com.dataObjects.UserVO;
import com.google.gson.Gson;
import com.models.Documents.CategoriesKeyBasedDocument;
import com.models.Documents.CountriesKeyBasedDocument;
import com.models.Documents.CurrenciesKeyBasedDocument;
import com.models.Documents.LocationKeyBasedDocument;
import com.models.Documents.PurchasesKeyBasedDocument;
import com.models.Documents.StatusKeyBasedDocument;

import util.BusinessException;
import util.TransactionServiceParser;

@ManagedBean
@SessionScoped
public class UserView extends JSFView {


	public List<CurrencyVO> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<CurrencyVO> currencyList) {
		this.currencyList = currencyList;
	}

	public ArrayList<CountryVO> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<CountryVO> countryList) {
		this.countryList = countryList;
	}
    private UserVO userVO;
	


	
    private boolean emailExit=false;
	private String emailExitMessage="";
	private List<CurrencyVO> currencyList=new ArrayList<CurrencyVO>();
    private ArrayList<CountryVO> countryList=new ArrayList<CountryVO>();
	

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public UserView() throws Exception
	{
		userVO=new UserVO();
		currencyList=getAllCurrencies();
		for(CurrencyVO currencyVO:currencyList)
		{
			currencyMap.put(currencyVO.getName(),currencyVO.getId()); //label, value
		}
		
		countryList=getAllCountries();
		for(CountryVO countryVO:countryList)
		{
			countryMap.put(countryVO.getEnglishName(),countryVO.getId()); //label, value
		}
		emailExit=false;
	}

	private Boolean status;
	

	private  ArrayList<CategoryVO> categoryVOs;
	//Generated by Map
	private Map<String,Object> currencyMap=new LinkedHashMap<String,Object>();
	private Map<String,Object> countryMap=new LinkedHashMap<String,Object>();

	
	public Map<String,Object> getCategoryList() {
		return currencyMap;
	}
	
	
	
	public void setCategoryList(Map<String, Object> categoryList) {
		this.currencyMap = categoryList;
	}
	
	public String message="Start Transaction..........";
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Action
	public void add() throws BusinessException
	{
	
	
		String content="";
		String responseMessage="";
		try {
		String requestData="<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>"+Constants.ADD_USER_SERVICE+"</serviceCode><name>"+userVO.getName()+"</name><password>"+userVO.getPassword()+"</password><email>"+userVO.getEmail()+"</email><address>"+userVO.getAddress()+"</address><currencyId>"+userVO.getCurrencyId()+"</currencyId><countryId>"+userVO.getCountryId()+"</countryId><genderId>"+userVO.getGenderId()+"</genderId><statusId>"+userVO.getStatusId()+"</statusId><mobileNumber>"+userVO.getMobile_number()+"</mobileNumber></createTransaction>]]>";
		String response=callTransactionService(requestData);
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
		setUserVO(null);
		
		
	}
	
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public void refesh() throws Exception
	{
		this.currencyList=getAllCurrencies();
	}
	public ArrayList<CurrencyVO> getAllCurrencies() throws Exception
	{
		try {
		    String output = "";
		    Gson gson= new Gson();
		    System.out.println("Call GetAllCurrencies..........");
		    output = callGetWebService("GetAllCurrencies");
		    System.out.println("Output.........."+output);
		    Object obj = gson.fromJson(output, CurrenciesKeyBasedDocument.class);
		    CurrenciesKeyBasedDocument currenciesKeyBasedDocument=(CurrenciesKeyBasedDocument)obj;
		    ArrayList<CurrencyVO>currenciesVos=(ArrayList<CurrencyVO>)currenciesKeyBasedDocument.getCurrencyVO();
		    return currenciesVos;

	}catch(Exception e)
	{
		   throw new Exception(e);
	}
	}
	public ArrayList<CountryVO> getAllCountries() throws Exception
	{
		try {
			String output = "";
		    Gson gson= new Gson();
		    System.out.println("Call getAllCountries..........");
		    output = callGetWebService("getAllCountries");
		    Object obj = gson.fromJson(output, CountriesKeyBasedDocument.class);
		    CountriesKeyBasedDocument countriesKeyBasedDocument=(CountriesKeyBasedDocument)obj;
		    ArrayList<CountryVO>locationsVOs=(ArrayList<CountryVO>)countriesKeyBasedDocument.getCountryVO();
		    return locationsVOs;

	}catch(Exception e)
	{
		   throw new Exception(e);
	}
	}

	@Action
	public void checkEmail() throws IOException
	{
		String serviceUrl = "http://localhost:8080/WebServices/getData" + "/" + "checkEmail";
		URL url = new URL(serviceUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		Gson gson2 = new Gson();
		String mail="mail="+userVO.getEmail();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("email", userVO.getEmail());

		conn.setDoOutput(true);
		conn.setUseCaches(false);

		byte[] bytes = mail.getBytes();
		OutputStream out = conn.getOutputStream();
		out.write(bytes);
		if (conn.getResponseCode() != 200) {

			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
		String output = "";
		System.out.println("Output from Server .... \n");
		output = br.readLine();
	    Object obj = gson2.fromJson(output, StatusKeyBasedDocument.class);
	    StatusKeyBasedDocument statusKeyBasedDocument=(StatusKeyBasedDocument)obj;
	    StatusVO statusVO=(StatusVO)statusKeyBasedDocument.getStatusVO();
	    if(statusVO.isFlage()==true)
	    {
	    	emailExit=true;
	    	emailExitMessage="This Mail Exit In System";
	    }else
	    {
	    	emailExit=false;
	    	emailExitMessage="Good Mail";
	    }
	    
	}

	public boolean getEmailExit() {
		return emailExit;
	}

	public void setEmailExit(boolean emailCheck) {
		this.emailExit = emailCheck;
	}
	public String getEmailExitMessage() {
		return emailExitMessage;
	}

	public void setEmailExitMessage(String emailExitMessage) {
		this.emailExitMessage = emailExitMessage;
	}
	
}
