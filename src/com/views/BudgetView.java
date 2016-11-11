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
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
import com.dataObjects.MonthlyBudgetVO;
import com.dataObjects.PurchaseVO;
import com.google.gson.Gson;
import com.models.Documents.CategoriesKeyBasedDocument;
import com.models.Documents.MonthlyBudgetKeyBasedDocument;

import util.BusinessException;
import util.TransactionServiceParser;


@ManagedBean
@SessionScoped
public class BudgetView extends JSFView {

	
	public BudgetView()
	{	
		super();
		 try {
		 categoryList=categoryView.getCategoryList();
	     categoryIncomeList=categoryView.getCategoryIncomeList();
	     categoryAllIncomeList=categoryView.getAllBudgetCategories();
	     categoryAllList=categoryView.getAllExpensesCategories();
		 monthlyBudgetVO=getActiveMonthlyBudgetByUserId();
		 if(actionMode.equals(Constants.EDIT_ACTION_MODE))
		 {
			 filterCategoeries();
		 }
		 monthlyBudgetVO.setTotalExpectedExpenses(getTotalExpectedExpenses(categoryList));
	     monthlyBudgetVO.setCompletedRatio((monthlyBudgetVO.getTotalExpenses()/monthlyBudgetVO.getTotalExpectedExpenses())*100);
	     System.out.println("Ratio: "+monthlyBudgetVO.getCompletedRatio());
	     style ="width:"+monthlyBudgetVO.getCompletedRatio()+"%";
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private String actionMode="";
	private String style;
	private MonthlyBudgetVO monthlyBudgetVO;
	private PurchaceView  purchaceView =null;
	private List<CategoryVO> categoryIncomeList=new ArrayList<CategoryVO>();
	private List<CategoryVO> categoryAllIncomeList=new ArrayList<CategoryVO>();
	public List<CategoryVO> getCategoryAllIncomeList() {
		return categoryAllIncomeList;
	}
	public void setCategoryAllIncomeList(List<CategoryVO> categoryAllIncomeList) {
		this.categoryAllIncomeList = categoryAllIncomeList;
	}

	
	

	public MonthlyBudgetVO getMonthlyBudgetVO() {
		return monthlyBudgetVO;
	}
	public void setMonthlyBudgetVO(MonthlyBudgetVO monthlyBudgetVO) {
		this.monthlyBudgetVO = monthlyBudgetVO;
	}


	public List<CategoryVO> getCategoryIncomeList() {
		return categoryIncomeList;
	}
	public void setCategoryIncomeList(List<CategoryVO> categoryIncomeList) {
		this.categoryIncomeList = categoryIncomeList;
	}


	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}



	private String categoryStatus;
	public int getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}
	private int categoryTypeId;
    private CategoryView categoryView=new CategoryView();
	//Generated by Map
	private List<CategoryVO> categoryList=new ArrayList<CategoryVO>();
	private List<CategoryVO> categoryAllList=new ArrayList<CategoryVO>();
	public List<CategoryVO> getCategoryAllList() {
		return categoryAllList;
	}
	public void setCategoryAllList(List<CategoryVO> categoryAllList) {
		this.categoryAllList = categoryAllList;
	}
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



	public String getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}	

	
	public void refesh() throws Exception
	{
		 categoryList=categoryView.getExpensesCategories();
	     categoryIncomeList=categoryView.getBudgetCategories();
	     categoryAllIncomeList=categoryView.getAllBudgetCategories();
	     categoryAllList=categoryView.getAllExpensesCategories();
		 monthlyBudgetVO=getActiveMonthlyBudgetByUserId();
		 if(actionMode.equals(Constants.EDIT_ACTION_MODE))
		 {
			 filterCategoeries();
		 }
		 monthlyBudgetVO.setTotalExpenses(getTotalExpenses());
		 monthlyBudgetVO.setTotalIncomes(getTotalIncomes(categoryIncomeList));
		 monthlyBudgetVO.setTotalExpectedExpenses(getTotalExpectedExpenses(categoryList));
		 monthlyBudgetVO.setCompletedRatio((monthlyBudgetVO.getTotalExpenses()/monthlyBudgetVO.getTotalExpectedExpenses())*100);
	     System.out.println(monthlyBudgetVO.getCompletedRatio());
	     style ="width:"+monthlyBudgetVO.getCompletedRatio()+"%";
	}
	public double getTotalIncomes(List<CategoryVO> categoryVOs)
	{
		double tatalExpenses=0;
		for(CategoryVO categoryVO:categoryVOs)
		{
			tatalExpenses+=categoryVO.getActualValue();
		}
		return tatalExpenses;
	}
	public double getTotalExpectedExpenses(List<CategoryVO> categoryVOs)
	{
		double tatalExpenses=0;
		for(CategoryVO categoryVO:categoryVOs)
		{
			tatalExpenses+=categoryVO.getPlanedValue();
		}
		return tatalExpenses;
	}
	public double getTotalExpenses() throws Exception
	{
		purchaceView=new PurchaceView();
		List<PurchaseVO>purchaseVOs;
		if(purchaceView.getPurchaseList()==null)
		{
			purchaseVOs=purchaceView.getAllPurchases();
		}else
		{
			purchaseVOs=purchaceView.getPurchaseList();
		}
		double tatalExpenses=0;
		for(PurchaseVO purchaseVO:purchaseVOs)
		{
			tatalExpenses+=purchaseVO.getPrice();
		}
		return tatalExpenses;
	}
	public void filterCategoeries()
	{
		for(CategoryVO categoryVO :categoryIncomeList)
		{
			
			for(CategoryVO categoryVO2:categoryAllIncomeList)
			{
				if(categoryVO2.getId()==categoryVO.getId())
				{
					categoryAllIncomeList.remove(categoryVO2);
					break;
				}
			}
		}
		for(CategoryVO categoryVO :categoryList)
		{
			
			for(CategoryVO categoryVO2:categoryAllList)
			{
				if(categoryVO2.getId()==categoryVO.getId())
				{
					categoryAllList.remove(categoryVO2);
					break;
				}
			}
		}
		
		
	}
	public void updateSelecedExpenseCategories()
	{
		
		for(int i=0;i<this.monthlyBudgetVO.getCategoryExpenseIds().length;i++)
		{
		System.out.println(""+this.monthlyBudgetVO.getCategoryExpenseIds()[i]);
		boolean found=false;
		CategoryVO selectedCategoryVo=new CategoryVO();
		int expenseCategoryId=Integer.parseInt(this.monthlyBudgetVO.getCategoryExpenseIds()[i]);
				for(CategoryVO categoryVO:categoryList)
				{
					if(expenseCategoryId==categoryVO.getId())
					{
						found=true;
						break;
					}
				}
				if(!found)
				{
					for(CategoryVO categoryVO:categoryAllList)
					{
						if(expenseCategoryId==categoryVO.getId())
						{
							selectedCategoryVo=categoryVO;
							categoryList.add(selectedCategoryVo);
							categoryAllList.remove(selectedCategoryVo);
							break;
						}
					}
				}
		}
	}
	public void updateSelecedRevenuesCategories()
	{
		
		for(int i=0;i<this.monthlyBudgetVO.getCategoryIncomeIds().length;i++)
		{
		System.out.println(""+this.monthlyBudgetVO.getCategoryIncomeIds()[i]);
		boolean found=false;
		CategoryVO selectedCategoryVo=new CategoryVO();
		int expenseCategoryId=Integer.parseInt(this.monthlyBudgetVO.getCategoryIncomeIds()[i]);
				for(CategoryVO categoryVO:categoryIncomeList)
				{
					if(expenseCategoryId==categoryVO.getId())
					{
						found=true;
						break;
					}
				}
				if(!found)
				{
					for(CategoryVO categoryVO:categoryAllIncomeList)
					{
						if(expenseCategoryId==categoryVO.getId())
						{
							selectedCategoryVo=categoryVO;
							categoryIncomeList.add(selectedCategoryVo);
							categoryAllIncomeList.remove(selectedCategoryVo);
							break;
						}
					}
				}
		}
	}
	public List<CategoryVO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryVO> categoryList) {
		this.categoryList = categoryList;
	}
	public void edit() throws BusinessException
	{
		String[]selectedExpenseCategortyList=new String[categoryList.size()];
		int count=0;
		for(CategoryVO categoryVO:categoryList)
		{
			selectedExpenseCategortyList[count]=String.valueOf(categoryVO.getId());
			count++;
		}
		monthlyBudgetVO.setCategoryExpenseIds(selectedExpenseCategortyList);
		String[]selectedRevenueCategortyList=new String[categoryIncomeList.size()];
		count=0;
		for(CategoryVO categoryVO:categoryIncomeList)
		{
			selectedRevenueCategortyList[count]=String.valueOf(categoryVO.getId());
			count++;
		}
		monthlyBudgetVO.setCategoryIncomeIds(selectedRevenueCategortyList);
		
		edit(this.monthlyBudgetVO);
	}
	@Action
	public void showEditPage(ActionEvent event) throws BusinessException
	{
		actionMode="edit";
		filterCategoeries();
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "editMonthlyBudget.xhtml");
	
	}
	@Action
	public void showAddPage(ActionEvent event) throws Exception
	{
		actionMode="add";
		refesh();
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "addMonthlyBudget.xhtml");
	
	}
	public boolean edit(MonthlyBudgetVO selectedMonthlyBudgetVO ) throws BusinessException
	{
		
		String responseMessage="";
		String requestData="";
		try {
	

	
		System.out.println("Calling Transaction Service Form Purchhase View(Edit Purchase)");
		requestData="<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>"+Constants.EDIT_MONTHLY_BUDGET_SERVICE+"</serviceCode><userId>37</userId><monthlyBudgetId>"+selectedMonthlyBudgetVO.getId()+"</monthlyBudgetId><startDate>"+selectedMonthlyBudgetVO.getStartDate()+"</startDate><endDate>"+selectedMonthlyBudgetVO.getEndDate()+"</endDate><incomeCategoriesId>"+Arrays.toString(selectedMonthlyBudgetVO.getCategoryIncomeIds())+"</incomeCategoriesId><expenseCategoriesId>"+Arrays.toString(selectedMonthlyBudgetVO.getCategoryExpenseIds())+"</expenseCategoriesId></createTransaction>]]>";	
		System.out.println("Request Data "+requestData);
		String response=callTransactionService(requestData);
		TransactionServiceParser transactionServiceParser=new  TransactionServiceParser();
		responseMessage=transactionServiceParser.parseCreateTransactionResponse(response);
		System.out.println("response  Data "+responseMessage);
		System.out.print(responseMessage);	
		 
		setStatus(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			setStatus(false);
			
			if(e instanceof BusinessException)
			{
				System.out.println(e);
				throw new BusinessException(e.toString());
			}
			
			
		
		}finally
		{
			if(!getStatus())
			{
				setMessage("Error");
			}
			
		}
		
		setMessage(responseMessage);

		
		
		return true;
	}
	@Action
	public void add() throws BusinessException
	{
		String responseMessage="";
		try
		{
	    String requestData="<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>"+Constants.ADD_BUDGET_SERVICE+"</serviceCode><startDate>"+monthlyBudgetVO.getStartDate()+"</startDate><endDate>"+monthlyBudgetVO.getEndDate()+"</endDate><incomeCategoriesId>"+Arrays.toString(monthlyBudgetVO.getCategoryIncomeIds())+"</incomeCategoriesId><expenseCategoriesId>"+Arrays.toString(monthlyBudgetVO.getCategoryExpenseIds())+"</expenseCategoriesId></createTransaction>]]>";
		String response=callTransactionService(requestData);
		System.out.println("Call Transaction Service For Location .....");
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
		setStatus(true);;
		setMessage(responseMessage);
		reset();
	}
	
	public void reset()
	{
		setMonthlyBudgetVO(new MonthlyBudgetVO());
		
		
	}
	public MonthlyBudgetVO getActiveMonthlyBudgetByUserId() throws  Exception
	{
		try {

			String output =callPostWebService("getActiveMonthlyBudgetByUserId");
			System.out.println("Call getActiveMonthlyBudgetByUserId .....");
			Gson gson=new Gson();
		    Object obj = gson.fromJson(output, MonthlyBudgetKeyBasedDocument.class);
		    MonthlyBudgetKeyBasedDocument monthlyBudgetKeyBasedDocument=(MonthlyBudgetKeyBasedDocument)obj;
		    MonthlyBudgetVO monthlyBudgetVO=(MonthlyBudgetVO)monthlyBudgetKeyBasedDocument.getMonthlyBudgetVO();
		    return monthlyBudgetVO;

	}catch(Exception e)
		{
		             setStatus(false);
					if(e instanceof BusinessException)
					{
						System.out.println(e);
						throw new BusinessException(e.toString());
					}else
					{
						System.out.println(e);
						throw new Exception(e);
					}
		}
		}

    
	
}
