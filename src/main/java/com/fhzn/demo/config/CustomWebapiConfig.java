package com.fhzn.demo.config;

import com.fhzn.commons.toolkit.context.UserInfo;
import com.fhzn.commons.webapi.interceptor.UserInfoLoader;
import com.fhzn.demo.remote.wups.TestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomWebapiConfig {


    @Bean
    public UserInfoLoader userInfoLoader(TestClient client) {
        return (user, appCode) -> {
            return new UserInfo("code", "user", "user", true);
        };
    }



}
