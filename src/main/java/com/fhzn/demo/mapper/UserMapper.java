package com.fhzn.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhzn.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//      @Select("select ")
//        @Update("UPDATE auth_application SET if_deleted = 0 WHERE id = #{id}")
//        int restoreById(Long id);
}