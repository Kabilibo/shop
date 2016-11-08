package com.mycourse.controller.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycourse.meta.User;
import com.mycourse.util.URIList;

public class MyFilter implements Filter {
	
	private ArrayList<String> buyerURI = URIList.getBuyerURI();
	private ArrayList<String> sellerURI = URIList.getSellerURI();
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest)request;
		HttpSession session = req.getSession();
		if (req.getRequestURI().equals("/logout")||req.getRequestURI().equals("/login")){
			session.setAttribute("user", null);
			chain.doFilter(request, response);
		}
		else if (buyerURI.contains(req.getRequestURI())){
			if (session.getAttribute("user")!=null && ((User)session.getAttribute("user")).getUsertype()==0){
				chain.doFilter(request, response);
			}
			else{
				((HttpServletResponse)response).sendRedirect("/login");
			}
		}
		else if (sellerURI.contains(req.getRequestURI())){
			if (session.getAttribute("user")!=null && ((User)session.getAttribute("user")).getUsertype()==1){
				chain.doFilter(request, response);
			}
			else{
				((HttpServletResponse)response).sendRedirect("/login");
			}
		}
		else{
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}




}
