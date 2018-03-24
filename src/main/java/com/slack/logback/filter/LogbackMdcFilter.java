package com.slack.logback.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.slack.logback.util.AgentUtils;
import com.slack.logback.util.HttpUtils;
import com.slack.logback.util.MDCUtil;
import com.slack.logback.util.RequestWrapper;


/*
 * MDC는 SLF4J API중 하나다. 
 *  ThreadLocal에 데이터를 넣고, 로그를 출력하는 시점에 넣은 데이터들을 맵핑 할 수 있게 해주는 ThreadLocal 유틸리티 정도로 생각하면 되겠다.
 *  즉 MDC에다 어떠한 데이터를 넣으면 해당 HttpRequest(Thread)가 존재하는 동안 데이터가 유지되고, 만약 에러가 발생 한 상황이라면 MDC의 데이터들을 로그에 활용 할 수 있다는 의미이다.
 */
@Component("logbackMdcFilter")
public class LogbackMdcFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		RequestWrapper requestWrapper = RequestWrapper.of(req);

		// Set Http Header
		MDCUtil.setJsonValue(MDCUtil.HEADER_MAP_MDC, requestWrapper.headerMap());

		// Set Http Body
		MDCUtil.setJsonValue(MDCUtil.PARAMETER_MAP_MDC, requestWrapper.parameterMap());

		// If you use SpringSecurity, you could SpringSecurity UserDetail Information.
		MDCUtil.setJsonValue(MDCUtil.USER_INFO_MDC, HttpUtils.getCurrentUser());

		// Set Agent Detail
		MDCUtil.setJsonValue(MDCUtil.AGENT_DETAIL_MDC, AgentUtils.getAgentDetail((HttpServletRequest) req));

		// Set Http Request URI
		MDCUtil.set(MDCUtil.REQUEST_URI_MDC, requestWrapper.getRequestUri());
		try {
			chain.doFilter(req, res);
		} finally {
			MDC.clear();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
