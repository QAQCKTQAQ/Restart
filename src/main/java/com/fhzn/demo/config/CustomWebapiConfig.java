package com.fhzn.demo.config;

import com.fhzn.commons.toolkit.context.UserInfo;
import com.fhzn.commons.webapi.entity.WebResponse;
import com.fhzn.commons.webapi.interceptor.UserInfoLoader;
import com.fhzn.demo.remote.wups.TestClient;
import com.fhzn.demo.remote.wups.vo.AuthQueryResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.util.Objects;

@Slf4j
@Configuration
public class CustomWebapiConfig {


    @Bean
    public UserInfoLoader userInfoLoader(TestClient client) {
        return (user, appCode) -> {
//            WebResponse<AuthQueryResponseVO> userResponse = client.authQuery(user);
//            if (!userResponse.isSuccess()) {
//                log.error("获取用户:{} 权限信息报错, error:{}", user, userResponse.getMessage());
//                throw new IllegalStateException("获取用户信息出错");
//            }
//            AuthQueryResponseVO data = userResponse.getData();
//            if (Objects.isNull(data)) {
//                return null;
//            }
            return new UserInfo("code", "user", "user", true);
        };
    }



}
