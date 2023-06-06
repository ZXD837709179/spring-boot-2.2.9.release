package com.ke.mytest.ext;

import com.ke.mytest.service.impl.ServiceAImpl;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Zhang Xudong
 * @date 2023/6/2 12:11
 * 用于在 Spring 容器刷新之前执行的一个回调函数，通常用于向 SpringBoot 容器中注入属性。
 * 这里的扩展接口方便开发者自己注入
 * https://www.cnblogs.com/fnlingnzb-learner/p/16423669.html
 */
public class ApplicationContextInitializerExt implements ApplicationContextInitializer {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		//设置激活文件是无效的 因为此时配置文件读取已经结束了 已经读取预先设置的配置文件和默认配置文件中的属性
//		ConfigurableEnvironment environment = applicationContext.getEnvironment();
//		environment.setActiveProfiles("test");
		System.out.println("sout InitializerExt initialize ");
	}
}
