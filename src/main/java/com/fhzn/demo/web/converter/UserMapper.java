package com.fhzn.demo.web.converter;

import com.fhzn.demo.entity.User;
import com.fhzn.demo.web.request.UserRequest;
import com.fhzn.demo.web.vo.UserVO;

public interface UserMapper {
    static UserVO toApplicationVO(User user) {
        if (user == null) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setId(user.getId());
        userVO.setName(user.getName());
        userVO.setUsername(user.getUsername());
        userVO.setPassword(user.getPassword());
        userVO.setStatus(user.getStatus());
        userVO.setType(user.getType());
        userVO.setPhonenumber(user.getPhonenumber());
        userVO.setCreator(user.getCreator());
        userVO.setModifier(user.getModifier());
        userVO.setCreatedTime(user.getCreatedTime());
        userVO.setUpdatedTime(user.getUpdatedTime());
        userVO.setIf_delete(user.getIf_delete());

        return userVO;
    }

    static User fromRequest(UserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();


        user.setId(request.getId());
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setStatus(request.getStatus());
        user.setType(request.getType());
        user.setPhonenumber(request.getPhonenumber());
        user.setIf_delete(request.getIf_delete());


        return user;
    }
}
