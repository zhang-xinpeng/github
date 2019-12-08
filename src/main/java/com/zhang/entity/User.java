package com.zhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id//指明数据库中的id，用于通过id做查询的通用mapper
    private String id;
    private String username;
    private String password;
    private String sex;
    private String name;
}
