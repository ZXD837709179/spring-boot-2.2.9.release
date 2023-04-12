package com.ke.mytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MytestApplication {
	/**
	 * 核心启动类  等同于
	 *
	 * @SpringBootConfiguration
	 * @EnableAutoConfiguration
	 * @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
	 * @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
	 */
	public static void main(String[] args) {
		SpringApplication.run(MytestApplication.class, args);
	}

}