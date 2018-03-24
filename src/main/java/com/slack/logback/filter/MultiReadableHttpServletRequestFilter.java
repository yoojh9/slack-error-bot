package com.slack.logback.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.slack.logback.util.MultiReadableHttpServletRequest;

@Component("multiReadableHttpServlerRequestFilter")
public class MultiReadableHttpServletRequestFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		MultiReadableHttpServletRequest multiReadRequest = new MultiReadableHttpServletRequest((HttpServletRequest) req);
		chain.doFilter(multiReadRequest, res);
	}
	
	@Override
	public void destroy() {}



	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
