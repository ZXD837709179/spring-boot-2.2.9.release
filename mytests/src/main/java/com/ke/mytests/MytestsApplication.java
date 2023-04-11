package com.ke.mytests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 核心启动类  等同于
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
 * @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
 *
 */

@SpringBootApplication
public class MytestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MytestsApplication.class, args);
	}

}
