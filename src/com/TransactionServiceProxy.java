package com;

public class TransactionServiceProxy implements com.TransactionService {
  private String _endpoint = null;
  private com.TransactionService transactionService = null;
  
  public TransactionServiceProxy() {
    _initTransactionServiceProxy();
  }
  
  public TransactionServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initTransactionServiceProxy();
  }
  
  private void _initTransactionServiceProxy() {
    try {
      transactionService = (new com.TransactionServiceServiceLocator()).getTransactionService();
      if (transactionService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)transactionService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)transactionService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (transactionService != null)
      ((javax.xml.rpc.Stub)transactionService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.TransactionService getTransactionService() {
    if (transactionService == null)
      _initTransactionServiceProxy();
    return transactionService;
  }
  
  public java.lang.String helloName(java.lang.String name) throws java.rmi.RemoteException{
    if (transactionService == null)
      _initTransactionServiceProxy();
    return transactionService.helloName(name);
  }
  
  public java.lang.String createTransaction(java.lang.String xmlSata) throws java.rmi.RemoteException{
    if (transactionService == null)
      _initTransactionServiceProxy();
    return transactionService.createTransaction(xmlSata);
  }
  
  
}