package com.github.fish.blog.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

	/**
     * 获取请求的来源IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestRealIP(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null) {
			final int idx = ip.indexOf(',');
			if (idx > -1) {
				ip = ip.substring(0, idx);
			}
		}
		return ip;
	}

}