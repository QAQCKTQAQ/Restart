package com.fhzn.demo.remote.wups;

import com.fhzn.commons.webapi.entity.WebResponse;
import com.fhzn.demo.remote.wups.vo.AuthQueryResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wupsClient", url = "${remote.host.wups}")
public interface TestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/auth-service/auth/query")
    WebResponse<AuthQueryResponseVO> authQuery(@RequestParam(required = false) String targetUser);
}
