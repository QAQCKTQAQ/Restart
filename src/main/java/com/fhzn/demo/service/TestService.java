package com.fhzn.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhzn.demo.entity.Test;
import com.fhzn.demo.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService extends ServiceImpl<TestMapper, Test> {


}
