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

    @Schema(description = "状态。1：启用；2：禁用")
    @NotNull
    private String status;

    @Schema(description = "手机号")
    @Length(max = 32)
    private String phonenumber;
}
