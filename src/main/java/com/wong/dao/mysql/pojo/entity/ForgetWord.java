package com.wong.dao.mysql.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ForgetWord)实体类
 *
 * @author makejava
 * @since 2021-12-04 12:31:22
 */
@Data
public class ForgetWord implements Serializable {
    private static final long serialVersionUID = -64689942495447999L;
    
    private Integer userId;
    
    private String wordId;

}

