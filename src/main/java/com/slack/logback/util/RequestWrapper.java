package com.slack.logback.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class RequestWrapper {

	private HttpServletRequest request;
	
	private RequestWrapper(HttpServletRequest request){
		this.request = request;
	}
	
	public static RequestWrapper of(HttpServletRequest request){
		return new RequestWrapper(request);
	}
	
	public static RequestWrapper of(ServletRequest request){
		return of((HttpServletRequest) request);
	}
	
	public Map<String, String> headerMap() {
		Map<String, String> convertedHeaderMap = new HashMap<String, String>();

		Enumeration<String> headerMap = request.getHeaderNames();

		while (headerMap.hasMoreElements()) {
			String name = headerMap.nextElement();
			String value = request.getHeader(name);

			convertedHeaderMap.put(name, value);
		}
		return convertedHeaderMap;
	}

	public Map<String, String> parameterMap() {
		Map<String, String> convertedParameterMap = new HashMap<String, String>();
		Map<String, String[]> parameterMap = request.getParameterMap();

		for (String key : parameterMap.keySet()) {
			String[] values = parameterMap.get(key);
			StringJoiner valueString = new StringJoiner(",");

			for (String value : values) {
				valueString.add(value);
			}

			convertedParameterMap.put(key, valueString.toString());
		}
		return convertedParameterMap;
	}

	public String getRequestUri() {
		return request.getRequestURI();
	}
}
