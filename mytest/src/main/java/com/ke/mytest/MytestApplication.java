package com.ke.mytest;

import com.ke.mytest.service.anno.EnableHandle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableHandle
@ServletComponentScan
@EnableFeignClients
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
//		args = new String[]{"1","2","--name=zhangsan","--name=lisi","age=18","--developer.name=shirenchuang666"};
		SpringApplication.run(MytestApplication.class, args);
	}

}
