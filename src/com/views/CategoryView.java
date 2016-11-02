package com.views;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;

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
import com.dataObjects.Constants;
import com.dataObjects.PurchaseVO;
import com.google.gson.Gson;
import com.models.Documents.CategoriesKeyBasedDocument;

import util.BusinessException;
import util.TransactionServiceParser;


@ManagedBean
@SessionScoped
public class CategoryView extends JSFView {

	
	public CategoryView() 
	{
     try {
    	categoryVO=new CategoryVO();
    	if(categoryList.size()==0 || categoryIncomeList.size()==0)
    	{
		categoryList=getExpensesCategories();
		categoryIncomeList=getBudgetCategories();
		categoryAllList=getAllExpensesCategories();
		categoryAllIncomeList=getAllBudgetCategories();
    	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	}
	
	private CategoryVO categoryVO;
	private int categoryIncomeId;
	public int getCategoryIncomeId() {
		return categoryIncomeId;
	}

	public void setCategoryIncomeId(int categoryIncomeId) {
		this.categoryIncomeId = categoryIncomeId;
	}

	

	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}



	//Generated by Map
	private List<CategoryVO> categoryList=new ArrayList<CategoryVO>();
	private List<CategoryVO> categoryAllList=new ArrayList<CategoryVO>();
	private List<CategoryVO> categoryAllIncomeList=new ArrayList<CategoryVO>();
	public List<CategoryVO> getCategoryAllList() {
		return categoryAllList;
	}

	public void setCategoryAllList(List<CategoryVO> categoryAllList) {
		this.categoryAllList = categoryAllList;
	}

	public List<CategoryVO> getCategoryAllIncomeList() {
		return categoryAllIncomeList;
	}

	public void setCategoryAllIncomeList(List<CategoryVO> categoryAllIncomeList) {
		this.categoryAllIncomeList = categoryAllIncomeList;
	}

	private List<CategoryVO> categoryIncomeList=new ArrayList<CategoryVO>();
	public List<CategoryVO> getCategoryIncomeList() {
		return categoryIncomeList;
	}

	public void setCategoryIncomeList(List<CategoryVO> categoryIncomeList) {
		this.categoryIncomeList = categoryIncomeList;
	}
	private static Map<String,Object> categoryStatusList;
	private static Map<String,Object> categoryTypeList;
	static{
		categoryStatusList = new LinkedHashMap<String,Object>();
		categoryStatusList.put(Constants.CATEGORY_STATUS_LABEL_ACTIVE,Constants.CATEGORY_STATUS_VALUE_ACTIVE); //label, value
		categoryStatusList.put(Constants.CATEGORY_STATUS_LABEL_CANCELED,Constants.CATEGORY_STATUS_VALUE_CANCELED);
		categoryStatusList.put(Constants.CATEGORY_STATUS_LABEL_SUSPENDED, Constants.CATEGORY_STATUS_VALUE_SUSPENDED);
		categoryTypeList=new LinkedHashMap<String,Object>();
		categoryTypeList.put(Constants.CATEGORY_TYPE_LABEL_REVENUES, Constants.CATEGORY_TYPE_VALUE_REVENUES);
		categoryTypeList.put(Constants.CATEGORY_TYPE_LABEL_EXPENSES, Constants.CATEGORY_TYPE_VALUE_EXPENSES);
	}

	public  Map<String, Object> getCategoryStatusList() {
		return categoryStatusList;
	}

	public  void setCategoryStatusList(Map<String, Object> categoryStatusList) {
		CategoryView.categoryStatusList = categoryStatusList;
	}

	public  Map<String, Object> getCategoryTypeList() {
		return categoryTypeList;
	}

	public static void setCategoryTypeList(Map<String, Object> categoryTypeList) {
		CategoryView.categoryTypeList = categoryTypeList;
	}
	public void refesh() throws Exception
	{
		categoryList=getExpensesCategories();
		categoryIncomeList=getBudgetCategories();
		categoryAllList=getAllExpensesCategories();
		categoryIncomeList=getAllBudgetCategories();
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
		String responseMessage="";
		try {
			String requestData="<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>"+Constants.ADD_CATEGORY_SERVICE+"</serviceCode><userId>37</userId><arabicDescription>"+categoryVO.getArabicDescription()+"</arabicDescription> <englishDescription>"+categoryVO.getEnglishDescription()+"</englishDescription><limitValue>"+categoryVO.getLimitValue()+"</limitValue><planedValue>"+categoryVO.getPlanedValue()+"</planedValue><actualValue>"+categoryVO.getActualValue()+"</actualValue><categoryStatus>"+categoryVO.getCategoryStatus()+"</categoryStatus><categoryType>"+categoryVO.getCategoryTypeId()+"</categoryType></createTransaction>]]>";
	        String response=callTransactionService(requestData);
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
		if(getCategoryVO()!=null)
		{
		 setCategoryVO(new CategoryVO());
		}
		
	}
	public ArrayList<CategoryVO> getExpensesCategories() throws Exception
	{
		try {

			System.out.println("Call GetExpensesCategories ..........");
			String output=callPostWebService("GetExpensesCategories");
			System.out.println("Output .........."+output);
			Gson gson=new Gson();
		    Object obj = gson.fromJson(output, CategoriesKeyBasedDocument.class);
		    CategoriesKeyBasedDocument categoriesKeyBasedDocument=(CategoriesKeyBasedDocument)obj;
		    ArrayList<CategoryVO>categoryVOs=(ArrayList<CategoryVO>)categoriesKeyBasedDocument.getCategoryVO();
		    return categoryVOs;

	}catch(Exception e)
	{
       throw new Exception(e);
	}
	}
	public ArrayList<CategoryVO> getAllExpensesCategories() throws Exception
	{
		try {

			System.out.println("Call GetAllExpensesCategories ..........");
			String output=callPostWebService("GetAllExpensesCategories");
			System.out.println("Output .........."+output);
			Gson gson=new Gson();
		    Object obj = gson.fromJson(output, CategoriesKeyBasedDocument.class);
		    CategoriesKeyBasedDocument categoriesKeyBasedDocument=(CategoriesKeyBasedDocument)obj;
		    ArrayList<CategoryVO>categoryVOs=(ArrayList<CategoryVO>)categoriesKeyBasedDocument.getCategoryVO();
		    return categoryVOs;

	}catch(Exception e)
	{
       throw new Exception(e);
	}
	}
	public ArrayList<CategoryVO> getBudgetCategories()throws Exception
	{
		try {
			System.out.println("Call GetBudgetCategories ..........");
			String output=callPostWebService("GetBudgetCategories");
			System.out.println("Output .........."+output);
			Gson gson=new Gson();
		    Object obj = gson.fromJson(output, CategoriesKeyBasedDocument.class);
		    CategoriesKeyBasedDocument categoriesKeyBasedDocument=(CategoriesKeyBasedDocument)obj;
		    ArrayList<CategoryVO>categoryVOs=(ArrayList<CategoryVO>)categoriesKeyBasedDocument.getCategoryVO();
		    return categoryVOs;

          }catch(Exception e)
	       {
		    throw new Exception(e);
	       }
	}
	public ArrayList<CategoryVO> getAllBudgetCategories()throws Exception
	{
		try {
			System.out.println("Call GetAllBudgetCategories ..........");
			String output=callPostWebService("GetAllBudgetCategories");
			System.out.println("Output .........."+output);
			Gson gson=new Gson();
		    Object obj = gson.fromJson(output, CategoriesKeyBasedDocument.class);
		    CategoriesKeyBasedDocument categoriesKeyBasedDocument=(CategoriesKeyBasedDocument)obj;
		    ArrayList<CategoryVO>categoryVOs=(ArrayList<CategoryVO>)categoriesKeyBasedDocument.getCategoryVO();
		    return categoryVOs;

          }catch(Exception e)
	       {
		    throw new Exception(e);
	       }
	}
    
	
}
