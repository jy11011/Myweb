package com.mywep.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//1.filter인텊페이스를 상속받고
//2.web.xml or 이노테이션 중 하나를 선택해서 필터를 등록
//@WebFilter("/*") //모든요청
public class Chainfilter01 implements Filter{

	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("1번필터 실행됨");
		
		//다음으로 연결
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
