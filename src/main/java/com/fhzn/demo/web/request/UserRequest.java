package com.fhzn.demo.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @Schema(description = "id，传了就是更新")
    private Long id;

    @Schema(description = "姓名")
    @NotEmpty
    @Length(min = 1, max = 20)
    private String name;

    @Schema(description = "账号", minLength = 3, maxLength = 20)
    @NotEmpty
    @Length(min = 3, max = 20)
    private String username;

    @Schema(description = "密码")
    @Length(max = 255)
    @NotNull
    private String password;

    @Schema(description = "所属部门")
    @Length(max = 32)
    private String type;

    @Schema(description = "状态。0：启用；1：禁用")
    @NotNull
    private String status;

    @Schema(description = "手机号")
    @Length(max = 32)
    private String phonenumber;

    @Schema(description = "状态。0：存在；1：删除")
    @NotNull
    private int if_delete;

    @Schema(description = "乐观锁版本")
    private Integer version;
}
