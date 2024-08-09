package com.fhzn.demo.web.converter;

import com.fhzn.demo.entity.Test;
import com.fhzn.demo.web.request.TestRequest;
import com.fhzn.demo.web.vo.TestVO;


public interface TestMapper {

    static TestVO toApplicationVO(Test test) {
        if (test == null) {
            return null;
        }

        TestVO testVO = new TestVO();

        testVO.setId(test.getId());
        testVO.setName(test.getName());
        testVO.setCode(test.getCode());
        testVO.setStatus(test.getStatus());
        testVO.setComment(test.getComment());
        testVO.setType(test.getType());
        testVO.setCreator(test.getCreator());
        testVO.setModifier(test.getModifier());
        testVO.setCreatedTime(test.getCreatedTime());
        testVO.setUpdatedTime(test.getUpdatedTime());

        return testVO;
    }

    static Test fromRequest(TestRequest request) {
        if (request == null) {
            return null;
        }

        Test test = new Test();

        test.setId(request.getId());
        test.setName(request.getName());
        test.setCode(request.getCode());
        test.setStatus(request.getStatus());
        test.setComment(request.getComment());
        test.setType(request.getType());

        return test;
    }
}
