package com.fhzn.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhzn.demo.constant.StatusEnum;
import lombok.Data;

@Data
@TableName("auth_application")
public class Test extends BaseEntity {

    @TableId
    private Long id;
    private String name;
    private String code;//账号
    private String comment;//密码
    private String type;//所属部门
    private StatusEnum status;
    private Boolean ifDeleted;
}
