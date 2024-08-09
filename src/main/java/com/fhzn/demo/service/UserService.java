package com.fhzn.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhzn.demo.entity.User;
import com.fhzn.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
