package com.fhzn.demo.web.vo;

import lombok.Data;

import java.util.Date;


@Data
public class UserVO {
    private Long id;
    private String name;
    private String username;//账号
    private String password;//密码
    private String type;//所属部门
    private String status;//停用启用状态
    private String phonenumber;//手机号
    private String creator;
    private String modifier;
    private Date createdTime;
    private Date updatedTime;
    private int if_delete;
    private Integer version;
}
