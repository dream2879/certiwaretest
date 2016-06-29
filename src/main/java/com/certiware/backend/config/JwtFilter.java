package com.certiware.backend.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {
	
    public void doFilter(final ServletRequest req,
            final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {
	
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");	
		
		System.out.println(request.getRequestURI());
		
		if(request.getRequestURI().equals("/user/login") || request.getRequestURI().equals("/test/excelDownload"))		
		{
			System.out.println("if문 진입" + request.getRequestURI());
			chain.doFilter(req, res);
		}
		else{
			System.out.println("check token..");	
		
		
		if ("OPTIONS".equals(request.getMethod())) {
		response.setStatus(HttpServletResponse.SC_OK);
		
		chain.doFilter(req, res);
		} else {
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new ServletException ("Missing or invalid Authorization header");
		}
		
		final String token = authHeader.substring(7);
		
		try {
			final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
			request.setAttribute("claims", claims);
		} catch (final SignatureException e) {
			throw new ServletException ("Invalid token");
		}		
		
		chain.doFilter(req, res);
		}
		
		}
		
	}

}
