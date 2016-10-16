/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  19/02/2008  - File created.
 */
package util;


import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import java.net.URLDecoder;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Used to encapsulate common functionality needed by SOAP clients.
 *
 * @author  shehab eldin tarek
 * @version 1.00
 */
public abstract class SoapUtils {
    /* *************************************************************************
     * **************** Getter methods (no validation errors) ******************
     * *************************************************************************
     */

    /**
     * Get Node by tag name.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @return Node object or null if the node not found.
     */
    public static Node getNodeByTagName(Element parent, String name) {
        if (parent == null) {
            return null;
        }

        NodeList nodeList = parent.getElementsByTagName(name);
        if (nodeList == null || nodeList.getLength() == 0) {
            return null;
        }

        return nodeList.item(0);
    }

    /**
     * Get Node first child by tag name.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @return Node object or null if the node not found.
     */
    public static Node getNodeChildByTagName(Element parent, String name) {
        Node node = getNodeByTagName(parent, name);
        if (node  == null) {
            return null;
        }

        if (! node.hasChildNodes()) {
            return null;
        }

        return node.getFirstChild();
    }

    /**
     * Get element first child by tag name.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @return Node object or null if the node not found.
     */
    public static Element getElementChildByTagName(Element parent, String name) {
        Node node = getNodeChildByTagName(parent, name);
        if (node == null) {
            return null;
        }

        if (node.getNodeType() != Node.ELEMENT_NODE) {
            return null;
        }

        return (Element) node;
    }

    /**
     * Get attribute value.
     * 
     * @param parent XML parent element.
     * @param tagName Element tag name.
     * @param attrName AttributeName.
     * @return attribute value.
     */
    public static String getAttribute(Element parent, String tagName, String attrName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList == null || nodeList.getLength() == 0) {
            return null;
        }

        Node node = nodeList.item(0);
        if (! node.hasChildNodes()) {
            return null;
        }

        NamedNodeMap attributes = node.getAttributes();
        if (attributes == null || attributes.getLength() == 0) {
            return null;
        }

        Node attr = attributes.getNamedItem(attrName);
        if (attr == null) {
            return null;
        }

        return attr.getNodeValue();
    }

    /**
     * Check if the node exists.
     *                        
     * @param parent XML parent element.
     * @param name Node tag name.
     * @return true if the node exists.
     */
    public static boolean isNodeHasChild(Element parent, String name) {
        return getNodeChildByTagName(parent , name) != null;
    }

    /**
     * Check if the node exists.
     *                        
     * @param parent XML parent element.
     * @param name Node tag name.
     * @return true if the node exists.
     */
    public static boolean isNodeExists(Element parent, String name) {
        return getNodeByTagName(parent , name) != null;
    }

    /**
     * Check if the element has a child.
     *                        
     * @param parent XML parent element.
     * @param name Element tag name.
     * @return true if the element exists and has at least one child.
     */
    public static boolean isElementHasChild(Element parent, String name) {
        return getElementChildByTagName(parent , name) != null;
    }

    /* *************************************************************************
     * ********** Validation methods (may generate validation errors) **********
     * *************************************************************************
     */

    /**
     * Check if the node exists and its value is not null or blank.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @return Node String value.
     */
    public static String parseStringNode(Element parent, String name) {
        // Check if the element exists
        Node node = getNodeChildByTagName(parent, name);
        if (node == null) {
            throw new WebServiceException(new StringBuffer(name)
              .append(" element not found").toString());
        }

        String value = node.getNodeValue();
        if (value == null || value.trim().length() == 0) {
            throw new WebServiceException(new StringBuffer(name)
              .append(" element is empty").toString());
        }

        return value.trim();
    }

    /**
     * Get integer value of this node.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @return integer value of this node.
     */
    public static int parseIntNode(Element parent, String name) {
        String value = parseStringNode(parent, name);
        try {
            return Integer.parseInt(value);

        } catch (NumberFormatException ex) {
            throw new WebServiceException(new StringBuffer("Invalid int value for ")
              .append(name).append(" element tag: ").append(value).toString());
        }
    }

    /**
     * Get long value of this node.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @return long value of this node.
     */
    public static long parseLongNode(Element parent, String name) {
        String value = parseStringNode(parent, name);
        try {
            return Long.parseLong(value);

        } catch (NumberFormatException ex) {
            throw new WebServiceException(new StringBuffer("Invalid long value for ")
              .append(name).append(" element tag: ").append(value).toString());
        }
    }

    /**
     * Get float value of this node.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @return float value of this node.
     */
    public static float parseFloatNode(Element parent, String name) {
        String value = parseStringNode(parent, name);
        try {
            return Float.parseFloat(value);

        } catch (NumberFormatException ex) {
            throw new WebServiceException(
                    new StringBuffer("Invalid float number value for ")
                     .append(name).append(" element tag: ").append(value)
                       .toString());
        }
    }

    /**
     * Get date value of this node.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @param format Date format expected for this node value.
     * @return date value of this node.
     */
    public static Date parseDateNode(Element parent, String name, 
                SimpleDateFormat format) {
        // Ge String node value to be parsed
        String value = parseStringNode(parent, name);
        try {
            Date newValue = format.parse(value);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH,1);
            calendar.set(Calendar.MONTH,Calendar.JANUARY);
            calendar.set(Calendar.YEAR,1800);
            
            if (newValue.compareTo(calendar.getTime()) < 0)  {
                throw new WebServiceException("Invalid Date");
            }
            
            return newValue;
            
        } catch (ParseException ex) {
            throw new WebServiceException(
                    new StringBuffer("Invalid Date format for tag ")
                      .append(name).append(": ").append(value).toString());
        } catch (Exception ex) {
            throw new WebServiceException(
                    new StringBuffer("Invalid Date format for tag ")
                      .append(name).append(": ").append(value).toString());
        }
    }

    /**
     * Get date value of this node. The DEFAULT_DATE_FORMAT will be used to 
     * parse this node value.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @param format Date format expected for this node value.
     * @return date value of this node.
     */
    public static Date parseDateNode(Element parent, String name) {
        return parseDateNode(parent, name, GlobalUtilities.DATE_FORMAT);
    }

    /**
     * Get attribute String value.
     * 
     * @param parent XML parent element.
     * @param tagName Element tag name.
     * @param attrName AttributeName.
     * @return attribute String value.
     */
    public static String parseStringAttribute(Element parent, String tagName, 
           String attrName) {
        // Error message
        StringBuffer err = new StringBuffer("Error validating ")
                          .append(tagName).append(" tag attribute [")
                          .append(attrName).append("] : ");

        // Get tag name
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList == null || nodeList.getLength() == 0) {
            throw new WebServiceException(
              err.append("Element not found").toString());
        }

        Node node = nodeList.item(0);
        NamedNodeMap attributes = node.getAttributes();
        if (attributes == null || attributes.getLength() == 0) {
            throw new WebServiceException(
              err.append("Element has no attribute").toString());
        }

        Node attr = attributes.getNamedItem(attrName);
        if (attr == null) {
            throw new WebServiceException(
              err.append("Element has no such attribute").toString());
        }

        String value = attr.getNodeValue();
        if (value == null || value.trim().length() == 0) {
            throw new WebServiceException(
              err.append("Empty attribute value").toString());
        }

        return value.trim();
    }

    /**
     * Get attribute integer value.
     * 
     * @param parent XML parent element.
     * @param tagName Element tag name.
     * @param attrName AttributeName.
     * @return attribute integer value.
     */
    public static int parseIntAttribute(Element parent, String tagName, 
           String attrName) {
        // Get attribute String value
        String value = parseStringAttribute(parent, tagName, attrName);
        try {
            return Integer.parseInt(value);

        } catch (NumberFormatException ex) {
            throw new WebServiceException(new StringBuffer("Error validating ")
              .append(tagName).append(" tag int attribute ").append(attrName)
              .append(": ").append(value).toString());
        }
    }
    
    /**
     * Parse the node value to boolean 
     * 
     * @return true,false
     * @param name node name .
     * @param XML parent element . 
     */
    public static boolean parseBooleanNode(Element parent, String name) {
        String value = parseStringNode(parent, name);
        if(value != null && value.trim().toLowerCase().equals("true")) {
            return true;
        } else if(value != null && value.trim().toLowerCase().equals("false")) {
            return false;
        } else {
            throw new WebServiceException("Accepted values is either true or false");
        }
    }

    /**
     * Get double value of this node.
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @return double value of this node.
     */
    public static double parseDoubleNode(Element parent, String name) {
        String value = parseStringNode(parent, name);
        try {
            return Double.parseDouble(value);

        } catch (NumberFormatException ex) {
            throw new WebServiceException(
                    new StringBuffer("Invalid double number value for ")
                     .append(name).append(" element tag: ").append(value)
                       .toString());
        }
    }    

    /**
     * Format string node
     * 
     * @param parent XML parent element.
     * @param name Node tag name.
     * @param value to be added to the node
     * @throws javax.xml.soap.SOAPException
     */    
    public static void formatStringNode(SOAPElement parent, String name ,
                                        String value) throws SOAPException {
        if(value == null || value.trim().length() == 0) {            
            return;
        }

        parent.addChildElement(name).addTextNode(value);
    }     
    
    /**
     * Format boolean node
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @param value to be added to the node
     * @throws javax.xml.soap.SOAPException
     */
    public static void formatBooleanNode(SOAPElement parent, String name, 
                                         Integer value)throws SOAPException {
        if(value == null) {            
            return;
        }
        if(value.intValue() == 2 ) {
            parent.addChildElement(name).addTextNode("true");
            return;
        } else {
            parent.addChildElement(name).addTextNode("false");            
        }
    }

    /**
     * Format Double node
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @param value to be added to the node
     * @throws javax.xml.soap.SOAPException
     */    
    public static void formatDoubleNode(SOAPElement parent, String name , Double value) throws SOAPException {
        if(value == null) {            
            return;
        }
        parent.addChildElement(name).addTextNode(""+value.doubleValue());
    }   
    
    /**
     * Format Integer node
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @param value to be added to the node
     * @throws javax.xml.soap.SOAPException
     */    
    public static void formatIntegerNode(SOAPElement parent, String name , Integer value) throws SOAPException {
        if(value == null) {            
            return;
        }
        parent.addChildElement(name).addTextNode(""+value.intValue());
    }      
    
    /**
     * Format Date node
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @param value to be added to the node
     * @throws javax.xml.soap.SOAPException
     */    
    public static void formatDateNode(SOAPElement parent, String name,
                       java.util.Date value) throws SOAPException {
        if(value == null) {            
            return;
        }

        parent.addChildElement(name).addTextNode(
          GlobalUtilities.DATE_FORMAT.format(value));
    }      

    /**
     * Format Long node
     * 
     * @param parent XML parent element .
     * @param name Node tag name.
     * @param value to be added to the node
     * @throws javax.xml.soap.SOAPException
     */    
    public static void formatLongNode(SOAPElement parent, String name , Long value) throws SOAPException {
        if(value == null) {            
            return;
        }
        parent.addChildElement(name).addTextNode(""+value.longValue());
    }
    
    /**
     * Remove node from parent.
     * 
     * @param parent Parent node.
     * @param name Node name to be removed.
     */
    public static void removeNode(SOAPElement parent, String name) {
        Node node = getNodeByTagName(parent, name);
        if (node == null) {
            return;
        }

        parent.removeChild(node);
    } 

    /**
     * Get node text content.
     * 
     * @param node Node to be extracted.
     * @return text content.
     */
    public static String getTextContent(Node node) throws DOMException {
        if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
            return node.getNodeValue();	
        }

        NodeList nodeList = node.getChildNodes();
        if (nodeList == null || nodeList.getLength() == 0) {
            return "";
        }
        
        StringBuffer textContent = new StringBuffer();
        for (int i = 0; i < nodeList.getLength(); i++)  {
            textContent.append(nodeList.item(i).getNodeValue());
        }

        return textContent.toString();
    }
    
    /**
     * Decode Arabic Values
     * 
     * @param value Value to be encoded
     * @return Decoded arabic value
     */
    public static String decodeURLFromArabic(String value){
        try{
            return URLDecoder.decode(value,"WINDOWS-1256");
        }catch(Exception e){
            return "";            
        }
    }
    

    
}