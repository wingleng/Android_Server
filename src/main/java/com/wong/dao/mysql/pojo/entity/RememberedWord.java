package com.wong.dao.mysql.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (RememberedWord)实体类
 *
 * @author makejava
 * @since 2021-12-04 12:31:40
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RememberedWord implements Serializable {
    private static final long serialVersionUID = 593979484296095708L;
    
    private Integer userId;
    
    private String wordId;

}

