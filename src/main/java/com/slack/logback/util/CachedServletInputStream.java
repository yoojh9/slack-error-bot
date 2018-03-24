package com.slack.logback.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;

public class CachedServletInputStream extends ServletInputStream {

	private ByteArrayInputStream input;
	
	public CachedServletInputStream(){}
	
	public CachedServletInputStream(ByteArrayOutputStream cachedBytes) {
		input = new ByteArrayInputStream(cachedBytes.toByteArray());
	}

	@Override
	public int read() throws IOException {
		return input.read();
	}
	

}
