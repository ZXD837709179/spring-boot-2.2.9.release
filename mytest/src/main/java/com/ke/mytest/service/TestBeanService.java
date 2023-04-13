package com.ke.mytest.service;

import com.ke.mytest.model.TestBean;
import com.ke.mytest.service.anno.Handle;

import javax.annotation.Resource;

/**
 * @author Zhang Xudong
 * @date 2023/4/13 22:39
 */
@Handle
public class TestBeanService {
	@Resource
	private TestBean testBean;

	public void TestBeanService() {
		System.out.println("依赖Bean加载");

		System.out.println("TestBeanService 的方法打印:" + testBean.getBeanNum());
	}

}
