package com.fhzn.demo.remote;

import com.fhzn.commons.toolkit.constant.Headers;
import com.fhzn.demo.web.interceptor.RequestContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 内部服务，调用时需注入user信息到header
 */
public class UserContextInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        template.header(Headers.LOGIN_USER, RequestContext.getRequestData().getNickname());
        template.header(Headers.APP_CODE, RequestContext.getRequestData().getAppCode());
    }
}
