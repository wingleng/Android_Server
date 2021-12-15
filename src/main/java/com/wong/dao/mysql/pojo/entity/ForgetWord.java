package com.wong.dao.mysql.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (ForgetWord)实体类
 *
 * @author makejava
 * @since 2021-12-04 12:31:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgetWord implements Serializable {
    private static final long serialVersionUID = -64689942495447999L;
    
    private Integer userId;
    
    private String wordId;

    private Date create_tm;
}

