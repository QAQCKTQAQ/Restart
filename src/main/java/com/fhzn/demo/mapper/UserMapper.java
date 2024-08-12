package com.fhzn.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhzn.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}