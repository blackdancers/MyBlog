package com.github.fish.common.instantiator;

import com.github.fish.common.constant.IConstInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(IConstInfo.CURRENT_USER) == null) {
            response.sendRedirect("/admin");//登录页面
        }
        return true;
    }
}
