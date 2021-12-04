package com.wong.dao.mysql.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-12-04 12:29:58
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -44570384671120294L;
    
    private Integer userId;
    
    private String userName;
    
    private String userPassword;


}

