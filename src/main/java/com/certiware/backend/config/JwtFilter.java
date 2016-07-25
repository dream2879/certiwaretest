package com.certiware.backend.config;

import java.io.IOException;
import java.util.Enumeration;

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
	

	private RoleFilter roleFilter;;
		
	public JwtFilter() {
		// TODO Auto-generated constructor stub
	}	
	
	public JwtFilter(RoleFilter roleFilter){
		
		this.roleFilter = roleFilter;		
				
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    public void doFilter(final ServletRequest req,
            final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {
	
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization"); // token
		final String roleCode = request.getHeader("RoleCode");
		final String reqURL = request.getRequestURI();
		
		/* 헤더 logging 기능*/	
		Enumeration<?> names = request.getHeaderNames();
		
		while (names.hasMoreElements()) {
		      String name = (String) names.nextElement();
		      Enumeration<?> values = request.getHeaders(name); // support multiple values
		      if (values != null) {
		        while (values.hasMoreElements()) {
		          String value = (String) values.nextElement();
		          System.out.println(name + ": " + value);
		        }
		      }
		    }		
		
		System.out.println("입력값 확인" + roleCode + " " + reqURL);

		// http 호출 method가 "OPTIONS"일 경우 token 체크를 스킵한다.
		if ("OPTIONS".equals(request.getMethod())) 
		{
			response.setStatus(HttpServletResponse.SC_OK);
			
			chain.doFilter(req, res);
			System.out.println("HTTP METHOD 'OPTIONS' SKIP");
		}
		// token 체크 스킵할 URL 설정
		else if(request.getRequestURI().equals("/main/login") || request.getRequestURI().equals("/progress/excelDownload") || request.getRequestURI().equals("/favicon.ico"))		
		{						
			chain.doFilter(req, res);
			System.out.println("권한체크제외 URL : "+request.getRequestURI());
		}
		// 권한 및 token체크 시작
		else
		{		
			// requestURL 권한 체크
			System.out.println("check role ... " + roleFilter.checkRole(roleCode, reqURL));
			
			if(!roleFilter.checkRole(roleCode, reqURL)){
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				throw new ServletException ("권한 없음");
			}
		
			// token 유효성 검사
			System.out.println("check token ...");			
			
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
