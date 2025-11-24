package com.evergreen.EvergreenServer.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;
        String authorizationHeader=httpServletRequest.getHeader("Authorization");
        System.out.println("===============================");
        System.out.println("headerValue "+authorizationHeader);
        System.out.println("===============================");
        if(authorizationHeader==null ||authorizationHeader.isEmpty()||authorizationHeader.isEmpty()){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
            return;
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
    }
