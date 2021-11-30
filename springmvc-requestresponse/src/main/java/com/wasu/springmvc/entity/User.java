package com.wasu.springmvc.entity;

import lombok.Data;

/**
 * @author ZHANGLEI
 * @date 2021/11/28 11:27
 */
@Data
public class User {
    private Long id;
    private String username;
    private int sex;
    private String email;
}
