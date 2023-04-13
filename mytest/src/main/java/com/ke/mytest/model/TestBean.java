package com.ke.mytest.model;

import com.ke.mytest.service.anno.Handle;

/**
 * @author Zhang Xudong
 * @date 2023/4/13 22:36
 */
@Handle //自定义的bean注解
public class TestBean {
	Integer i;

	public TestBean() {
		this.i = 2;
		System.out.println("单Bean 注册：TestBean");
	}

	public Integer getBeanNum() {
		return i;
	}
}
