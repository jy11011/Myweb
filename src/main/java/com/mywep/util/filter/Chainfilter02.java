package com.mywep.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Chainfilter02 implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("2번째 필터");
		
		chain.doFilter(request, response);
		
		
		
		
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	

}
