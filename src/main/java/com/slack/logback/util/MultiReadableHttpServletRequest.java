package com.slack.logback.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

/*
 *  HttpServletRequest InputStream을 여러번 읽을 수 있도록 Cache 하는 Filter를 적용.
 *  최초의 inputStream을 ByteArrayOutputStream에 복사해두고, 항상 카피된 ByteArrayOutputStream을 반환.
 */
public class MultiReadableHttpServletRequest extends HttpServletRequestWrapper{

	private ByteArrayOutputStream  cachedBytes;
	
	public MultiReadableHttpServletRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if(cachedBytes == null){
			cacheInputStream();
		}
		return new CachedServletInputStream(cachedBytes);
	}
	
	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}
	
	public void cacheInputStream() throws IOException {
		cachedBytes = new ByteArrayOutputStream();
		IOUtils.copy(super.getInputStream(), cachedBytes);
	}
	
	
	
}
