package com.views;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.rpc.ServiceException;

import com.TransactionService;
import com.TransactionServiceServiceLocator;

import util.BusinessException;
import util.Configurations;

@ManagedBean
@SessionScoped
public abstract class JSFView {
   private static String backendUrl;
   private Configurations configurations;
   private String message;
   private Boolean status;
   public String getMessage() {
	return message;
}

public JSFView()
   {
	   configurations=new Configurations();
	   try {
		   backendUrl=configurations.initialize();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   public abstract void add() throws BusinessException;
   
   public abstract void refesh() throws Exception;
   public abstract void reset();
   
   public String callPostWebService(String webServiceName) throws Exception
   {
	    String serviceUrl = backendUrl+ "/" + webServiceName;
		URL url = new URL(serviceUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String userId="userId=37";
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("userId", userId);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		byte[] bytes = userId.getBytes();
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
	    return output;
	
   }
   public String callGetWebService(String webServiceName)throws Exception
   {
	    String serviceUrl =  backendUrl+ "/" +webServiceName ;
		URL url = new URL(serviceUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
	    conn.setUseCaches(false);
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	    String output = "";
	    output = br.readLine();
	    return output;
   }
   public String callTransactionService(String xml) throws Exception
   {
	  
			
			TransactionService transactionService=null;
			String response="";
			TransactionServiceServiceLocator  serviceServiceLocator=new TransactionServiceServiceLocator();
			try {
			 transactionService=serviceServiceLocator.getTransactionService();
		     response=	transactionService.createTransaction(xml);
		
			}catch(Exception e)
			{
				throw new Exception(e.toString());
			}
	       return response;
	   	
   }
   public String getBackendUrl() {
		return backendUrl;
	}
	public void setBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

}
