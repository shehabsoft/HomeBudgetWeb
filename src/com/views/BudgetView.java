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
import javax.faces.event.AjaxBehaviorEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.Action;
import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.TransactionService;
import com.TransactionServiceProxy;
import com.TransactionServiceService;
import com.TransactionServiceServiceLocator;
import com.TransactionServiceSoapBindingStub;
import com.dataObjects.CategoryHistoryVO;
import com.dataObjects.CategoryVO;
import com.dataObjects.Constants;
import com.dataObjects.MonthlyBudgetVO;
import com.dataObjects.PurchaseVO;
import com.dataObjects.UserVO;
import com.google.gson.Gson;
import com.models.Documents.CategoriesKeyBasedDocument;
import com.models.Documents.MonthlyBudgetKeyBasedDocument;
import com.models.Documents.PurchasesKeyBasedDocument;

import util.BusinessException;
import util.TransactionServiceParser;

@ManagedBean
@SessionScoped
public class BudgetView extends JSFView {

	private static final Logger logger = Logger.getLogger(BudgetView.class);
	private double exceedLimit = 0;
	private double savingLimit = 0;
	// for Charts
	private String incomes = "";
	private String expenses = "";
	private String dayString = "";
	private String totalPriceString = "";
	private String expensesLabelsString = "";
	private String expensesValuesString = "";
	private String dayStringByCategory = "";
	private String totalPriceStringByCategory = "";
	private double maxExpenses = 0;
	private List<CategoryHistoryVO> categoryHistoryList = new ArrayList<CategoryHistoryVO>();
	private String actualValueStr="";

	private int categoryId;
	// Generated by Map
	private Map<String, Object> categoryMap = new LinkedHashMap<String, Object>();

	public BudgetView() {
		super();
		try {
			logger.info("Initilizing BuggetView........");
			//userView = new UserView();
			// userVo=userView.getActiveUser();
			categoryList = categoryView.getCategoryList();
			for (CategoryVO categoryVO : categoryList) {
				categoryMap.put(categoryVO.getEnglishDescription(), categoryVO.getId()); // label,
																							// value
			}
			categoryIncomeList = categoryView.getCategoryIncomeList();
			categoryAllIncomeList = categoryView.getAllBudgetCategories();
			categoryAllList = categoryView.getAllExpensesCategories();
			monthlyBudgetVO = getActiveMonthlyBudgetByUserId();
			if (actionMode.equals(Constants.EDIT_ACTION_MODE)) {
				filterCategoeries();
			}
			getLimits(categoryList);
			monthlyBudgetVO.setExceedLimit(exceedLimit);
			monthlyBudgetVO.setSavingValue(savingLimit);
			monthlyBudgetVO.setTotalExpenses(getTotalExpenses(categoryList));
			monthlyBudgetVO.setTotalIncomes(getTotalIncomes(categoryIncomeList));
			monthlyBudgetVO.setTotalExpectedExpenses(getTotalExpectedExpenses(categoryList));
			monthlyBudgetVO.setRemaining(monthlyBudgetVO.getTotalIncomes() - monthlyBudgetVO.getTotalExpenses());
			monthlyBudgetVO.setTotalLimitExpenses(getTotalLimitExpenses(categoryList));
			monthlyBudgetVO.setCompletedRatio(
					(monthlyBudgetVO.getTotalExpenses() / monthlyBudgetVO.getTotalExpectedExpenses()) * 100);
			System.out.println("Ratio: " + monthlyBudgetVO.getCompletedRatio());
			style = "width:" + monthlyBudgetVO.getCompletedRatio() + "%";
			logger.info("Ratio: " + monthlyBudgetVO.getCompletedRatio());

			initializeChart();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
	}

	public Map<String, Object> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<String, Object> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public String getExpensesLabelsString() {
		return expensesLabelsString;
	}

	public void setExpensesLabelsString(String expensesLabelsString) {
		this.expensesLabelsString = expensesLabelsString;
	}

	public String getExpensesValuesString() {
		return expensesValuesString;
	}

	public void setExpensesValuesString(String expensesValuesString) {
		this.expensesValuesString = expensesValuesString;
	}

	private String actionMode = "";
	private String style;
	private MonthlyBudgetVO monthlyBudgetVO;
	private PurchaceView purchaceView = null;
	private List<CategoryVO> categoryIncomeList = new ArrayList<CategoryVO>();
	private List<CategoryVO> categoryAllIncomeList = new ArrayList<CategoryVO>();
	private List<PurchaseVO> monthlyChartPurchaseList = new ArrayList<PurchaseVO>();
	private List<PurchaseVO> monthlyChartPurchaseListByCategory = new ArrayList<PurchaseVO>();

	public List<PurchaseVO> getMonthlyChartPurchaseListByCategory() {
		return monthlyChartPurchaseListByCategory;
	}

	public void setMonthlyChartPurchaseListByCategory(List<PurchaseVO> monthlyChartPurchaseListByCategory) {
		this.monthlyChartPurchaseListByCategory = monthlyChartPurchaseListByCategory;
	}

	public List<PurchaseVO> getMonthlyChartPurchaseList() {
		return monthlyChartPurchaseList;
	}

	public void setMonthlyChartPurchaseList(List<PurchaseVO> monthlyChartPurchaseList) {
		monthlyChartPurchaseList = monthlyChartPurchaseList;
	}

//	private UserView userView;
	List<MonthlyBudgetVO> monthlyBudgetVOs = null;

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
	private CategoryView categoryView = new CategoryView();
	// Generated by Map
	private List<CategoryVO> categoryList = new ArrayList<CategoryVO>();
	private List<CategoryVO> categoryAllList = new ArrayList<CategoryVO>();

	public List<CategoryVO> getCategoryAllList() {
		return categoryAllList;
	}

	public void setCategoryAllList(List<CategoryVO> categoryAllList) {
		this.categoryAllList = categoryAllList;
	}

	private static Map<String, Object> categoryStatusList;
	private static Map<String, Object> categoryTypeList;
	static {
		categoryStatusList = new LinkedHashMap<String, Object>();
		categoryStatusList.put("Active", "2"); // label, value
		categoryStatusList.put("Canceled", "3");
		categoryStatusList.put("Suspended", "1");
		categoryTypeList = new LinkedHashMap<String, Object>();
		categoryTypeList.put("Revenues", "1");
		categoryTypeList.put("Expenses", "2");
	}

	public Map<String, Object> getCategoryStatusList() {
		return categoryStatusList;
	}

	public void setCategoryStatusList(Map<String, Object> categoryStatusList) {
		BudgetView.categoryStatusList = categoryStatusList;
	}

	public Map<String, Object> getCategoryTypeList() {
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

	public void refesh() throws Exception {
		try {
			logger.info("Calling Refresh........");
			categoryList = categoryView.getExpensesCategories();
			categoryIncomeList = categoryView.getBudgetCategories();
			categoryAllIncomeList = categoryView.getAllBudgetCategories();
			categoryAllList = categoryView.getAllExpensesCategories();
			monthlyBudgetVO = getActiveMonthlyBudgetByUserId();
			if (actionMode.equals(Constants.EDIT_ACTION_MODE)) {
				filterCategoeries();
			}
			getLimits(categoryList);
			monthlyBudgetVO.setExceedLimit(exceedLimit);
			monthlyBudgetVO.setSavingValue(savingLimit);
			monthlyBudgetVO.setTotalExpenses(getTotalExpenses(categoryList));
			monthlyBudgetVO.setTotalIncomes(getTotalIncomes(categoryIncomeList));
			monthlyBudgetVO.setTotalExpectedExpenses(getTotalExpectedExpenses(categoryList));
			monthlyBudgetVO.setTotalLimitExpenses(getTotalLimitExpenses(categoryList));
			monthlyBudgetVO.setCompletedRatio(
					(monthlyBudgetVO.getTotalExpenses() / monthlyBudgetVO.getTotalExpectedExpenses()) * 100);
			monthlyBudgetVO.setRemaining(monthlyBudgetVO.getTotalIncomes() - monthlyBudgetVO.getTotalExpenses());
			System.out.println(monthlyBudgetVO.getCompletedRatio());
			style = "width:" + monthlyBudgetVO.getCompletedRatio() + "%";
			logger.info("Ratio: " + monthlyBudgetVO.getCompletedRatio());
			initializeChart();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public double getTotalIncomes(List<CategoryVO> categoryVOs) {
		double tatalExpenses = 0;
		for (CategoryVO categoryVO : categoryVOs) {
			tatalExpenses += categoryVO.getActualValue();
		}
		return tatalExpenses;
	}

	public double getTotalExpectedExpenses(List<CategoryVO> categoryVOs) {
		double tatalExpenses = 0;
		for (CategoryVO categoryVO : categoryVOs) {
			tatalExpenses += categoryVO.getPlanedValue();
		}
		return tatalExpenses;
	}

	public double getTotalExpenses(List<CategoryVO> categoryVOs) throws Exception {
		double tatalExpenses = 0;
		for (CategoryVO categoryVO : categoryVOs) {
			tatalExpenses += categoryVO.getActualValue();
		}
		return tatalExpenses;
	}

	public double getTotalLimitExpenses(List<CategoryVO> categoryVOs) throws Exception {
		double totalLimitExpenses = 0;
		for (CategoryVO categoryVO : categoryVOs) {
			totalLimitExpenses += categoryVO.getLimitValue();
		}
		return totalLimitExpenses;
	}

	public double getLimits(List<CategoryVO> categoryVOs) throws Exception {

		exceedLimit = 0;
		savingLimit = 0;
		for (CategoryVO categoryVO : categoryVOs) {
			if (categoryVO.getActualValue() > categoryVO.getLimitValue()) {
				exceedLimit += categoryVO.getActualValue() - categoryVO.getLimitValue();
			} else {
				savingLimit += categoryVO.getLimitValue() - categoryVO.getActualValue();
			}
		}
		return exceedLimit;
	}

	public void filterCategoeries() {
		for (CategoryVO categoryVO : categoryIncomeList) {

			for (CategoryVO categoryVO2 : categoryAllIncomeList) {
				if (categoryVO2.getId() == categoryVO.getId()) {
					categoryAllIncomeList.remove(categoryVO2);
					break;
				}
			}
		}
		for (CategoryVO categoryVO : categoryList) {

			for (CategoryVO categoryVO2 : categoryAllList) {
				if (categoryVO2.getId() == categoryVO.getId()) {
					categoryAllList.remove(categoryVO2);
					break;
				}
			}
		}

	}

	public void initializeChart() throws Exception {
		incomes = "";
		expenses = "";
		monthlyBudgetVOs = getAllMonthlyBudgetByUserId();
		for (int i = 0; i < monthlyBudgetVOs.size(); i++) {
			if (i == monthlyBudgetVOs.size() - 1) {
				incomes += monthlyBudgetVOs.get(i).getTotalIncomes();
				expenses += monthlyBudgetVOs.get(i).getTotalExpenses();
			} else {
				incomes += monthlyBudgetVOs.get(i).getTotalIncomes() + ",";
				expenses += monthlyBudgetVOs.get(i).getTotalExpenses() + ",";
			}
		}
		// Daily Sales Chart
		monthlyChartPurchaseList = getMonthlyPurchases();
		dayString = "";
		totalPriceString = "";
		for (int i = 0; i < monthlyChartPurchaseList.size(); i++) {
			if (i == monthlyChartPurchaseList.size() - 1) {
				dayString += monthlyChartPurchaseList.get(i).getCreationDate().substring(8);
				totalPriceString += monthlyChartPurchaseList.get(i).getTotalPrice();
			} else {
				dayString += monthlyChartPurchaseList.get(i).getCreationDate().substring(8) + ",";
				totalPriceString += monthlyChartPurchaseList.get(i).getTotalPrice() + ",";
			}
		}
		// expenses Category Chart
		expensesLabelsString = "";
		expensesValuesString = "";
		for (int i = 0; i < categoryList.size(); i++) {
			if (i == categoryList.size() - 1) {
				expensesLabelsString += categoryList.get(i).getEnglishDescription() + "(" + i + ")";
				expensesValuesString += categoryList.get(i).getActualValue();
			} else {
				expensesLabelsString += categoryList.get(i).getEnglishDescription() + "(" + i + ") " + ",";
				expensesValuesString += categoryList.get(i).getActualValue() + ",";
			}
		}
		// monthlyChartPurchaseListByCategory Chart
		monthlyChartPurchaseListByCategory = getMonthlyPurchasesListByCategory();
		dayStringByCategory = "";
		totalPriceStringByCategory = "";
		for (int i = 0; i < monthlyChartPurchaseListByCategory.size(); i++) {
			if (i == monthlyChartPurchaseListByCategory.size() - 1) {
				dayString += monthlyChartPurchaseListByCategory.get(i).getCreationDate().substring(8);
				totalPriceString += monthlyChartPurchaseListByCategory.get(i).getTotalPrice();
			} else {
				dayString += monthlyChartPurchaseListByCategory.get(i).getCreationDate().substring(8) + ",";
				totalPriceString += monthlyChartPurchaseListByCategory.get(i).getTotalPrice() + ",";
			}
		}

	}

	public void ajaxChart(AjaxBehaviorEvent event) throws Exception {
		// monthlyChartPurchaseListByCategory Chart
		monthlyChartPurchaseListByCategory = getMonthlyPurchasesListByCategory();
		maxExpenses = getMaxExpensesPurchases(monthlyChartPurchaseListByCategory);
		dayStringByCategory = "";
		totalPriceStringByCategory = "";
		for (int i = 0; i < monthlyChartPurchaseListByCategory.size(); i++) {
			if (i == monthlyChartPurchaseListByCategory.size() - 1) {

				dayStringByCategory += monthlyChartPurchaseListByCategory.get(i).getCreationDate().substring(8);
				totalPriceStringByCategory += monthlyChartPurchaseListByCategory.get(i).getTotalPrice();

			} else {
				dayStringByCategory += monthlyChartPurchaseListByCategory.get(i).getCreationDate().substring(8) + ",";
				totalPriceStringByCategory += monthlyChartPurchaseListByCategory.get(i).getTotalPrice() + ",";
			}
		}
	}

	public double getMaxExpensesPurchases(List<PurchaseVO> monthlyChartPurchaseListByCategory) {
		if (monthlyChartPurchaseListByCategory != null && monthlyChartPurchaseListByCategory.size() > 0) {
			double max = monthlyChartPurchaseListByCategory.get(0).getTotalPrice();
			for (int i = 1; i < monthlyChartPurchaseListByCategory.size(); i++) {
				if (max < monthlyChartPurchaseListByCategory.get(i).getTotalPrice()) {
					max = monthlyChartPurchaseListByCategory.get(i).getTotalPrice();
				}
			}
			return max;
		} else {
			return 0;
		}
	}

	public ArrayList<PurchaseVO> getMonthlyPurchases() throws Exception {
		try {
			String output = "";
			System.out.println("Calling GetMonthlyPurchases  .... \n");
			output = callPostWebService("getMonthlyPurchases");
			System.out.println("Output From Server  .... " + output);
			System.out.println(" .... \n");
			Gson gson = new Gson();
			Object obj = gson.fromJson(output, PurchasesKeyBasedDocument.class);
			PurchasesKeyBasedDocument purchasesKeyBasedDocument = (PurchasesKeyBasedDocument) obj;
			ArrayList<PurchaseVO> purchaseVos = (ArrayList<PurchaseVO>) purchasesKeyBasedDocument.getPurchaseVO();
			return purchaseVos;

		} catch (Exception e)

		{
			throw new Exception(e);
		}
	}

	

	public ArrayList<PurchaseVO> getMonthlyPurchasesListByCategory() throws Exception {
		try {
			String output = "";
			System.out.println("Calling getMonthlyPurchasesListByCategory  .... \n");
			output = callPostWebService("getMonthlyPurchasesListByCategory", "CategoryId", categoryId);
			System.out.println("Output From Server  .... " + output);
			System.out.println(" .... \n");
			Gson gson = new Gson();
			Object obj = gson.fromJson(output, PurchasesKeyBasedDocument.class);
			PurchasesKeyBasedDocument purchasesKeyBasedDocument = (PurchasesKeyBasedDocument) obj;
			ArrayList<PurchaseVO> purchaseVos = (ArrayList<PurchaseVO>) purchasesKeyBasedDocument.getPurchaseVO();
			return purchaseVos;

		} catch (Exception e)

		{
			throw new Exception(e);
		}
	}

	public String getDayString() {
		return dayString;
	}

	public void setDayString(String dayString) {
		this.dayString = dayString;
	}

	public String getTotalPriceString() {
		return totalPriceString;
	}

	public void setTotalPriceString(String totalPriceString) {
		this.totalPriceString = totalPriceString;
	}

	public String getIncomes() {
		return incomes;
	}

	public void setIncomes(String incomes) {
		this.incomes = incomes;
	}

	public String getExpenses() {
		return expenses;
	}

	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}

	public void updateSelecedExpenseCategories() {

		for (int i = 0; i < this.monthlyBudgetVO.getCategoryExpenseIds().length; i++) {
			System.out.println("" + this.monthlyBudgetVO.getCategoryExpenseIds()[i]);
			boolean found = false;
			CategoryVO selectedCategoryVo = new CategoryVO();
			int expenseCategoryId = Integer.parseInt(this.monthlyBudgetVO.getCategoryExpenseIds()[i]);
			for (CategoryVO categoryVO : categoryList) {
				if (expenseCategoryId == categoryVO.getId()) {
					found = true;
					break;
				}
			}
			if (!found) {
				for (CategoryVO categoryVO : categoryAllList) {
					if (expenseCategoryId == categoryVO.getId()) {
						selectedCategoryVo = categoryVO;
						categoryList.add(selectedCategoryVo);
						categoryAllList.remove(selectedCategoryVo);
						break;
					}
				}
			}
		}
	}

	public void updateSelecedRevenuesCategories() {

		for (int i = 0; i < this.monthlyBudgetVO.getCategoryIncomeIds().length; i++) {
			System.out.println("" + this.monthlyBudgetVO.getCategoryIncomeIds()[i]);
			boolean found = false;
			CategoryVO selectedCategoryVo = new CategoryVO();
			int expenseCategoryId = Integer.parseInt(this.monthlyBudgetVO.getCategoryIncomeIds()[i]);
			for (CategoryVO categoryVO : categoryIncomeList) {
				if (expenseCategoryId == categoryVO.getId()) {
					found = true;
					break;
				}
			}
			if (!found) {
				for (CategoryVO categoryVO : categoryAllIncomeList) {
					if (expenseCategoryId == categoryVO.getId()) {
						selectedCategoryVo = categoryVO;
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

	public void edit() throws BusinessException {
		String[] selectedExpenseCategortyList = new String[categoryList.size()];
		int count = 0;
		for (CategoryVO categoryVO : categoryList) {
			selectedExpenseCategortyList[count] = String.valueOf(categoryVO.getId());
			count++;
		}
		monthlyBudgetVO.setCategoryExpenseIds(selectedExpenseCategortyList);
		String[] selectedRevenueCategortyList = new String[categoryIncomeList.size()];
		count = 0;
		for (CategoryVO categoryVO : categoryIncomeList) {
			selectedRevenueCategortyList[count] = String.valueOf(categoryVO.getId());
			count++;
		}
		monthlyBudgetVO.setCategoryIncomeIds(selectedRevenueCategortyList);

		edit(this.monthlyBudgetVO);
	}

	@Action
	public void showEditPage(ActionEvent event) throws BusinessException {
		actionMode = "edit";
		filterCategoeries();
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "editMonthlyBudget.xhtml");

	}

	@Action
	public void showAddPage(ActionEvent event) throws Exception {
		actionMode = "add";
		refesh();
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "addMonthlyBudget.xhtml");

	}

	public boolean edit(MonthlyBudgetVO selectedMonthlyBudgetVO) throws BusinessException {

		String responseMessage = "";
		String requestData = "";
		try {

			System.out.println("Calling Transaction Service Form Purchhase View(Edit Purchase)");
			requestData = "<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>"
					+ Constants.EDIT_MONTHLY_BUDGET_SERVICE + "</serviceCode><userId>" + getUserVO().getId()
					+ "</userId><monthlyBudgetId>" + selectedMonthlyBudgetVO.getId() + "</monthlyBudgetId><startDate>"
					+ selectedMonthlyBudgetVO.getStartDate() + "</startDate><endDate>"
					+ selectedMonthlyBudgetVO.getEndDate() + "</endDate><incomeCategoriesId>"
					+ Arrays.toString(selectedMonthlyBudgetVO.getCategoryIncomeIds())
					+ "</incomeCategoriesId><expenseCategoriesId>"
					+ Arrays.toString(selectedMonthlyBudgetVO.getCategoryExpenseIds())
					+ "</expenseCategoriesId></createTransaction>]]>";
			System.out.println("Request Data " + requestData);
			String response = callTransactionService(requestData);
			TransactionServiceParser transactionServiceParser = new TransactionServiceParser();
			responseMessage = transactionServiceParser.parseCreateTransactionResponse(response);
			System.out.println("response  Data " + responseMessage);
			System.out.print(responseMessage);

			setStatus(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setStatus(false);
			logger.error(e);
			if (e instanceof BusinessException) {
				System.out.println(e);
				throw new BusinessException(e.toString());
			}

		} finally {
			if (!getStatus()) {
				setMessage("Error");
			}

		}

		setMessage(responseMessage);

		return true;
	}

	@Action
	public boolean close() throws Exception {

		String responseMessage = "";
		String requestData = "";
		try {
			System.out.println("Calling Transaction Service Form Purchhase View(Edit Purchase)");
			Gson gson = new Gson();
			requestData = "<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>"
					+ Constants.CLOSE_MONTHLY_BUDGET_SERVICE + "</serviceCode><userId>" + getUserVO().getId()
					+ "</userId><monthlyBudgetId>" + this.getMonthlyBudgetVO().getId()
					+ "</monthlyBudgetId><incomeCategories>{CategoryVO:" + gson.toJson(getCategoryIncomeList())
					+ "}</incomeCategories></createTransaction>]]>";
			System.out.println("Request Data " + requestData);
			String response = callTransactionService(requestData);
			TransactionServiceParser transactionServiceParser = new TransactionServiceParser();
			responseMessage = transactionServiceParser.parseCreateTransactionResponse(response);
			System.out.println("response  Data " + responseMessage);
			System.out.print(responseMessage);

			setStatus(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setStatus(false);
			logger.error(e);
			if (e instanceof BusinessException) {
				System.out.println(e);
				throw new BusinessException(e.toString());
			}

		} finally {
			if (!getStatus()) {
				setMessage("Error");
			}

		}

		setMessage(responseMessage);
		refesh();
		return true;
	}

	@Action
	public void add() throws Exception {
		String responseMessage = "";
		try {
			String requestData = "<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?><createTransaction><serviceCode>"
					+ Constants.ADD_BUDGET_SERVICE + "</serviceCode><startDate>" + monthlyBudgetVO.getStartDate()
					+ "</startDate><endDate>" + monthlyBudgetVO.getEndDate() + "</endDate><incomeCategoriesId>"
					+ Arrays.toString(monthlyBudgetVO.getCategoryIncomeIds())
					+ "</incomeCategoriesId><expenseCategoriesId>"
					+ Arrays.toString(monthlyBudgetVO.getCategoryExpenseIds())
					+ "</expenseCategoriesId></createTransaction>]]>";
			String response = callTransactionService(requestData);
			System.out.println("Call Transaction Service For Location .....");
			TransactionServiceParser transactionServiceParser = new TransactionServiceParser();
			responseMessage = transactionServiceParser.parseCreateTransactionResponse(response);
			System.out.print(responseMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			if (e instanceof BusinessException) {
				System.out.println(e);
				throw new BusinessException(e.toString());
			}
		}
		setStatus(true);
		setMessage(responseMessage);
		reset();
		refesh();
	}

	public void reset() {
		setMonthlyBudgetVO(new MonthlyBudgetVO());

	}

	public MonthlyBudgetVO getActiveMonthlyBudgetByUserId() throws Exception {
		try {
			logger.info("Call getActiveMonthlyBudgetByUserId .....");
			String output = callPostWebService("getActiveMonthlyBudgetByUserId");
			System.out.println("Call getActiveMonthlyBudgetByUserId .....");
			Gson gson = new Gson();
			Object obj = gson.fromJson(output, MonthlyBudgetKeyBasedDocument.class);
			MonthlyBudgetKeyBasedDocument monthlyBudgetKeyBasedDocument = (MonthlyBudgetKeyBasedDocument) obj;
			MonthlyBudgetVO monthlyBudgetVO = (MonthlyBudgetVO) monthlyBudgetKeyBasedDocument.getMonthlyBudgetVO();
			return monthlyBudgetVO;

		} catch (Exception e) {

			logger.error(e);
			setStatus(false);
			if (e instanceof BusinessException) {
				System.out.println(e);
				throw new BusinessException(e.toString());
			} else {
				System.out.println(e);
				throw new Exception(e);
			}
		}
	}

	public List<MonthlyBudgetVO> getAllMonthlyBudgetByUserId() throws Exception {
		try {
			logger.info("Call getAllMonthlyBudgetByUserId .....");
			String output = callPostWebService("getAllMonthlyBudgetByUserId");
			System.out.println("Call getAllMonthlyBudgetByUserId .....");
			Gson gson = new Gson();
			Object obj = gson.fromJson(output, MonthlyBudgetKeyBasedDocument.class);
			MonthlyBudgetKeyBasedDocument monthlyBudgetKeyBasedDocument = (MonthlyBudgetKeyBasedDocument) obj;
			List<MonthlyBudgetVO> monthlyBudgetVOs = monthlyBudgetKeyBasedDocument.getMonthlyBudgetVOs();
			return monthlyBudgetVOs;

		} catch (Exception e) {

			logger.error(e);
			setStatus(false);
			if (e instanceof BusinessException) {
				System.out.println(e);
				throw new BusinessException(e.toString());
			} else {
				System.out.println(e);
				throw new Exception(e);
			}
		}
	}

	public List<MonthlyBudgetVO> getMonthlyBudgetVOs() {
		return monthlyBudgetVOs;
	}

	public void setMonthlyBudgetVOs(List<MonthlyBudgetVO> monthlyBudgetVOs) {
		this.monthlyBudgetVOs = monthlyBudgetVOs;
	}

	public String getTotalPriceStringByCategory() {
		return totalPriceStringByCategory;
	}

	public void setTotalPriceStringByCategory(String totalPriceStringByCategory) {
		this.totalPriceStringByCategory = totalPriceStringByCategory;
	}

	public String getDayStringByCategory() {
		return dayStringByCategory;
	}

	public void setDayStringByCategory(String dayStringByCategory) {
		this.dayStringByCategory = dayStringByCategory;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public double getMaxExpenses() {
		return maxExpenses;
	}

	public void setMaxExpenses(double maxExpenses) {
		this.maxExpenses = maxExpenses;
	}
	public String getActualValueStr() {
		return actualValueStr;
	}

	public void setActualValueStr(String actualValueStr) {
		this.actualValueStr = actualValueStr;
	}

}
