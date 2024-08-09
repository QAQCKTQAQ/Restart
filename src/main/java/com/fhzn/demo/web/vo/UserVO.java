package com.fhzn.demo.web.vo;

import com.fhzn.demo.constant.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


@Data
public class UserVO {
    private Long id;
    private String name;
    private String account;//账号
    private String password;//密码
    private String type;//所属部门
    private String status;//停用启用状态
    private String phonenumber;//手机号
    private String creator;
    private String modifier;
    private Date createdTime;
    private Date updatedTime;
}
