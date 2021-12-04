package com.wong.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置mybatis
 */
@Configuration
@MapperScan("com.wong.dao.mysql.mapper")
public class MybatisPlusConfig {
}
