package com.ke.mytest.service.impl;

import com.ke.mytest.service.ServiceA;
import org.springframework.stereotype.Service;

/**
 * @author Zhang Xudong
 * @date 2023/5/10 10:38
 */
@Service
public class ServiceAImpl extends ServiceA {
	public ServiceAImpl() {
		System.out.println("ServiceAImpl");
	}


	@Override
	public void say() {
		System.out.println("i am ServiceAImpl");
	}
}
