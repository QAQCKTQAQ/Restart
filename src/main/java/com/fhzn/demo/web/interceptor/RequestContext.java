package com.fhzn.demo.web.interceptor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestContext {

    private static final ThreadLocal<RequestData> CONTEXT = new ThreadLocal<>();

    public static void setRequestData(RequestData data) {
        CONTEXT.set(data);
    }

    public static RequestData getRequestData() {
        return CONTEXT.get();
    }

    public static void clean() {
        CONTEXT.remove();
    }
}
