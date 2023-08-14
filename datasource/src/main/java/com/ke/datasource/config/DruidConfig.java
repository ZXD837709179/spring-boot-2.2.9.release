package com.ke.datasource.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	//对于druid数据库连接池，从spring.datasource.druid或者spring.datasource设置基础属性
	public DataSource dataSource(){
		/**
		 * 配置成 mydruid.config的前缀后，那么原先的可以直接生效的spring.datasource.druid下面的配置不会生效了，因为只会有一处的datasource生成
		 *       max-active: 10
		 *       min-idle: 2
		 *       不生效
		 */
		return new DruidDataSource();
	}
}
