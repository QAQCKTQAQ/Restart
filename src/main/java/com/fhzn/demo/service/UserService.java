package com.fhzn.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhzn.demo.entity.User;
import com.fhzn.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

//    public boolean userExists(User user){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", user.getId());
//
//        int count = UserMapper.selectCount(queryWrapper);
//        return count > 0;
//    }

}
