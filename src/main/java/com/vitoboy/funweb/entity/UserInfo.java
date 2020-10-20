package com.vitoboy.funweb.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: vito
 * @Date: 2020/10/13 14:27
 * @Version: 1.0
 */
@Data
@ToString
public class UserInfo {

    private String userId;

    private String username;

    private String password;

    private Integer gender;

    private Integer age;

    private String email;

    private String phone;
}
