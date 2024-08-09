package com.fhzn.demo.web.interceptor;

import com.fhzn.commons.toolkit.constant.Headers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestContextInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String user = request.getHeader(Headers.LOGIN_USER);
        String appCode = request.getHeader(Headers.APP_CODE);
        RequestContext.setRequestData(new RequestData(user, appCode));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContext.clean();
    }
}
