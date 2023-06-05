package com.ke.mytest.configuration;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

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
		System.out.println("#######   InitializerExt initialize        #######");
	}
}
