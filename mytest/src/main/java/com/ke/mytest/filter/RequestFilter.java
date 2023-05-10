package com.ke.mytest.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zhang Xudong
 * @date 2023/5/9 22:33
 */
@WebFilter(urlPatterns = "/*")
@Slf4j
public class RequestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		String servletPath = ((RequestFacade) servletRequest).getServletPath();
		Map<String, String[]> parameterMap = servletRequest.getParameterMap();
		log.info("请求路径:{}，参数:{}", servletPath, printRequestParam(parameterMap));
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	private String printRequestParam(Map<String, String[]> parameterMap) {
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, String[]>> entryIterator = parameterMap.entrySet().iterator();
		while (entryIterator.hasNext()) {
			Map.Entry<String, String[]> entry = entryIterator.next();
			sb.append(entry.getKey() + ":" + Arrays.stream(entry.getValue()).collect(Collectors.toList()).toString() + "     ");
		}
		return sb.toString();

	}
}
