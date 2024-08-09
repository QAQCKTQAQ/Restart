//package com.fhzn.demo.web.request;
//
//import com.fhzn.demo.constant.StatusEnum;
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Data;
//import org.hibernate.validator.constraints.Length;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//
//@Data
//public class TestRequest {
//
//    @Schema(description = "id，传了就是更新")
//    private Long id;
//    @Schema(description = "账号", minLength = 3, maxLength = 20)
//    @NotEmpty
//    @Length(min = 3, max = 20)
//    private String code;
//    @Schema(description = "姓名")
//    @NotEmpty
//    @Length(min = 1, max = 10)
//    private String name;
//    @Schema(description = "状态。1：启用；2：禁用")
//    @NotNull
//    private StatusEnum status;
//    @Schema(description = "密码")
//    @Length(max = 64)
//    @NotNull
//    private String comment;
//    @Schema(description = "所属部门")
//    @Length(max = 64)
//    @NotNull
//    private String type;
//}
