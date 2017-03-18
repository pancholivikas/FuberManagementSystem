package com.cat.fms.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RestAuthenticationFilter implements javax.servlet.Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	private static final String URL = "https://dealermobileapp.azurewebsites.net";
	private static final String SCOPES = "openid profile";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {
		filter.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
