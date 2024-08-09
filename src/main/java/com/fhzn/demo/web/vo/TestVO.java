package com.fhzn.demo.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fhzn.demo.constant.TimeConstants;
import com.fhzn.demo.constant.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class TestVO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "名称")
    private String name;
    private String code;
    private StatusEnum status;
    private String comment;
    private String creator;
    private String type;
    private String modifier;
    @JsonFormat(pattern = TimeConstants.PATTERN_YYYY_MM_DD_HH_MM_SS)
    private Date createdTime;
    @JsonFormat(pattern = TimeConstants.PATTERN_YYYY_MM_DD_HH_MM_SS)
    private Date updatedTime;
}
