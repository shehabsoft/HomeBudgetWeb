package com.views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TransactionService;
import com.TransactionServiceServiceLocator;
import com.dataObjects.UserVO;

import util.BusinessException;
import util.Configurations;
import util.GlobalUtilities;

@ManagedBean
@SessionScoped
public abstract class JSFView {
   private  UserVO userVO;
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
		   if(userVO==null)
		   {
			   userVO=getCurrentUser();
			   
		   }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
  
   public UserVO getUserVO() throws Exception {
	   if(userVO!=null)
	   System.out.println("get Current User : "+userVO.getId());
	return getCurrentUser();
}

public void setUserVO(UserVO userVO) {
	 System.out.println("set Current User : "+userVO.getId());
	 this.userVO = userVO;
}

   public abstract void add() throws BusinessException, Exception;
   public abstract void refesh() throws Exception;
   public abstract void reset();
   
   public String callPostWebService(String webServiceName) throws Exception
   {
	    
	    String serviceUrl = backendUrl+ "/" + webServiceName;
		URL url = new URL(serviceUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if(userVO==null)
		   {
			   userVO=getCurrentUser();
			   System.out.println("Call Web Service Current User : "+userVO.getId());
		   }
		String userId="userId="+userVO.getId();
		System.out.println("Call Web Service Current User : "+userVO.getId());
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
   public String callPostWebService(String webServiceName,String ParamLabel,int paramVal) throws Exception
   {
	    
	    String serviceUrl = backendUrl+ "/" + webServiceName;
		URL url = new URL(serviceUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if(userVO==null)
		   {
			   userVO=getCurrentUser();
			   System.out.println("Call Web Service Current User : "+userVO.getId());
		   }
		String userId="userId="+userVO.getId();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty(ParamLabel, String.valueOf(paramVal));
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
	public UserVO getActiveUser() throws Exception
	{	
		try {

			HttpSession session = getSession(true);
			UserVO userVO=(UserVO)session.getAttribute("UserVo");
		    return userVO;

	}catch(Exception e)
		{
		   throw new Exception(e);
		}
		}
	
	public UserVO getCurrentUser() throws Exception
	{
		try {

			HttpSession session = getSession(true);
		 
			UserVO userVO=(UserVO)session.getAttribute("UserVo");
			if(userVO!=null)
			{
				System.out.println("Current User(getCurrentUser method) "+userVO.getId());
			}
		    return userVO;

	}catch(Exception e)
		{
		   throw new Exception(e);
		}
	}
	
    /**
     * Get related HttpServletRequest.
     *
     * @return Related HttpServletRequest.
     */
    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }
    /**
     * Get ExternalContext reference.
     *
     * @return ExternalContext reference.
     */
    public ExternalContext getExternalContext() {
        FacesContext fctx = getFacesContext();
        if (fctx == null) {
            return null;
        }

        return fctx.getExternalContext();
    }
    /**
     * Get FacesContext reference.
     *
     * @return FacesContext reference.
     */
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    /**
     * Returns the current HttpSession associated with this request or, if there is no current session and
     * create is true, returns a new session.
     *
     * @param create true to create a new session for this request if necessary; false to return null if
     *               there's no current session
     * @return the HttpSession associated with this request or null if create is false and the request has
     *         no valid session
     */
    public HttpSession getSession(boolean create) {
        return (HttpSession) getExternalContext().getSession(create);
    }
    
    /**
     * Returns the current HttpSession associated with this request
     * 
     * @return the HttpSession associated with this request
     */
    public HttpSession getSession() {
        return (HttpSession) getExternalContext().getSession( false );
    }

    /**
     * Redirect user reponse to requested path.
     *
     * @param path requested relative path.
     * @throws Exception 
     */
    public void sendRedirect(String path) throws Exception {
        sendRedirect(path, null);
    }
    /**
     * Redirect user reponse to requested path.
     *
     * @param path requested relative path.
     * @param parameters New request parameters.
     * @throws Exception 
     */
    public void sendRedirect(String path, Map parameters) throws Exception {
        // Validate path
        if (isBlankOrNull(path)) {
            throw new Exception("Missing path parameter");
        }

        // Build requested URL
        StringBuffer url = new StringBuffer(getRequest().getContextPath());

        if (!path.trim().startsWith("/")) {
            url.append("/");
        }

        url.append(path);

        try {
            // Check if parameters array is empty
            if (parameters == null || parameters.isEmpty()) {
                redirect(getResponse(), url.toString());
                return;
            }

            // Append parameters
            Iterator keys = parameters.keySet().iterator();
            while (keys.hasNext()) {
                Object name = keys.next();

                if (url.indexOf("?") > 0) {
                    url.append("&");
                } else {
                    url.append("?");
                }

                url.append(name);
                url.append("=");
                url.append(parameters.get(name));
            }

            redirect(getResponse(), url.toString());

        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    /**
     * Redirect to target URL.
     *
     * @param response Current HTTP response.
     * @param url target URL
     */
    private void redirect(HttpServletResponse response, String url) throws Exception {
        if (getExternalContext() != null) {
            getExternalContext().redirect(url);
            return;
        }

        response.sendRedirect(response.encodeRedirectURL(url.toString()));
    }

    /**
     * Get related HttpServletResponse.
     *
     * @return Related HttpServletResponse.
     */
    public HttpServletResponse getResponse() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }
    /**
     * Checks if the field is not null or contains only blank spaces.
     *
     * @param value String value to be checked.
     * @return true if the field is not null or blank.
     */
    public boolean isBlankOrNull(Object value) {
        return GlobalUtilities.isBlankOrNull(value);
    }
}
