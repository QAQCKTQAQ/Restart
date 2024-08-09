package com.fhzn.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhzn.demo.constant.StatusEnum;
import lombok.Data;

@Data
@TableName("user")
public class User extends BaseEntity {

    @TableId
    private Long id;
    private String name;
    private String account;//账号
    private String password;//密码
    private String type;//所属部门
    private String status;//停用启用状态
    private String phonenumber;//手机号

}

