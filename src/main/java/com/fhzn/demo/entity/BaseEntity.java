package com.fhzn.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private String creator;
    private String modifier;
    private Date createdTime;
    private Date updatedTime;
    private int if_delete;
}
