package util;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.xml.DOMConfigurator;
 
@WebListener
public final class Log4jInitListener implements ServletContextListener {
 
    public Log4jInitListener() {
    }
 
    public void contextDestroyed(ServletContextEvent paramServletContextEvent)  { 
    }
 
    public void contextInitialized(ServletContextEvent servletContext)  { 
        String webAppPath = servletContext.getServletContext().getRealPath("/");
         String log4jFilePath = webAppPath + "WEB-INF/classes/log4j.xml";
        DOMConfigurator.configure(log4jFilePath);
        System.out.println("initialized log4j configuration from file:"+log4jFilePath);
    }
     
}