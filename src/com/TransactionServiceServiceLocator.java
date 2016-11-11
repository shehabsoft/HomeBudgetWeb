/**
 * TransactionServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com;

import util.Configurations;

public class TransactionServiceServiceLocator extends org.apache.axis.client.Service implements com.TransactionServiceService {

    public TransactionServiceServiceLocator() {
    }


    public TransactionServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TransactionServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }
    Configurations configurations;
    // Use to get a proxy class for TransactionService
    private java.lang.String TransactionService_address ;

    public java.lang.String getTransactionServiceAddress() {
        return TransactionService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TransactionServiceWSDDServiceName = "TransactionService";

    public java.lang.String getTransactionServiceWSDDServiceName() {
        return TransactionServiceWSDDServiceName;
    }

    public void setTransactionServiceWSDDServiceName(java.lang.String name) {
        TransactionServiceWSDDServiceName = name;
    }

    public com.TransactionService getTransactionService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
        	configurations=new Configurations();
        	configurations.initialize();
        	TransactionService_address=configurations.getTransactionBackendUrl();
            endpoint = new java.net.URL(TransactionService_address);
        }
        catch ( Exception e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTransactionService(endpoint);
    }

    public com.TransactionService getTransactionService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.TransactionServiceSoapBindingStub _stub = new com.TransactionServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getTransactionServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTransactionServiceEndpointAddress(java.lang.String address) {
        TransactionService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.TransactionService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.TransactionServiceSoapBindingStub _stub = new com.TransactionServiceSoapBindingStub(new java.net.URL(TransactionService_address), this);
                _stub.setPortName(getTransactionServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TransactionService".equals(inputPortName)) {
            return getTransactionService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://com", "TransactionServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://com", "TransactionService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TransactionService".equals(portName)) {
            setTransactionServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
