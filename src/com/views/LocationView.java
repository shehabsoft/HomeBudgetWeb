package com.views;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
import com.google.gson.Gson;
import com.models.Documents.CategoriesKeyBasedDocument;

import util.BusinessException;
import util.TransactionServiceParser;


@ManagedBean
@RequestScoped
public class LocationView {

	
	public LocationView()
	{
     // getAllCategories();
	}
	public String message;
	private Boolean status;
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


	private String englishDescription;
	private String arabicDescription;
	

	

	
	
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
			
			TransactionService transactionService=null;
			TransactionServiceServiceLocator  serviceServiceLocator=new TransactionServiceServiceLocator();
			try {
				 transactionService=	serviceServiceLocator.getTransactionService();
				
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		String response=	transactionService.createTransaction("<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>3</serviceCode><arabicDescription>"+getArabicDescription()+"</arabicDescription> <englishDescription>"+getEnglishDescription()+"</englishDescription></createTransaction>]]>");
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
		
		setArabicDescription("");
		setEnglishDescription("");
	
		
	}
	public ArrayList<CategoryVO> getAllCategories()
	{
		try {

			
			String serviceUrl = "http://localhost:8080/WebServices/getData" + "/" + "GetAllCategories";
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
