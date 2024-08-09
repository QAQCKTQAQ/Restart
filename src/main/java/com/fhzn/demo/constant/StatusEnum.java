package com.fhzn.demo.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fhzn.commons.toolkit.exception.BuzException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusEnum {

    ENABLE(1),

    DISABLE(2);

    @JsonValue
    @EnumValue
    public final int status;

    public static StatusEnum of(@NonNull Integer status) {
        for (StatusEnum value : StatusEnum.values()) {
            if (value.status == status) {
                return value;
            }
        }
        throw new BuzException("Unknown status:" + status);
    }
}
