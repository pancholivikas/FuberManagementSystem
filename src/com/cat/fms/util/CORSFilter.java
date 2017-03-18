package com.cat.fms.util;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {
	
	//static Logger log = Logger.getLogger(CORSFilter.class.getName());

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		
		if (httpRequest instanceof HttpServletRequest) {
			String requestMethod = httpRequest.getMethod().toString();
			if(requestMethod.equalsIgnoreCase("POST")){
				response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader("Access-Control-Allow-Methods",
						"POST");
				//		"POST, GET, OPTIONS, DELETE");
				response.setHeader("Access-Control-Max-Age", "3600");
				response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
				ServletRequestWrapper wrappedReq=null;
			    
				
				wrappedReq = new ServletRequestWrapper(
						(HttpServletRequest) httpRequest);
				
				chain.doFilter(httpRequest, response);
			}else{
			
				response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader("Access-Control-Allow-Methods",
						"GET, OPTIONS, DELETE");
				//		"POST, GET, OPTIONS, DELETE");
				response.setHeader("Access-Control-Max-Age", "3600");
				response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
				Map<String, String[]> extraParams = new TreeMap<String, String[]>();
				ServletRequestWrapper wrappedReq=null;
			    
				
				wrappedReq = new ServletRequestWrapper(
						(HttpServletRequest) httpRequest);
				
				chain.doFilter(httpRequest, response);
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
