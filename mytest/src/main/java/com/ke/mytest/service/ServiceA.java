package com.ke.mytest.service;

import javax.annotation.PostConstruct;

/**
 * @author Zhang Xudong
 * @date 2023/5/10 10:39
 */

public class ServiceA {

	public void say() {
		System.out.println("i am ServiceA");
	}

	/**
	 * PostConstruct所在的类 不需要注册成bean 只要实例化就会生成
	 **/
	@PostConstruct
	public void postConstruct() {
		System.out.println("ServiceA postConstruct");
	}

}
