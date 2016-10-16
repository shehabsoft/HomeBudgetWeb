package util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.WebServiceException;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public  class TransactionServiceParser {

    /**
     * Parse create transaction request.
     * 
     * @param request Transaction data in XML format.
     * @return Transaction parameters.
     * @throws Exception 
     */
    public String parseCreateTransactionResponse(String request) throws BusinessException {
        if (GlobalUtilities.isBlankOrNull(request)) {
            try {
				throw new Exception("·„ Ì „ ≈” ·«„ √Ì »Ì«‰« ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        String content="";
        String status="";
        Document doc=null;
        try
        {
        InputStream in = null;
    
            request = request.replaceAll("<!\\[CDATA\\[", "");
            request = request.replaceAll("\\]\\]>", "");
            // Parse config file
            byte[] requestBytes = request.getBytes("UTF-8");
            in = new ByteArrayInputStream(requestBytes);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(in);
            NodeList statusNode=  doc.getElementsByTagName("status");
            status=statusNode.item(0).getTextContent();
	        }catch(Exception e)
	        {
	        	System.out.println(e);
	        }
            if(status!=null && "Certified".equals(status))
            {
            	
            	content="Operation Done Successfully";
            }else
            {
                NodeList nList=  doc.getElementsByTagName("description-ar");
                Node nNode = nList.item(0);
                content= nNode.getTextContent();
                throw new BusinessException(content);
                
            }
            return content;
    }

}
