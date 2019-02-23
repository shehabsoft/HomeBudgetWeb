package util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Smart Traffic web application security filter, it make sure that all JSF pages under "/secure/" can be accessed only
 * by loged-in users
 */
@WebFilter(filterName = "SmartSecurityFilter", urlPatterns = "/HomeBudgetWeb/web/*")
public class SecurityFilter implements Filter {
    /*
     * Constants
     */

//    private static final UserHandler userHandler = UserHandler.getInstance();
//    private static final RoleGroupHandler roleGroupHandler = new RoleGroupHandler();
//    private static final RoleHandler roleHandler = new RoleHandler();
    private static final String AUTHORIZATION_URL = "/include/not_authorized.htm";
    private static final String AUTHORIZATION_DENIAL_URL = "/include/not_authorized_denied_role.jsp";
    private static final String LOGIN = "faces/jsf/auth/login.jsf";
    private static final String LANDING_SECURITY = "userAuthorizationBean";
    
    /*
     * Instance Methods
     */
    
    /** Public access pages which are accessible by all users */
    private Set<String> publicPagesSet;
    
    /** Map<newUrl, url> to retrieve old URL if smart JSF pages are disabled */
    private Map<String, String> newOldPagesMap;
    
    /*
     * Initialize and destroy methods
     */

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    //    publicPagesSet = roleGroupHandler.getPublicPagesUrls();
        
        newOldPagesMap = Collections.synchronizedMap(new HashMap<String, String>());
    }

    @Override
    public void destroy() {
        // Empty Body
    }
    
    /*
     * Methods
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // Get/Parse related parameters
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();

        HttpSession session = request.getSession();
        if(!isPublicAccessPage(path))
        {
        	if(session.getAttribute("UserVo")==null)
	        {
	        	System.out.println("Invalid Authorization");
	        	response.sendRedirect(request.getContextPath() + "/web/login.jsf");
	        }else
	        {
	        	System.out.println(session.getAttribute("UserVo"));
	        }
        }
        
       // HttpSession session = request.set
       
        
        // View requested resource
        filterChain.doFilter(servletRequest, servletResponse);
    }



    /**
     * Check if this uri is a publicc access URI
     *
     * @param uri Requested resource URI
     * @return true if this is a public access page
     */
    private boolean isPublicAccessPage(String uri) {
        // Check public pages URL's
        return uri.contains("login");
    }
}
